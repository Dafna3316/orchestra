package com.team3316.orchestra.tuning;

import org.apache.commons.numbers.fraction.Fraction;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import com.team3316.orchestra.pitch.NamedNote;
import com.team3316.orchestra.pitch.Pitch;
import com.team3316.orchestra.pitch.interval.Interval;

/**
 * Convenience interface for equal temperaments.
 */
public interface EqualTemperament extends TuningSystem, ContextlessIntervalInterpreter {
    /**
     * Compute the amount of semitones that an interval induces.
     * <p>
     * This is <b>not</b> the same as {@link Interval#halfsteps()}!
     * @param interval Interval
     * @return Amount of semitones under this tuning system
     */
    @Contract(pure = true)
    @NotNull Fraction semitonesOf(Interval interval);

    /**
     * Reference note of the system.
     * @return A note
     */
    @NotNull NamedNote referenceNote();

    /**
     * Pitch that {@link #referenceNote()} should map to.
     * @return A pitch
     */
    @NotNull Pitch referencePitch();

    @Override
    default @NotNull Pitch upInterval(@NotNull Pitch original, @NotNull Interval interval) {
        return original.upSemis(semitonesOf(interval));
    }

    @Override
    default @NotNull Pitch downInterval(@NotNull Pitch original, @NotNull Interval interval) {
        return original.downSemis(semitonesOf(interval));
    }

    @Override
    default @NotNull Pitch interpret(@NotNull NamedNote note) {
        return referencePitch().upSemis(
            semitonesOf(referenceNote().intervalTo(note))
                .multiply(note.compareTo(referenceNote()) < 0 ? -1 : 1)
        );
    }
}
