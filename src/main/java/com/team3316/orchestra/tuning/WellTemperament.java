package com.team3316.orchestra.tuning;

import org.apache.commons.numbers.fraction.Fraction;
import org.jetbrains.annotations.NotNull;

import com.team3316.orchestra.pitch.NamedNote;
import com.team3316.orchestra.pitch.Pitch;

/**
 * Convenience interface for 12-tone temperaments.
 */
public interface WellTemperament extends TuningSystem {
    /**
     * Reference note of the system.
     * <p>
     * Implementer must ensure that the note returned doesn't have
     * half-accidentals.
     * @return A note
     */
    @NotNull NamedNote referenceNote();

    /**
     * Pitch that {@link #referenceNote()} should map to.
     * @return A pitch
     */
    @NotNull Pitch referencePitch();

    /**
     * Raise a base pitch by some amount of half-steps.
     * <p>
     * {@code base} should be treated as if it were the reference pitch of the system.
     * <p>
     * This is <b>not</b> the same as {@link Pitch#upSemis(int)}!
     * @param base Pitch
     * @param halfsteps Amount of half-steps, strictly less than 12
     * @return Raised pitch
     */
    @NotNull Pitch byHalfsteps(Pitch base, int halfsteps);

    @Override
    default @NotNull Pitch interpret(@NotNull NamedNote note) {
        if (note.accidental().halfsteps.abs().getDenominator() != 1) {
            throw new UnsupportedOperationException(this.getClass().getName() + " can't interpret half-accidentals");
        }

        // Halfsteps above base of target note
        var targetHalfsteps = referenceNote().halfstepsTo(note).intValue();
        var octaves = targetHalfsteps / 12;
        targetHalfsteps -= 12 * octaves;
        if (targetHalfsteps < 0) {
            targetHalfsteps += 12;
            octaves--;
        }

        final var basePitch = byHalfsteps(referencePitch(), targetHalfsteps);
        return basePitch.upRatio(Fraction.of(2).pow(octaves));
    }

    /**
     * Thrown when an invalid half-step index is passed.
     */
    public static class IllegalHalfstepException extends IllegalArgumentException {
        /**
         * Default constructor.
         */
        public IllegalHalfstepException() {
            super("Half-step not between 0 and 12");
        }
    }
}
