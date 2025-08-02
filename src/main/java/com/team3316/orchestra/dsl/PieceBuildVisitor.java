package com.team3316.orchestra.dsl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import org.antlr.v4.runtime.misc.Pair;
import org.apache.commons.numbers.fraction.Fraction;

import com.team3316.orchestra.Piece;
import com.team3316.orchestra.antlr.PieceBaseVisitor;
import com.team3316.orchestra.antlr.PieceParser.DurContext;
import com.team3316.orchestra.antlr.PieceParser.FixedMusicContext;
import com.team3316.orchestra.antlr.PieceParser.NamedNoteContext;
import com.team3316.orchestra.antlr.PieceParser.PieceContext;
import com.team3316.orchestra.antlr.PieceParser.PitchContext;
import com.team3316.orchestra.antlr.PieceParser.RelativeMusicContext;
import com.team3316.orchestra.antlr.PieceParser.StorageDeclContext;
import com.team3316.orchestra.antlr.PieceParser.TempoDeclContext;
import com.team3316.orchestra.antlr.PieceParser.TimedNNContext;
import com.team3316.orchestra.antlr.PieceParser.TimedPitchContext;
import com.team3316.orchestra.antlr.PieceParser.TuningDeclContext;
import com.team3316.orchestra.antlr.PieceParser.VoiceContext;
import com.team3316.orchestra.antlr.PieceParser.VoicesContext;
import com.team3316.orchestra.pitch.Accidental;
import com.team3316.orchestra.pitch.Diatonic;
import com.team3316.orchestra.pitch.NamedNote;
import com.team3316.orchestra.pitch.Pitch;
import com.team3316.orchestra.time.Tempo;
import com.team3316.orchestra.time.Timed;
import com.team3316.orchestra.time.TimedFrequency;
import com.team3316.orchestra.tuning.TuningSystem;
import com.team3316.orchestra.voice.Voice;
import com.team3316.orchestra.voice.VoiceSupplier;

public class PieceBuildVisitor extends PieceBaseVisitor<Object> {
    private static final Map<String, Pair<Diatonic, Accidental>> names;

    private static void addNote(Map<String, Pair<Diatonic, Accidental>> map, String baseName, Diatonic note, boolean contract) {
        map.put(baseName, new Pair<>(note, Accidental.NATURAL));
        map.put(baseName + "es", new Pair<>(note, Accidental.FLAT));
        map.put(baseName + "is", new Pair<>(note, Accidental.SHARP));
        map.put(baseName + "eses", new Pair<>(note, Accidental.DOUBLE_FLAT));
        map.put(baseName + "isis", new Pair<>(note, Accidental.DOUBLE_SHARP));
        map.put(baseName + "eh", new Pair<>(note, Accidental.HALF_FLAT));
        map.put(baseName + "ih", new Pair<>(note, Accidental.HALF_SHARP));
        map.put(baseName + "eheh", new Pair<>(note, Accidental.SESQUIFLAT));
        map.put(baseName + "ihih", new Pair<>(note, Accidental.SESQUISHARP));

        if (contract) {
            map.put(baseName + "s", new Pair<>(note, Accidental.FLAT));
            map.put(baseName + "ses", new Pair<>(note, Accidental.DOUBLE_FLAT));
            map.put(baseName + "h", new Pair<>(note, Accidental.HALF_FLAT));
            map.put(baseName + "heh", new Pair<>(note, Accidental.SESQUIFLAT));
        }
    }

    static {
        var map = new HashMap<String, Pair<Diatonic, Accidental>>(7 * 9);
        addNote(map, "c", Diatonic.C, false);
        addNote(map, "d", Diatonic.D, false);
        addNote(map, "e", Diatonic.E, true);
        addNote(map, "f", Diatonic.F, false);
        addNote(map, "g", Diatonic.G, false);
        addNote(map, "a", Diatonic.A, true);
        addNote(map, "b", Diatonic.B, false);
        names = Collections.unmodifiableMap(map);
    }

    private TuningSystem sys = null;
    private Tempo tempo = new Tempo(Fraction.of(1, 4), Tempo.BPM.of(120));
    private ArrayList<VoiceSupplier> voices = new ArrayList<>();
    private boolean relativeMode = false;
    private NamedNote relativeNote = null;
    private Fraction lastDur = Fraction.of(1, 4);
    private int fixedOctave = 0;
    private Supplier<VoiceSupplier> newForStorage = () -> new Voice.NoteBuilder(sys);

    @Override
    public Piece visitPiece(PieceContext ctx) {
        super.visitPiece(ctx);
        return new Piece(tempo, voices);
    }

