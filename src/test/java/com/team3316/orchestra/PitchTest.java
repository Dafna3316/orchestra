package com.team3316.orchestra;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.commons.numbers.fraction.Fraction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PitchTest {
    @Test
    @DisplayName("Just octave above A440")
    void ratios() {
        Pitch p = Pitch.of(Fraction.of(2, 1));
        assertEquals(880, p.frequency());
    }

    @Test
    @DisplayName("12TET Fifth above A440")
    void fifth() {
        Pitch p = Pitch.of(7);
        assertEquals(440 * Math.pow(2, 7.0/12), p.frequency());
    }
}
