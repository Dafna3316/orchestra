package com.team3316.orchestra;

import org.apache.commons.numbers.fraction.Fraction;

/**
 * Accidental that can be applied to a {@link Diatonic note name}.
 */
public enum Accidental {
    /**
     * Flat (1 semitone below)
     */
    FLAT(Fraction.of(-1)),

    /**
     * Sharp (1 semitone above)
     */
    SHARP(Fraction.of(1)),

    /**
     * Half flat (1/2 semitone below)
     */
    HALF_FLAT(Fraction.of(-1, 2)),

    /**
     * Half sharp (1/2 semitone above)
     */
    HALF_SHARP(Fraction.of(1, 2)),

    /**
     * Flat-and-a-half (1 + 1/2 semitones below)
     */
    FLAT_AND_A_HALF(Fraction.of(-3, 2)),

    /**
     * Sharp-and-a-half (1 + 1/2 semitones above)
     */
    SHARP_AND_A_HALF(Fraction.of(3, 2));

    /**
     * Amount of semitones that this accidental induces over the basic diatonic
     * pitch, in 12TET.
     */
    public final Fraction semitones;

    private Accidental(Fraction semitones) {
        this.semitones = semitones;
    }
}
