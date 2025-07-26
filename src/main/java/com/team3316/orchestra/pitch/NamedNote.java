package com.team3316.orchestra.pitch;

import org.apache.commons.numbers.fraction.Fraction;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import com.team3316.orchestra.pitch.interval.Interval;

/**
 * A note with a name and an accidental.
 * <p>
 * This doesn't store any frequencies, only an abstract pitch which is to be
 * interpreted by a tuning system.
 */
public record NamedNote(Diatonic name, Accidental accidental, int octave) {
    /**
     * Transpose this note up by a given interval.
     * 
     * @param interval Interval to transpose
     * @return New transposed note
     */
    @Contract(pure = true)
    @NotNull
    public NamedNote up(@NotNull final Interval interval) {
        final var newOctave = this.octave + ((interval.ordinal() - 1) / Interval.NUM_INTERVALS);
        final var newName = Diatonic.byOrdinal(this.name.ordinal() + interval.ordinal() - 1);
        final var expectedOffset = interval.halfsteps();
        final int actualOffset; {
            // tfw when you miss let-bindings
            var offset = newName.halfsteps - this.name.halfsteps;
            actualOffset = offset < 0 ? offset + 12 : offset;
        }
        final var error = actualOffset - expectedOffset;

        // Now we find the correct accidental
        final var newAcc = Accidental.byHalfsteps(Fraction.of(error));

        if (newAcc == null)
            throw new UnsupportedOperationException("Couldn't find accidental with offset " + error);

        return new NamedNote(newName, newAcc, newOctave);
    }

    /**
     * Transpose this note down by a given interval.
     * 
     * @param interval Interval to transpose
     * @return New transposed note
     */
    @Contract(pure = true)
    @NotNull
    public NamedNote down(@NotNull final Interval interval) {
        // Analogous to up()
        final var newOctave = this.octave - ((interval.ordinal() - 1) / Interval.NUM_INTERVALS);
        final var newName = Diatonic.byOrdinal(this.name.ordinal() - interval.ordinal() + 1);
        final var expectedOffset = interval.halfsteps();
        final int actualOffset; {
            var offset = this.name.halfsteps - newName.halfsteps;
            actualOffset = offset < 0 ? offset + 12 : offset;
        }
        final var error = actualOffset - expectedOffset;

        // Now we find the correct accidental
        final var newAcc = Accidental.byHalfsteps(Fraction.of(error));

        if (newAcc == null)
            throw new UnsupportedOperationException("Couldn't find accidental with offset " + error);

        return new NamedNote(newName, newAcc, newOctave);
    }
}
