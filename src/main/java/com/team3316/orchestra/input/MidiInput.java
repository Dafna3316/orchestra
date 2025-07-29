package com.team3316.orchestra.input;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ArrayList;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;

import org.apache.commons.numbers.fraction.Fraction;

import com.team3316.orchestra.Piece;
import com.team3316.orchestra.pitch.Accidental;
import com.team3316.orchestra.pitch.Diatonic;
import com.team3316.orchestra.pitch.NamedNote;
import com.team3316.orchestra.time.Tempo;
import com.team3316.orchestra.time.Timed;
import com.team3316.orchestra.tuning.TuningSystem;
import com.team3316.orchestra.tuning.WellTemperament;
import com.team3316.orchestra.tuning.temperament.Equal24;
import com.team3316.orchestra.voice.Voice;

/**
 * Utility class for creating pieces out of MIDI files.
 */
public final class MidiInput {
    private static final WellTemperament DEFAULT_TUNING_SYSTEM = new Equal24();
    private static final Fraction QUARTER = Fraction.of(1, 4);

    // This is not a declared constant in MetaMessage
    // I don't know why
    private static final int SET_TEMPO = 0x51;

    // Very long but also not that complicated
    private static Piece fromSequence(Sequence sequence, TuningSystem sys) {
        Tempo tempo;
        Fraction tick;

        // If we have a tick-per-frame type, set the tempo so that
        // a frame = 1/4.
        final var divType = sequence.getDivisionType();
        if (divType == Sequence.SMPTE_24)
            tempo = new Tempo(QUARTER, Fraction.of(24));
        else if (divType == Sequence.SMPTE_25)
            tempo = new Tempo(QUARTER, Fraction.of(25));
        else if (divType == Sequence.SMPTE_30DROP)
            tempo = new Tempo(QUARTER, Fraction.of(2997, 100));
        else if (divType == Sequence.SMPTE_30)
            tempo = new Tempo(QUARTER, Fraction.of(30));
        else if (divType == Sequence.PPQ)
            tempo = null; // We'll later set the tempo correctly
        else throw new IllegalStateException();

        // Now the resolution is always tick/quarter
        tick = QUARTER.divide(sequence.getResolution());

        final var voices = new ArrayList<Voice>(sequence.getTracks().length);

        // We don't support overlapping notes/polyphony in one track
        for (final var track : sequence.getTracks()) {
            final var builder = new Voice.NoteBuilder(sys);
            var inNote = false;
            var key = 0;
            long lastEvent = 0; // tick of last event

            // Iterate over events in the track
            for (int i = 0; i < track.size(); i++) {
                final var event = track.get(i);
                final var message = event.getMessage();

                // Tempo message
                // (but don't set it more than once)
                if (tempo == null && message instanceof MetaMessage mm && mm.getType() == SET_TEMPO)
                    tempo = readTempoMessage(mm);

                // Truly ungodly nesting
                if (message instanceof ShortMessage sm) {
                    // Some systems (Lilypond…) use NOTE_ON with velocity 0 to denote NOTE_OFF
                    // for some reason
                    if (sm.getCommand() == ShortMessage.NOTE_OFF || (sm.getCommand() == ShortMessage.NOTE_ON && sm.getData2() == 0)) {
                        if (!inNote) {
                            System.err.println("NOTE_OFF without NOTE_ON");
                            continue;
                        }

                        if (sm.getData1() != key) {
                            System.err.println("NOTE_OFF for different key");
                            continue;
                        }

                        inNote = false;
                        builder.add(Timed.of(decipherKey(key), tick.multiply((int) (event.getTick() - lastEvent))));
                        lastEvent = event.getTick();
                    } else if (sm.getCommand() == ShortMessage.NOTE_ON) {
                        if (inNote) {
                            System.err.println("Successive NOTE_ON events");
                            continue;
                        }

                        inNote = true;
                        // The time between NOTE_OFF and NOTE_ON is a rest
                        if (lastEvent > 0 && lastEvent < event.getTick())
                            builder.add(Timed.rest(tick.multiply((int) (event.getTick() - lastEvent))));
                        lastEvent = event.getTick();
                        key = sm.getData1();
                    }
                }
            }

            final var voice = builder.build();
            if (voice.frequencies().length > 0)
                voices.add(voice);
        }

        // Default tempo of 120BPM
        if (tempo == null)
            tempo = new Tempo(QUARTER, Tempo.BPM.of(120));

        return new Piece(tempo, voices);
    }

    private static Tempo readTempoMessage(MetaMessage mm) {
        assert mm.getType() == SET_TEMPO;
        // microseconds per quarter
        var mcsPerQ = new BigInteger(1, mm.getData());
        return new Tempo(
            QUARTER,
            // oh yes
            // ✨ precision ✨
            Fraction.from(BigDecimal.valueOf(1_000_000L).divide(new BigDecimal(mcsPerQ), RoundingMode.HALF_UP).doubleValue())
        );
    }

