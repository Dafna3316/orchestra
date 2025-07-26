package com.team3316.orchestra.pitch.interval;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class IntervalTest {
    @Test
    @DisplayName("Interval constructor")
    void constructor() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Interval(3, IntervalDiscriminator.PERFECT)
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> new Interval(-3, IntervalDiscriminator.MAJOR)
        );

        assertDoesNotThrow(() -> new Interval(5, IntervalDiscriminator.PERFECT));
        assertDoesNotThrow(() -> new Interval(6, IntervalDiscriminator.MINOR));
    }

    @Test
    @DisplayName("Normalization tests")
    void normalization() {
        for (int i = 1; i <= 7; i++) {
            assertEquals(i, Interval.normalizedOrdinal(i));
        }

        assertEquals(1, Interval.normalizedOrdinal(8));
        assertEquals(6, Interval.normalizedOrdinal(13));
    }

    @Test
    @DisplayName("Interval offset tests")
    void halfsteps() {
        assertEquals(1, new Interval(2, IntervalDiscriminator.MINOR).halfsteps());
        assertEquals(2, new Interval(2, IntervalDiscriminator.MAJOR).halfsteps());
        assertEquals(7, new Interval(5, IntervalDiscriminator.PERFECT).halfsteps());
        assertEquals(6, new Interval(4, IntervalDiscriminator.AUGMENTED).halfsteps());
        assertEquals(9, new Interval(13, IntervalDiscriminator.MAJOR).halfsteps());
    }

    @Test
    @DisplayName("Octave tests")
    void octaves() {
        assertEquals(1, Interval.octaves(8));
        assertEquals(2, Interval.octaves(15));
        assertEquals(2, Interval.octaves(16));
    }
}
