package com.team3316.orchestra.tuning.temperament;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import com.team3316.orchestra.pitch.Diatonic;
import com.team3316.orchestra.pitch.NamedNote;
import com.team3316.orchestra.pitch.Pitch;
import com.team3316.orchestra.pitch.interval.Interval;
import com.team3316.orchestra.tuning.ContextlessIntervalInterpreter;
import com.team3316.orchestra.tuning.TuningSystem;

/**
 * Twelve-tone equal temperament.
 * <p>
 * Actually 24-tone equal temperament.
 */
public class TwelveTET implements ContextlessIntervalInterpreter, TuningSystem {
    private final Pitch standardPitch;
    private static final Diatonic referenceName = Diatonic.A;

    @Override
    @Contract(pure = true)
    public @NotNull Pitch interpret(@NotNull NamedNote note) {
        return standardPitch.upSemis(NamedNote.of(referenceName).halfstepsTo(note));
    }

    @Override
    @Contract(pure = true)
    public @NotNull Pitch upInterval(@NotNull Pitch original, @NotNull Interval interval) {
        return original.upSemis(interval.halfsteps()).upRatio(1 << interval.octaves());
    }

    @Override
    @Contract(pure = true)
    public @NotNull Pitch downInterval(@NotNull Pitch original, @NotNull Interval interval) {
        return original.downSemis(interval.halfsteps()).downRatio(1 << interval.octaves());
    }

    /**
     * Construct a 12TET with the reference pitch being the default of {@link Pitch#Pitch()}.
     */
    public TwelveTET() {
        standardPitch = new Pitch();
    }

    /**
     * Construct a 12TET with the given reference pitch being used for A.
     * 
     * @param reference Reference pitch
     */
    public TwelveTET(Pitch reference) {
        standardPitch = reference;
    }
}
