package com.team3316.orchestra;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Supplier;

import com.ctre.phoenix6.controls.MusicTone;
import com.ctre.phoenix6.hardware.TalonFX;
import com.team3316.orchestra.time.Tempo;
import com.team3316.orchestra.voice.Voice;
import com.team3316.orchestra.voice.VoiceSupplier;

import edu.wpi.first.units.Units;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * Piece of music.
 * @param tempo Tempo of this piece
 * @param voices Voices of this piece
 */
public record Piece(Tempo tempo, Collection<? extends VoiceSupplier> voices) implements Serializable {
    /**
     * Create a command the plays this piece using {@code orchestra}.
     * Timing is calculated at the time of call.
     * @param orchestra Target orchestra
     * @return Command that plays this piece
     */
    public Command playEagerly(Orchestra orchestra) {
        verifyOrchestra(orchestra);
        return new EagerPlayCommand(
            voices.stream()
                .map(v -> voiceToDTFs(v.get(), tempo))
                .toArray(i -> new DirectlyTimedFrequency[i][]),
            orchestra
        );
    }

    /**
     * Create a command that plays this piece using {@code orchestra}.
     * Timing is calculated as needed.
     * @param orchestra Target orchestra
     * @return Command that plays this piece
     */
    public Command playLazily(Orchestra orchestra) {
        verifyOrchestra(orchestra);
        return new LazyPlayCommand(voices, tempo, orchestra);
    }

    /**
     * Evaluate all {@link VoiceSupplier}s to actual {@link Voice}s.
     * @return New piece with voices evaluated
     */
    public Piece evaluateVoices() {
        return new Piece(tempo, voices.stream().map(Supplier::get).toList());
    }

    private void verifyOrchestra(Orchestra orchestra) {
        if (orchestra.getPlayers().length < voices.size())
            throw new IllegalArgumentException("Orchestra must have as many or more voices than piece");
    }

    // oh yes
    private static DirectlyTimedFrequency[] voiceToDTFs(Voice voice, Tempo tempo) {
        final var dtfs = new DirectlyTimedFrequency[voice.frequencies().length + 1];
        double acc = 0;
        for (int i = 0; i < voice.frequencies().length; i++) {
            dtfs[i] = new DirectlyTimedFrequency(voice.frequencies()[i].frequency(), acc);
            acc += tempo.interpret(voice.frequencies()[i].duration()).in(Units.Seconds);
        }
        dtfs[dtfs.length - 1] = new DirectlyTimedFrequency(0, acc);

        return dtfs;
    }
}

record DirectlyTimedFrequency(double frequency, double start) {}

class EagerPlayCommand extends Command {
    private final Timer metronome;
    private final DirectlyTimedFrequency[][] voices;
    private final TalonFX[] players;
    private int[] progress;

    public EagerPlayCommand(DirectlyTimedFrequency[][] voices, Orchestra orchestra) {
        addRequirements(orchestra);
        this.voices = voices;
        this.players = orchestra.getPlayers();
        this.progress = new int[voices.length];
        this.metronome = new Timer();

        Arrays.fill(progress, 0);
        assert voices.length <= players.length;
    }

    @Override
    public void initialize() {
        metronome.restart();
        Arrays.fill(progress, 0);
        for (final var player : players) {
            player.setControl(new MusicTone(0));
        }
    }

    @Override
    public void execute() {
        int i = 0;
        for (final var voice : voices) {
            if (progress[i] >= voice.length) continue;

            final var note = voice[progress[i]];
            if (note.start() <= metronome.get()) {
                players[i].setControl(new MusicTone(note.frequency()));
                progress[i]++;
            }
            i++;
        }
    }

    @Override
    public boolean isFinished() {
        int i = 0;
        for (final var voice : voices) {
            if (progress[i] < voice.length) return false;
            i++;
        }

        return true;
    }

    @Override
    public void end(boolean interrupted) {
        for (final var player : players) {
            player.setControl(new MusicTone(0));
        }
        metronome.stop();
    }
}

class LazyPlayCommand extends Command {
    private final Timer metronome;
    private final Tempo tempo;
    private final Voice[] voices;
    private final TalonFX[] players;
    private int[] progress; // Indices
    private double[] nextNote; // Seconds

    public LazyPlayCommand(Collection<? extends VoiceSupplier> voices, Tempo tempo, Orchestra orchestra) {
        addRequirements(orchestra);
        this.metronome = new Timer();
        this.tempo = tempo;
        this.voices = voices.stream().map(Supplier::get).toArray(len -> new Voice[len]);
        this.players = orchestra.getPlayers();
        this.progress = new int[this.voices.length];
        this.nextNote = new double[this.voices.length];

        Arrays.fill(progress, 0);
        Arrays.fill(nextNote, 0);
        assert this.voices.length <= players.length;
    }

    @Override
    public void initialize() {
        metronome.restart();
        Arrays.fill(progress, 0);
        Arrays.fill(nextNote, 0);
        for (final var player : players) {
            player.setControl(new MusicTone(0));
        }
    }

    @Override
    public void execute() {
        int i = 0;
        for (final var voice : voices) {
            final var freqs = voice.frequencies();

            if (nextNote[i] <= metronome.get()) {
                if (progress[i] >= freqs.length) {
                    players[i].setControl(new MusicTone(0));
                    continue;
                }

                final var note = freqs[progress[i]];
                players[i].setControl(new MusicTone(note.frequency()));
                progress[i]++;
                nextNote[i] += tempo.interpret(note.duration()).in(Units.Seconds);
            }
            i++;
        }
    }

    @Override
    public boolean isFinished() {
        int i = 0;
        for (final var voice : voices) {
            if (progress[i] <= voice.frequencies().length) return false;
            i++;
        }

        return true;
    }

    @Override
    public void end(boolean interrupted) {
        for (final var player : players) {
            player.setControl(new MusicTone(0));
        }
        metronome.stop();
    }
}
