package com.team3316.orchestra;

import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.lang3.NotImplementedException;

import com.ctre.phoenix6.controls.MusicTone;
import com.ctre.phoenix6.hardware.TalonFX;
import com.team3316.orchestra.time.Tempo;
import com.team3316.orchestra.time.Voice;

import edu.wpi.first.units.Units;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;

public record Piece(Tempo tempo, Collection<Voice> voices) {
    public Command playEagerly(Orchestra orchestra) {
        verifyOrchestra(orchestra);
        return new EagerPlayCommand(
            voices.stream()
                .map(v -> voiceToDTFs(v, tempo))
                .toArray(i -> new DirectlyTimedFrequency[i][]),
            orchestra
        );
    }

    public Command playLazily(Orchestra orchestra) {
        verifyOrchestra(orchestra);
        throw new NotImplementedException();
    }

    private void verifyOrchestra(Orchestra orchestra) {
        if (orchestra.getPlayers().length < voices.size())
            throw new IllegalArgumentException("Orchestra must have as many or more voices than piece");
    }

    private static DirectlyTimedFrequency[] voiceToDTFs(Voice voice, Tempo tempo) {
        final var dtfs = new DirectlyTimedFrequency[voice.frequencies().length + 1];
        double acc = 0;
        for (int i = 0; i < voice.frequencies().length; i++) {
            dtfs[i] = new DirectlyTimedFrequency(voice.frequencies()[i].frequency(), acc);
            acc += voice.frequencies()[i].duration().divide(tempo.duration()).doubleValue() / tempo.frequency().in(Units.Hertz);
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
