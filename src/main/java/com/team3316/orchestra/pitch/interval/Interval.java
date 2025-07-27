package com.team3316.orchestra.pitch.interval;

import org.jetbrains.annotations.NotNull;

/**
 * An interval.
 * 
 * @param ordinal The name of the interval (positive, 1 for unison)
 * @param discriminator The kind of interval
 */
public record Interval(int ordinal, @NotNull IntervalDiscriminator discriminator) {
    public Interval {
        if (ordinal < 1)
            throw new IllegalArgumentException("Non-positive ordinal: " + ordinal);

        if (!discriminator.isApplicableTo(ordinal))
            throw new IllegalArgumentException("Illegal interval: " + ordinal + "/" + discriminator);
    }

    /**
     * Reduce octaves.
     * 
     * @return Ordinal with octaves removed.
     */
    public int normalizedOrdinal() {
        return normalizedOrdinal(ordinal);
    }

    /**
     * Count octaves.
     * 
     * @return The amount of additional octaves induced.
     */
    public int octaves() {
        return octaves(ordinal);
    }

    /**
     * Compute the amount of half-steps induced by the normalized version of this interval.
     * <p>
     * This corresponds to the concept of a <i>semitone</i> in 12TET.
     * Other tuning systems typically don't have a direct equivalent, but might
     * still make use of this value.
     * @return Amount of half-steps induced by the normalized interval.
     */
    public int halfsteps() {
        return IntervalDiscriminator.PERFECT.isApplicableTo(ordinal())
            ? expectedPerfectOffset(this)
            : expectedImperfectOffset(this);
    }

    /**
     * Reduce compound ordinals.
     * 
     * @param ordinal Original ordinal
     * @return Positive ordinal between 1 and 7
     */
    public static int normalizedOrdinal(int ordinal) {
        if (ordinal == 0) return 0;
        return ((Math.abs(ordinal) - 1) % NUM_INTERVALS) + 1;
    }

    /**
     * Compute how many octaves this interval induces.
     * 
     * @param ordinal Compound ordinal
     * @return Amount of octaves
     */
    public static int octaves(int ordinal) {
        if (ordinal == 0) return 0;
        return (Math.abs(ordinal) - 1) / NUM_INTERVALS;
    }

    /**
     * Number of interval names smaller than the octave.
     */
    public static final int NUM_INTERVALS = 7;

    private static int expectedPerfectOffset(final Interval interval) {
        // Find the perfect offset
        final var perfectOffset = switch (interval.normalizedOrdinal()) {
            case 1 -> 0;
            case 4 -> 5;
            case 5 -> 7;
            default -> throw new IllegalArgumentException("Tried to extract perfect offset of imperfect interval");
        };

        return perfectOffset + interval.discriminator.halfstepsFromPerfect();
    }

    private static int expectedImperfectOffset(final Interval interval) {
        // Find the major offset
        final var majorOffset = switch (interval.normalizedOrdinal()) {
            case 2 -> 2;
            case 3 -> 4;
            case 6 -> 9;
            case 7 -> 11;
            default -> throw new IllegalArgumentException("Tried to extract major offset of perfect interval");
        };
        
        return majorOffset + interval.discriminator.halfstepsFromMajor();
    }
}
