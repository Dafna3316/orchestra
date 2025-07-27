package com.team3316.orchestra.time;

import org.apache.commons.numbers.fraction.Fraction;

import com.team3316.orchestra.pitch.Pitch;

public record TimedPitch(Pitch pitch, Fraction duration) {
    public TimedPitch {
        if (duration.compareTo(Fraction.ZERO) <= 0)
            throw new IllegalArgumentException("Negative duration");
    }

    public TimedFrequency toTimedFrequency() {
        return new TimedFrequency(pitch.frequency(), duration);
    }
}
