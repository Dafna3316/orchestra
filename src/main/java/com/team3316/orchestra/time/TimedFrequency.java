package com.team3316.orchestra.time;

import org.apache.commons.numbers.fraction.Fraction;

import com.team3316.orchestra.pitch.Pitch;

public record TimedFrequency(double frequency, Fraction duration) {
    public TimedFrequency {
        if (duration.compareTo(Fraction.ZERO) <= 0)
            throw new IllegalArgumentException("Negative duration");
    }

    public static TimedFrequency of(Timed<Pitch> tp) {
        return Timed.toFrequency(tp);
    }
}
