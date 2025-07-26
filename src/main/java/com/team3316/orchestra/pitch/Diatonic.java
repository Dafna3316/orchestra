package com.team3316.orchestra.pitch;

/**
 * A diatonic note name: a note of the scale.
 */
public enum Diatonic {
    // C is used as a base, which is weird since the pitch standard is A.

    /**
     * C/Do
     */
    C(0),

    /**
     * D/Re
     */
    D(2),

    /**
     * E/Mi
     */
    E(4),

    /**
     * F/Fa
     */
    F(5),

    /**
     * G/Sol/So
     */
    G(7),

    /**
     * A/La
     */
    A(9),

    /**
     * B/H/Si/Ti
     */
    B(11);

    /**
     * Number of halfsteps above {@link #C}.
     * <p>
     * This corresponds to the concept of a <i>semitone</i> in 12TET.
     * Other tuning systems typically don't have a direct equivalent, but might
     * still make use of this value.
     */
    public final int halfsteps;

    private Diatonic(int halfsteps) {
        this.halfsteps = halfsteps;
    }

    /**
     * Find a diatonic note name by its ordinal.
     * <p>
     * Negative values and values with a greater magnitude than 7 are handled.
     * 
     * @param ordinal Target ordinal
     * @return Corresponding diatonic note name
     */
    public static Diatonic byOrdinal(int ordinal) {
        final var values = values();
        ordinal %= values.length;
        if (ordinal < 0) ordinal += values.length;
        return values[ordinal];
    }

    /**
     * Alias for {@link #C}.
     */
    public static final Diatonic DO = C;

    /**
     * Alias for {@link #D}.
     */
    public static final Diatonic RE = D;

    /**
     * Alias for {@link #E}.
     */
    public static final Diatonic MI = E;

    /**
     * Alias for {@link #F}.
     */
    public static final Diatonic FA = F;

    /**
     * Alias for {@link #G}.
     */
    public static final Diatonic SOL = G;

    /**
     * Alias for {@link #G}.
     */
    public static final Diatonic SO = G;

    /**
     * Alias for {@link #A}.
     */
    public static final Diatonic LA = A;

    /**
     * Alias for {@link #B}.
     */
    public static final Diatonic SI = B;

    /**
     * Alias for {@link #B}.
     */
    public static final Diatonic TI = B;
}
