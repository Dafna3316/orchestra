package com.team3316.orchestra.pitch.interval;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class IntervalDiscriminatorTest {
    @Test
    @DisplayName("Imperfect on imperfect")
    void imperfectOnImperfect() {
        assertTrue(IntervalDiscriminator.AUGMENTED.isApplicableTo(3));
        assertTrue(IntervalDiscriminator.MAJOR.isApplicableTo(-3));
        assertTrue(IntervalDiscriminator.MINOR.isApplicableTo(6));
    }

    @Test
    @DisplayName("Imperfect on perfect")
    void imperfectOnPerfect() {
        assertFalse(IntervalDiscriminator.MAJOR.isApplicableTo(5));
        assertFalse(IntervalDiscriminator.MINOR.isApplicableTo(4));
    }

    @Test
    @DisplayName("Perfect on imperfect")
    void perfectOnImperfect() {
        assertFalse(IntervalDiscriminator.PERFECT.isApplicableTo(3));
        assertFalse(IntervalDiscriminator.PERFECT.isApplicableTo(2));
    }

    @Test
    @DisplayName("Perfect on perfect")
    void perfectOnPerfect() {
        assertTrue(IntervalDiscriminator.DIMINISHED.isApplicableTo(5));
        assertTrue(IntervalDiscriminator.PERFECT.isApplicableTo(1));
        assertTrue(IntervalDiscriminator.PERFECT.isApplicableTo(4));
        assertTrue(IntervalDiscriminator.PERFECT.isApplicableTo(8));
    }
}
