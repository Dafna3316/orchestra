package com.team3316.orchestra.pitch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.apache.commons.numbers.fraction.Fraction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AccidentalTest {
    @Test
    @DisplayName("All valid fractions")
    void allFractions() {
        assertEquals(Accidental.FLAT, Accidental.byHalfsteps(Fraction.of(-1)));
        assertEquals(Accidental.SHARP, Accidental.byHalfsteps(Fraction.of(1)));
        assertEquals(Accidental.DOUBLE_FLAT, Accidental.byHalfsteps(Fraction.of(-2)));
        assertEquals(Accidental.DOUBLE_SHARP, Accidental.byHalfsteps(Fraction.of(2)));
        assertEquals(Accidental.HALF_FLAT, Accidental.byHalfsteps(Fraction.of(1, -2)));
        assertEquals(Accidental.HALF_SHARP, Accidental.byHalfsteps(Fraction.of(1, 2)));
        assertEquals(Accidental.SESQUIFLAT, Accidental.byHalfsteps(Fraction.of(-3, 2)));
        assertEquals(Accidental.SESQUISHARP, Accidental.byHalfsteps(Fraction.of(3, 2)));
    }

    @Test
    @DisplayName("Invalid fractions")
    void invalidFractions() {
        assertNull(Accidental.byHalfsteps(Fraction.of(4, 3)));
    }
}
