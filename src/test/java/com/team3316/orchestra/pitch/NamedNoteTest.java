package com.team3316.orchestra.pitch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.commons.numbers.fraction.Fraction;
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

    @Test
    @DisplayName("halfstepsTo")
    void halfsteps() {
        assertEquals(Fraction.of(7), NamedNote.of(Diatonic.C).halfstepsTo(NamedNote.of(Diatonic.G)));
        assertEquals(Fraction.of(-7), NamedNote.of(Diatonic.G).halfstepsTo(NamedNote.of(Diatonic.C)));
        assertEquals(Fraction.of(17), NamedNote.of(Diatonic.D, Accidental.FLAT, 1).halfstepsTo(NamedNote.of(Diatonic.F, Accidental.SHARP, 2)));
    }

    @Test
    @DisplayName("intervalTo")
    void intervals() {
        assertEquals(new Interval(1, IntervalDiscriminator.PERFECT), NamedNote.of(Diatonic.A).intervalTo(NamedNote.of(Diatonic.A)));
        assertEquals(new Interval(5, IntervalDiscriminator.PERFECT), NamedNote.of(Diatonic.A, 1).intervalTo(NamedNote.of(Diatonic.E, 2)));
        assertEquals(new Interval(4, IntervalDiscriminator.AUGMENTED), NamedNote.of(Diatonic.A, 1).intervalTo(NamedNote.of(Diatonic.E, Accidental.FLAT, 1)));
        assertEquals(new Interval(3, IntervalDiscriminator.MAJOR), NamedNote.of(Diatonic.C, 2).intervalTo(NamedNote.of(Diatonic.A, Accidental.FLAT, 1)));
        assertEquals(new Interval(1, IntervalDiscriminator.DIMINISHED), NamedNote.of(Diatonic.A).intervalTo(NamedNote.of(Diatonic.A, Accidental.FLAT)));
        assertEquals(new Interval(2, IntervalDiscriminator._DIMINISHED2), NamedNote.of(Diatonic.B, 1).intervalTo(NamedNote.of(Diatonic.C, Accidental.DOUBLE_FLAT, 2)));
    }
}