    public Void visitTuningDecl(TuningDeclContext ctx) {
        var stringLiteral = ctx.STRING().getText();
        var sys = TuningRegistry.lookup(stringLiteral.substring(1, stringLiteral.length() - 1));
        if (sys == null) {
            try {
                Class<?> clazz = Class.forName(ctx.STRING().getText());
                if (TuningSystem.class.isAssignableFrom(clazz)) {
                    this.sys = (TuningSystem) clazz.getConstructor(NamedNote.class, Pitch.class)
                            .newInstance(visitNamedNote(ctx.namedNote()), visitPitch(ctx.pitch()));
                }
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                e.printStackTrace();
                this.sys = null;
            }
        } else {
            this.sys = sys.construct(visitNamedNote(ctx.namedNote()), visitPitch(ctx.pitch()));
        }

        return null;
    }

    @Override
    public Void visitStorageDecl(StorageDeclContext ctx) {
        if (ctx.storage().NOTE_STORAGE() != null)
            newForStorage = () -> new Voice.NoteBuilder(sys);
        else if (ctx.storage().PITCH_STORAGE() != null)
            newForStorage = Voice.PitchBuilder::new;
        else if (ctx.storage().FREQ_STORAGE() != null)
            newForStorage = Voice.FrequencyBuilder::new;

        return null;
    }

    @Override
    public Object visitTempoDecl(TempoDeclContext ctx) {
        tempo = new Tempo(visitDur(ctx.dur()), Tempo.BPM.of(ctx.number().result));
        return null;
    }

    @Override
    public Object visitVoices(VoicesContext ctx) {
        if (sys == null) {
            System.err.println("No tuning system set");
            return null;
        }

        voices.ensureCapacity(ctx.voice().size());
        return super.visitVoices(ctx);
    }

    @Override
    public Object visitVoice(VoiceContext ctx) {
        voices.add(newForStorage.get());
        relativeMode = false;
        fixedOctave = 0;
        return super.visitVoice(ctx);
    }

    @Override
    public Object visitRelativeMusic(RelativeMusicContext ctx) {
        relativeMode = true;
        if (ctx.namedNote() != null) {
            relativeNote = visitNamedNote(ctx.namedNote());
        } else {
            relativeNote = null;
        }

        return super.visitRelativeMusic(ctx);
    }

    @Override
    public Object visitFixedMusic(FixedMusicContext ctx) {
        relativeMode = false;
        if (ctx.namedNote() != null) {
            fixedOctave = ctx.namedNote().octave().result;
        } else {
            fixedOctave = 0;
        }

        return super.visitFixedMusic(ctx);
    }

    @Override
    public Void visitTimedPitch(TimedPitchContext ctx) {
        var pitch = Timed.of(visitPitch(ctx.pitch()), ctx.dur() == null ? lastDur : visitDur(ctx.dur()));
        var latestVoice = voices.get(voices.size() - 1);
        lastDur = pitch.duration();

        if (latestVoice instanceof Voice.NoteBuilder nb) {
            System.err.println("Warning: specializing storage to Pitches for this voice");
            var pb = nb.pitches();
            pb.add(Timed.of(pitch.pitch(), pitch.duration()));
            voices.set(voices.size() - 1, pb);
        } else if (latestVoice instanceof Voice.PitchBuilder pb) {
            pb.add(pitch);
        } else if (latestVoice instanceof Voice.FrequencyBuilder fb) {
            fb.add(new TimedFrequency(pitch.pitch().frequency(), pitch.duration()));
        } else {
            System.err.println("Warning: Voice found while building");
        }

        return null;
    }

    @Override
    public Void visitTimedNN(TimedNNContext ctx) {
        var dur = ctx.dur() == null ? lastDur : visitDur(ctx.dur());
        var raw = visitNamedNote(ctx.namedNote());
        var newOctave = raw.octave();
        if (relativeMode && relativeNote != null) {
            var diff = raw.name().ordinal() - relativeNote.name().ordinal();
            newOctave += relativeNote.octave();
            if (Math.abs(diff) >= 4) {
                if (diff > 0) newOctave--;
                else newOctave++;
            }
        } else {
            newOctave += fixedOctave;
        }

        lastDur = dur;
        var timed = Timed.of(NamedNote.of(raw.name(), raw.accidental(), newOctave), dur);
        if (relativeMode) relativeNote = timed.pitch();
        var latestVoice = voices.get(voices.size() - 1);

        if (latestVoice instanceof Voice.NoteBuilder nb) nb.add(timed);
        else if (latestVoice instanceof Voice.PitchBuilder pb)
            pb.add(Timed.of(sys.interpret(timed.pitch()), timed.duration()));
        else if (latestVoice instanceof Voice.FrequencyBuilder fb)
            fb.add(new TimedFrequency(sys.interpret(timed.pitch()).frequency(), timed.duration()));
        else System.err.println("Warning: Voice found while building");

        return null;
    }

    @Override
    public NamedNote visitNamedNote(NamedNoteContext ctx) {
        final var pair = names.get(ctx.NOTE_NAME().getText());
        return NamedNote.of(pair.a, pair.b, ctx.octave().result);
    }

    @Override
    public Pitch visitPitch(PitchContext ctx) {
        return ctx.result;
    }

    @Override
    public Fraction visitDur(DurContext ctx) {
        return ctx.result;
    }
}
