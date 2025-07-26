package com.team3316.orchestra.pitch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DiatonicTest {
    @Test
    @DisplayName("2 -> E")
    void basicOrdinal() {
        assertEquals(Diatonic.E, Diatonic.byOrdinal(2));
    }

    @Test
    @DisplayName("8 -> D")
    void compoundOrdinal() {
        assertEquals(Diatonic.D, Diatonic.byOrdinal(8));
    }

    @Test
    @DisplayName("-2 -> D")
    void negativeOrdinal() {
        assertEquals(Diatonic.A, Diatonic.byOrdinal(-2));
    }
}
