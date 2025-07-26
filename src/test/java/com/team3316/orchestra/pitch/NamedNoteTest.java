package com.team3316.orchestra.pitch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.team3316.orchestra.pitch.interval.Interval;
import com.team3316.orchestra.pitch.interval.IntervalDiscriminator;

public class NamedNoteTest {
    @Test
    @DisplayName("Up-transposition tests")
    void transposeUp() {
        assertEquals(
            new NamedNote(Diatonic.G, Accidental.NATURAL, 1),
            new NamedNote(Diatonic.C, Accidental.NATURAL, 1)
                .up(new Interval(5, IntervalDiscriminator.PERFECT))
        );

        assertEquals(
            new NamedNote(Diatonic.G, Accidental.FLAT, 1),
            new NamedNote(Diatonic.C, Accidental.NATURAL, 1)
                .up(new Interval(5, IntervalDiscriminator.DIMINISHED))
        );

        assertEquals(
            new NamedNote(Diatonic.F, Accidental.SHARP, 1),
            new NamedNote(Diatonic.C, Accidental.NATURAL, 1)
                .up(new Interval(4, IntervalDiscriminator.AUGMENTED))
        );

        assertEquals(
            new NamedNote(Diatonic.E, Accidental.FLAT, 2),
            new NamedNote(Diatonic.C, Accidental.NATURAL, 1)
                .up(new Interval(10, IntervalDiscriminator.MINOR))
        );

        assertEquals(
            new NamedNote(Diatonic.F, Accidental.SHARP, 1),
            new NamedNote(Diatonic.B, Accidental.NATURAL, 0)
                .up(new Interval(5, IntervalDiscriminator.PERFECT))
        );

        assertEquals(
            new NamedNote(Diatonic.F, Accidental.NATURAL, 1),
            new NamedNote(Diatonic.B, Accidental.FLAT, 0)
                .up(new Interval(5, IntervalDiscriminator.PERFECT))
        );

        assertEquals(
            new NamedNote(Diatonic.F, Accidental.SHARP, 1),
            new NamedNote(Diatonic.B, Accidental.FLAT, 0)
                .up(new Interval(5, IntervalDiscriminator.AUGMENTED))
        );
    }

    @Test
    @DisplayName("Down-transposition tests")
    void transposeDown() {
        assertEquals(
            new NamedNote(Diatonic.B, Accidental.NATURAL, 0),
            new NamedNote(Diatonic.F, Accidental.SHARP, 1)
                .down(new Interval(5, IntervalDiscriminator.PERFECT))
        );

        assertEquals(
            new NamedNote(Diatonic.D, Accidental.SHARP, 1),
            new NamedNote(Diatonic.E, Accidental.NATURAL, 2)
                .down(new Interval(9, IntervalDiscriminator.MINOR))
        );

        assertEquals(
            new NamedNote(Diatonic.B, Accidental.NATURAL, 0),
            new NamedNote(Diatonic.E, Accidental.NATURAL, 2)
                .down(new Interval(11, IntervalDiscriminator.PERFECT))
        );
    }
}