    // MIDI only has 12 keys :(
    private static NamedNote decipherKey(int key) {
        final Diatonic name;
        Accidental accidental = Accidental.NATURAL;
        // miserēre meī Deus
        // quod peccāvī
        switch (key % 12) {
            case 1:
                accidental = Accidental.SHARP;
            case 0:
                name = Diatonic.C;
                break;
            case 3:
                accidental = Accidental.SHARP;
            case 2:
                name = Diatonic.D;
                break;
            case 4:
                name = Diatonic.E;
                break;
            case 6:
                accidental = Accidental.SHARP;
            case 5:
                name = Diatonic.F;
                break;
            case 8:
                accidental = Accidental.SHARP;
            case 7:
                name = Diatonic.G;
                break;
            case 10:
                accidental = Accidental.SHARP;
            case 9:
                name = Diatonic.A;
                break;
            case 11:
                name = Diatonic.B;
                break;
            default:
                throw new IllegalStateException();
        }

        return NamedNote.of(name, accidental, (key / 12) - 4);
    }

    /**
     * Read a piece from a MIDI file and tune it using the given {@link WellTemperament}.
     * @param name File name
     * @param sys Tuning system (12-tone)
     * @return The piece (if successful)
     * @throws InvalidMidiDataException See {@link MidiSystem#getSequence(File)}
     * @throws IOException See {@link MidiSystem#getSequence(File)}
     */
    public static Piece open(String name, WellTemperament sys) throws InvalidMidiDataException, IOException {
        return fromSequence(MidiSystem.getSequence(new File(name)), sys);
    }

    /**
     * Read a piece from a MIDI file and tune it according to 12TET.
     * @param name File name
     * @return Piece (if successful)
     * @throws InvalidMidiDataException See {@link MidiSystem#getSequence(File)}
     * @throws IOException See {@link MidiSystem#getSequence(File)}
     */
    public static Piece open(String name) throws InvalidMidiDataException, IOException {
        return open(name, DEFAULT_TUNING_SYSTEM);
    }

    /**
     * Read a piece from a MIDI file and tune it using the given {@link WellTemperament}.
     * @param file File pointer
     * @param sys Tuning system (12-tone)
     * @return The piece (if successful)
     * @throws InvalidMidiDataException See {@link MidiSystem#getSequence(File)}
     * @throws IOException See {@link MidiSystem#getSequence(File)}
     */
    public static Piece open(File file, WellTemperament sys) throws InvalidMidiDataException, IOException {
        return fromSequence(MidiSystem.getSequence(file), sys);
    }

    /**
     * Read a piece from a MIDI file and tune it according to 12TET.
     * @param file File pointer
     * @return Piece (if successful)
     * @throws InvalidMidiDataException See {@link MidiSystem#getSequence(File)}
     * @throws IOException See {@link MidiSystem#getSequence(File)}
     */
    public static Piece open(File file) throws InvalidMidiDataException, IOException {
        return open(file, DEFAULT_TUNING_SYSTEM);
    }

    /**
     * Read a piece from a MIDI file over URL and tune it using the given {@link WellTemperament}.
     * @param url URL
     * @param sys Tuning system (12-tone)
     * @return The piece (if successful)
     * @throws InvalidMidiDataException See {@link MidiSystem#getSequence(File)}
     * @throws IOException See {@link MidiSystem#getSequence(File)}
     */
    public static Piece open(URL url, WellTemperament sys) throws InvalidMidiDataException, IOException {
        return fromSequence(MidiSystem.getSequence(url), sys);
    }

    /**
     * Read a piece from a MIDI file over URL and tune it according to 12TET.
     * @param url URL
     * @return Piece (if successful)
     * @throws InvalidMidiDataException See {@link MidiSystem#getSequence(File)}
     * @throws IOException See {@link MidiSystem#getSequence(File)}
     */
    public static Piece open(URL url) throws InvalidMidiDataException, IOException {
        return open(url, DEFAULT_TUNING_SYSTEM);
    }

    /**
     * Read a piece from an input stream and tune it using the given {@link WellTemperament}.
     * @param stream Input stream
     * @param sys Tuning system (12-tone)
     * @return The piece (if successful)
     * @throws InvalidMidiDataException See {@link MidiSystem#getSequence(File)}
     * @throws IOException See {@link MidiSystem#getSequence(File)}
     */
    public static Piece open(InputStream stream, WellTemperament sys) throws InvalidMidiDataException, IOException {
        return fromSequence(MidiSystem.getSequence(stream), sys);
    }

    /**
     * Read a piece from an input stream and tune it according to 12TET.
     * @param stream Input stream
     * @return Piece (if successful)
     * @throws InvalidMidiDataException See {@link MidiSystem#getSequence(File)}
     * @throws IOException See {@link MidiSystem#getSequence(File)}
     */
    public static Piece open(InputStream stream) throws InvalidMidiDataException, IOException {
        return open(stream, DEFAULT_TUNING_SYSTEM);
    }

    private MidiInput() {}
}
