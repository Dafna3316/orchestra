package com.team3316.orchestra.interval;

public record Interval(int ordinal, IntervalDiscriminator discriminator) {
    public Interval {
        if (!discriminator.isApplicableTo(ordinal))
            throw new IllegalArgumentException("Illegal interval: " + ordinal + "/" + discriminator);
    }

    public static final int NUM_INTERVALS = 7;
}
