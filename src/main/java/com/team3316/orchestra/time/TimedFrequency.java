package com.team3316.orchestra.time;

import java.io.Serializable;

import org.apache.commons.numbers.fraction.Fraction;

/**
 * Like {@link Timed}, but for {@code double}.
 * @param frequency Frequency (in Hertz)
 * @param duration Note value
 */
public record TimedFrequency(double frequency, Fraction duration) implements Serializable {
    public TimedFrequency {
        if (duration.compareTo(Fraction.ZERO) <= 0)
            throw new IllegalArgumentException("Negative duration");
    }
}
