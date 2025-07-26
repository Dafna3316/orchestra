package com.team3316.orchestra.pitch;

import org.apache.commons.numbers.fraction.Fraction;
import org.jetbrains.annotations.Nullable;

/**
 * Accidental that can be applied to a {@link Diatonic note name}.
 */
public enum Accidental {
    /**
     * ♭ (1 semitone below)
     */
    FLAT(Fraction.of(-1)),

    /**
     * ♯ (1 semitone above)
     */
    SHARP(Fraction.of(1)),

    /**
     * 𝄫 (2 semitones below)
     */
    DOUBLE_FLAT(Fraction.of(-2)),

    /**
     * 𝄪 (2 semitones above)
     */
    DOUBLE_SHARP(Fraction.of(2)),

    /**
     * Half flat (1/2 semitone below)
     */
    HALF_FLAT(Fraction.of(-1, 2)),

    /**
     * Half sharp (1/2 semitone above)
     */
    HALF_SHARP(Fraction.of(1, 2)),

    /**
     * Sesquiflat (1 + 1/2 semitones below)
     */
    SESQUIFLAT(Fraction.of(-3, 2)),

    /**
     * Sesquisharp (1 + 1/2 semitones above)
     */
    SESQUISHARP(Fraction.of(3, 2));

    /**
     * Amount of half-steps that this accidental induces over the base note
     * name.
     * <p>
     * This corresponds to the concept of a <i>semitone</i> in 12TET.
     * Other tuning systems typically don't have a direct equivalent, but might
     * still make use of this value.
     */
    public final Fraction halfsteps;

    private Accidental(final Fraction semitones) {
        this.halfsteps = semitones;
    }

    /**
     * Find an accidental by its induced {@link #halfsteps}.
     * 
     * @param halfsteps Amount of half steps to look for
     * @return Matching accidental, or null
     */
    public static @Nullable Accidental byHalfsteps(Fraction halfsteps) {
        for (Accidental a : values()) {
            if (a.halfsteps.equals(halfsteps)) return a;
        }
        return null;
    }
}
