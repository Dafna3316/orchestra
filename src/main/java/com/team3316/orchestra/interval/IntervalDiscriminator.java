package com.team3316.orchestra.interval;

public enum IntervalDiscriminator {
    PERFECT,
    MAJOR,
    MINOR,
    AUGMENTED,
    DIMINISHED;

    boolean isApplicableTo(final int ordinal) {
        if (this == AUGMENTED || this == DIMINISHED) return true;

        return switch (((ordinal) - 1 % Interval.NUM_INTERVALS) + 1) {
            case 1, 4, 5 -> this == PERFECT;
            default -> this != PERFECT;
        };
    }
}
