package com.team3316.orchestra.tuning.temperament;

import java.io.Serializable;

import org.apache.commons.numbers.fraction.Fraction;
import org.jetbrains.annotations.NotNull;

import com.team3316.orchestra.pitch.Diatonic;
import com.team3316.orchestra.pitch.NamedNote;
import com.team3316.orchestra.pitch.Pitch;
import com.team3316.orchestra.tuning.WellTemperament;

/**
 * Kirnberger III temperament on a given base note.
 * @param referenceNote Base note name of the system
 * @param referencePitch Pitch that the base note should be mapped to
 */
public record Kirnberger3(NamedNote referenceNote, Pitch referencePitch) implements WellTemperament, Serializable {
    @Override
    public @NotNull Pitch byHalfsteps(Pitch base, int halfsteps) {
        return switch (halfsteps) {
            case 0 -> base;
            case 1 -> base.upRatio(Fraction.of(256, 243));
            case 2 -> base.rawMultiply(Math.sqrt(5)).upRatio(Fraction.of(1, 2));
            case 3 -> base.upRatio(Fraction.of(32, 27));
            case 4 -> base.upRatio(Fraction.of(5, 4));
            case 5 -> base.upRatio(Fraction.of(4, 3));
            case 6 -> base.upRatio(Fraction.of(45, 32));
            case 7 -> base.rawMultiply(Math.sqrt(Math.sqrt(5)));
            case 8 -> base.upRatio(Fraction.of(128, 81));
            case 9 -> base.rawMultiply(1 / Math.sqrt(Math.sqrt(5))).upRatio(Fraction.of(5, 2));
            case 10 -> base.upRatio(Fraction.of(16, 9));
            case 11 -> base.upRatio(Fraction.of(15, 8));
            default -> throw new IllegalStateException("Half steps not between 0 and 12");
        };
    }

    public Kirnberger3 {
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
