package com.team3316.orchestra.tuning.temperament;

import java.io.Serializable;

import org.apache.commons.numbers.fraction.Fraction;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import com.team3316.orchestra.pitch.Diatonic;
import com.team3316.orchestra.pitch.NamedNote;
import com.team3316.orchestra.pitch.Pitch;
import com.team3316.orchestra.pitch.interval.Interval;
import com.team3316.orchestra.tuning.EqualTemperament;
import com.team3316.orchestra.tuning.WellTemperament;

/**
 * 12-tone/24-tone equal temperament.
 * @param referenceNote Note name corresponding to the reference pitch
 * @param referencePitch Reference pitch
 */
public record Equal24(NamedNote referenceNote, Pitch referencePitch) implements WellTemperament, EqualTemperament, Serializable {
    @Override
    @Contract(pure = true)
    public @NotNull Pitch interpret(@NotNull NamedNote note) {
        return referencePitch.upSemis(referenceNote.halfstepsTo(note));
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
     * Construct a 12TET with the reference {@link Diatonic#A A} pitch being the default of {@link Pitch#Pitch()}.
     */
    public Equal24() {
        this(NamedNote.of(Diatonic.A), new Pitch());
    }

    /**
     * Construct a 12TET with the given reference pitch being used for {@link Diatonic#A A}.
     *
     * @param referencePitch Reference pitch
     */
    public Equal24(Pitch referencePitch) {
        this(NamedNote.of(Diatonic.A), referencePitch);
    }

    @Override
    public @NotNull Fraction semitonesOf(Interval interval) {
        return Fraction.of(interval.halfsteps());
    }

    @Override
    public @NotNull Pitch byHalfsteps(Pitch base, int halfsteps) {
        return base.upSemis(halfsteps);
    }
}
