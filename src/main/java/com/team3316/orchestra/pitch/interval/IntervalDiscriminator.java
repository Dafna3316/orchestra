package com.team3316.orchestra.pitch.interval;

/**
 * Pefect, major, minor, augmented and diminished intervals.
 */
public enum IntervalDiscriminator {
    /**
     * Perfect (for unisons, fourths, fifth, and compounds thereof)
     */
    PERFECT,

    /**
     * Major (for seconds, thirds, sixths, sevenths and compounds thereof)
     */
    MAJOR,

    /**
     * Minor (for seconds, thirds, sixths, sevenths and compounds thereof)
     */
    MINOR,

    /**
     * Augmented (always applicable)
     */
    AUGMENTED,

    /**
     * Diminished (always applicable)
     */
    DIMINISHED;

    /**
     * Check if this discriminator is applicable to a given interval name.
     * 
     * @param ordinal Interval ordinal name
     * @return Whether this discriminator is applicable
     */
    public boolean isApplicableTo(final int ordinal) {
        if (this == AUGMENTED || this == DIMINISHED) return true;

        return switch (Interval.normalizedOrdinal(ordinal)) {
            case 1, 4, 5 -> this == PERFECT;
            default -> this != PERFECT;
        };
    }
}
