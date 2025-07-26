package com.team3316.orchestra.time;

import org.apache.commons.numbers.fraction.Fraction;

public record TimedNote<P>(P pitch, Fraction duration) {
    public TimedNote {
        if (duration.compareTo(Fraction.ZERO) <= 0) throw new IllegalArgumentException("Non-positive duration");
    }
}
