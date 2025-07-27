package com.team3316.orchestra.pitch.interval;

import org.jetbrains.annotations.ApiStatus;

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
    DIMINISHED,

    // The following are internal and shouldn't be used
    // They represent additional unnamed intervals, up to the largest possible
    // augmentation/diminution, which is like the interval between C𝄫 and D𝄪.
    //
    // They're only defined in order to make NamedNote#intervalTo total.
    // note that because of this, NamedNote#up and NamedNote#down aren't total.
    // TODO: replace this enum-based system with an int-based system
    /** @hidden */
    @ApiStatus.Internal _AUGMENTED2,
    /** @hidden */
    @ApiStatus.Internal _AUGMENTED3,
    /** @hidden */
    @ApiStatus.Internal _AUGMENTED4,
    /** @hidden */
    @ApiStatus.Internal _DIMINISHED2,
    /** @hidden */
    @ApiStatus.Internal _DIMINISHED3,
    /** @hidden */
    @ApiStatus.Internal _DIMINISHED4;

    /**
     * Check if this discriminator is applicable to a given interval name.
     * 
     * @param ordinal Interval ordinal name
     * @return Whether this discriminator is applicable
     */
    public boolean isApplicableTo(final int ordinal) {
        switch (this) {
            case PERFECT, MAJOR, MINOR: break;
            default: return true;
        }

        return switch (Interval.normalizedOrdinal(ordinal)) {
            case 1, 4, 5 -> this == PERFECT;
            default -> this != PERFECT;
        };
    }

    @ApiStatus.Internal
    public int halfstepsFromPerfect() {
        return switch (this) {
            case PERFECT -> 0;
            case AUGMENTED -> 1;
            case _AUGMENTED2 -> 2;
            case _AUGMENTED3 -> 3;
            case _AUGMENTED4 -> 4;
            case DIMINISHED -> -1;
            case _DIMINISHED2 -> -2;
            case _DIMINISHED3 -> -3;
            case _DIMINISHED4 -> -4;
            case MAJOR, MINOR -> throw new UnsupportedOperationException();
        };
    }

    @ApiStatus.Internal
    public static IntervalDiscriminator byHalfstepsFromPerfect(int halfsteps) {
        return switch (halfsteps) {
            case 0 -> PERFECT;
            case 1 -> AUGMENTED;
            case 2 -> _AUGMENTED2;
            case 3 -> _AUGMENTED3;
            case 4 -> _AUGMENTED4;
            case -1 -> DIMINISHED;
            case -2 -> _DIMINISHED2;
            case -3 -> _DIMINISHED3;
            case -4 -> _DIMINISHED4;
            default -> throw new UnsupportedOperationException();
        };
    }

    @ApiStatus.Internal
    public int halfstepsFromMajor() {
        return switch (this) {
            case MAJOR -> 0;
            case MINOR -> -1;
            case AUGMENTED -> 1;
            case _AUGMENTED2 -> 2;
            case _AUGMENTED3 -> 3;
            case _AUGMENTED4 -> 4;
            case DIMINISHED -> -2;
            case _DIMINISHED2 -> -3;
            case _DIMINISHED3 -> -4;
            case _DIMINISHED4 -> -5;
            case PERFECT -> throw new UnsupportedOperationException();
        };
    }

    @ApiStatus.Internal
    public static IntervalDiscriminator byHalfstepsFromMajor(int halfsteps) {
        return switch (halfsteps) {
            case 0 -> MAJOR;
            case -1 -> MINOR;
            case 1 -> AUGMENTED;
            case 2 -> _AUGMENTED2;
            case 3 -> _AUGMENTED3;
            case 4 -> _AUGMENTED4;
            case -2 -> DIMINISHED;
            case -3 -> _DIMINISHED2;
            case -4 -> _DIMINISHED3;
            case -5 -> _DIMINISHED4;
            default -> throw new UnsupportedOperationException();
        };
    }
}
