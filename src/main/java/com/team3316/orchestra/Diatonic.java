package com.team3316.orchestra;

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
     * The semitones above {@link #C} that this note induces, in 12TET.
     */
    public final int semitones;

    private Diatonic(int semitones){
        this.semitones = semitones;
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
