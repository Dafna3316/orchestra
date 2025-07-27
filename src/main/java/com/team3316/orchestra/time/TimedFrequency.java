package com.team3316.orchestra.time;

import org.apache.commons.numbers.fraction.Fraction;

public record TimedFrequency(double frequency, Fraction duration) {
    public TimedFrequency {
        if (duration.compareTo(Fraction.ZERO) <= 0)
            throw new IllegalArgumentException("Negative duration");
    }

    public static TimedFrequency of(TimedPitch tp) {
        return tp.toTimedFrequency();
    }
}
