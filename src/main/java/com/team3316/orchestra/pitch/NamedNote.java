package com.team3316.orchestra.pitch;

import org.apache.commons.numbers.fraction.Fraction;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import com.team3316.orchestra.pitch.interval.Interval;
import com.team3316.orchestra.pitch.interval.IntervalDiscriminator;
import com.team3316.orchestra.tuning.TuningSystem;

/**
 * A note with a name and an accidental.
 * <p>
 * This doesn't store any frequencies, only an abstract pitch which is to be
 * interpreted by a {@link TuningSystem}.
 */
public record NamedNote(Diatonic name, Accidental accidental, int octave) implements Comparable<NamedNote> {
    /**
     * Transpose this note up by a given interval.
     * 
     * @param interval Interval to transpose
     * @return New transposed note
     */
    @Contract(pure = true)
    @NotNull
    public NamedNote up(@NotNull final Interval interval) {
        var newOctave = this.octave + interval.octaves();
        final var newName = Diatonic.byOrdinal(this.name.ordinal() + interval.ordinal() - 1);
        final var expectedOffset = interval.halfsteps();
        final int actualOffset; {
            // tfw when you miss let-bindings
            var offset = newName.halfsteps - this.name.halfsteps;
            if (offset < 0) newOctave++;
            actualOffset = offset < 0 ? offset + 12 : offset;
        }
        final var error = Fraction.of(expectedOffset - actualOffset).add(this.accidental.halfsteps);

        // Now we find the correct accidental
        final var newAcc = Accidental.byHalfsteps(error);

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
        var newOctave = this.octave - interval.octaves();
        final var newName = Diatonic.byOrdinal(this.name.ordinal() - interval.ordinal() + 1);
        final var expectedOffset = interval.halfsteps();
        final int actualOffset; {
            var offset = this.name.halfsteps - newName.halfsteps;
            if (offset < 0) newOctave--;
            actualOffset = offset < 0 ? offset + 12 : offset;
        }
        final var error = Fraction.of(expectedOffset - actualOffset).subtract(this.accidental.halfsteps);

        // Now we find the correct accidental
        final var newAcc = Accidental.byHalfsteps(error.negate());

        if (newAcc == null)
            throw new UnsupportedOperationException("Couldn't find accidental with offset " + error);

        return new NamedNote(newName, newAcc, newOctave);
    }

    /**
     * Compute the interval between this note and another note.
     * <p>
     * Negative intervals are not supported; if this note is higher than
     * {@code other}, the same interval is returned.
     *
     * @param other Note to compare
     * @return Interval between the notes
     */
    @Contract(pure = true)
    public @NotNull Interval intervalTo(final @NotNull NamedNote other) {
        // Count half-steps
        final var targetHalfstepsFraction = halfstepsTo(other);
        // We don't support negatives
        if (targetHalfstepsFraction.compareTo(Fraction.ZERO) < 0) return other.intervalTo(this);
        if (targetHalfstepsFraction.abs().getDenominator() != 1)
            throw new UnsupportedOperationException("Half-intervals aren't supported");

        final var targetHalfsteps = targetHalfstepsFraction.intValue();

        // Name of the interval
        var intervalOrd = other.name.ordinal() - this.name.ordinal();
        if (intervalOrd < 0) intervalOrd += Interval.NUM_INTERVALS;
        intervalOrd++;

        if (IntervalDiscriminator.PERFECT.isApplicableTo(intervalOrd)) {
            var interval = new Interval(intervalOrd, IntervalDiscriminator.PERFECT);
            var error = (targetHalfsteps % 12) - interval.halfsteps();
            var disc = IntervalDiscriminator.PERFECT;
            if (error == 1) disc = IntervalDiscriminator.AUGMENTED;
            else if (error == -1) disc = IntervalDiscriminator.DIMINISHED;
            else if (error != 0) throw new UnsupportedOperationException();

            return new Interval(intervalOrd + (Interval.NUM_INTERVALS * (targetHalfsteps / 12)), disc);
        } else {
            var interval = new Interval(intervalOrd, IntervalDiscriminator.MAJOR);
            var error = (targetHalfsteps % 12) - interval.halfsteps();
            var disc = IntervalDiscriminator.MAJOR;
            if (error == 1) disc = IntervalDiscriminator.AUGMENTED;
            else if (error == -1) disc = IntervalDiscriminator.MINOR;
            else if (error == -2) disc = IntervalDiscriminator.DIMINISHED;
            else if (error != 0) throw new UnsupportedOperationException();

            return new Interval(intervalOrd + (Interval.NUM_INTERVALS * (targetHalfsteps / 12)), disc);
        }
    }

    @Override
    public int compareTo(NamedNote o) {
        if (this.octave != o.octave) return this.octave - o.octave;
        return this.name.ordinal() - o.name.ordinal();
    }

    /**
     * Count half-steps between this note and another note.
     * <p>
     * This corresponds to the concept of a <i>semitone</i> in 12TET.
     * Other tuning systems typically don't have a direct equivalent, but might
     * still make use of this value.
     * @param other Another note
     * @return Number of half-steps between this and {@code other}
     */
    @Contract(pure = true)
    public Fraction halfstepsTo(final @NotNull NamedNote other) {
        return other.accidental.halfsteps.add(other.name.halfsteps).add((other.octave - 1) * 12).subtract(
            this.accidental.halfsteps.add(this.name.halfsteps).add((this.octave - 1) * 12)
        );
    }

    /**
     * Create a new named note.
     * @param other Note to copy from
     * @return New note
     */
    @Contract(pure = true)
    public static @NotNull NamedNote of(@NotNull NamedNote other) {
        return new NamedNote(other.name, other.accidental, other.octave);
    }

    /**
     * Create a new named note, with {@link Accidental#NATURAL} and octave 1.
     * @param name Name of the note
     * @return New note
     */
    @Contract(pure = true)
    public static @NotNull NamedNote of(@NotNull Diatonic name) {
        return new NamedNote(name, DEFAULT_ACC, DEFAULT_OCTAVE);
    }

    /**
     * Create a new named note, with {@link Accidental#NATURAL}.
     * @param name Name of the note
     * @param octave Octave of the note
     * @return New note
     */
    @Contract(pure = true)
    public static @NotNull NamedNote of(@NotNull Diatonic name, int octave) {
        return new NamedNote(name, DEFAULT_ACC, octave);
    }

    /**
     * Create a new named note, at octave 1.
     * @param name Name of the note
     * @param accidental Accidental of the note
     * @return New note
     */
    @Contract(pure = true)
    public static @NotNull NamedNote of(@NotNull Diatonic name, @NotNull Accidental accidental) {
        return new NamedNote(name, accidental, DEFAULT_OCTAVE);
    }

    /**
     * Create a new named note.
     * @param name Name of the note
     * @param accidental Accidental of the note
     * @param octave Octave of the note
     * @return New note
     */
    @Contract(pure = true)
    public static @NotNull NamedNote of(@NotNull Diatonic name, @NotNull Accidental accidental, int octave) {
        return new NamedNote(name, accidental, octave);
    }

    private static final int DEFAULT_OCTAVE = 1;
    private static final Accidental DEFAULT_ACC = Accidental.NATURAL;
}
