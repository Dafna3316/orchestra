package com.team3316.orchestra.tuning.temperament;

import org.apache.commons.numbers.fraction.Fraction;
import org.jetbrains.annotations.NotNull;

import com.team3316.orchestra.pitch.Diatonic;
import com.team3316.orchestra.pitch.NamedNote;
import com.team3316.orchestra.pitch.Pitch;
import com.team3316.orchestra.tuning.TuningSystem;

/**
 * Kirnberger III temperament on a given base note.
 */
public class Kirnberger3 implements TuningSystem {
    private final NamedNote referenceNote;
    private final Pitch referencePitch;

    @Override
    public @NotNull Pitch interpret(@NotNull NamedNote note) {
        if (note.accidental().halfsteps.abs().getDenominator() != 1) {
            throw new UnsupportedOperationException("Kirnberger III can't interpret half-accidentals");
        }

        var targetHalfsteps = referenceNote.halfstepsTo(note).intValue();
        var octaves = targetHalfsteps / 12;
        targetHalfsteps -= 12 * octaves;
        if (targetHalfsteps < 0) {
            targetHalfsteps += 12;
            octaves--;
        }

        // Kirnberger is a 12-tone temperament
        final var basePitch = switch (targetHalfsteps) {
            case 0 -> referencePitch;
            case 1 -> referencePitch.upRatio(Fraction.of(256, 243));
            case 2 -> referencePitch.rawMultiply(Math.sqrt(5)).upRatio(Fraction.of(1, 2));
            case 3 -> referencePitch.upRatio(Fraction.of(32, 27));
            case 4 -> referencePitch.upRatio(Fraction.of(5, 4));
            case 5 -> referencePitch.upRatio(Fraction.of(4, 3));
            case 6 -> referencePitch.upRatio(Fraction.of(45, 32));
            case 7 -> referencePitch.rawMultiply(Math.sqrt(Math.sqrt(5)));
            case 8 -> referencePitch.upRatio(Fraction.of(128, 81));
            case 9 -> referencePitch.rawMultiply(1 / Math.sqrt(Math.sqrt(5))).upRatio(Fraction.of(5, 2));
            case 10 -> referencePitch.upRatio(Fraction.of(16, 9));
            case 11 -> referencePitch.upRatio(Fraction.of(15, 8));
            default -> throw new IllegalStateException("Half steps not between 0 and 12");
        };

        return basePitch.upRatio(Fraction.of(2).pow(octaves));
    }

    /**
     * Construct a Kirnberger III temperament on a given base note.
     *
     * @param referenceNote Base note name of the system
     * @param referencePitch Pitch that the base note should be mapped to
     */
    public Kirnberger3(NamedNote referenceNote, Pitch referencePitch) {
        this.referenceNote = referenceNote;
        this.referencePitch = referencePitch;

        if (referenceNote.accidental().halfsteps.abs().getDenominator() != 1) {
            throw new UnsupportedOperationException("Kirnberger III can't interpret half-accidentals");
        }
    }

    /**
     * Construct a Kirnberger III based on middle C at 264Hz.
     */
    public Kirnberger3() {
        this(NamedNote.of(Diatonic.C), Pitch.of(440.0).upRatio(Fraction.of(6, 10)));
    }
}
