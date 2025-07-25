package com.team3316.orchestra.tuning;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import com.team3316.orchestra.NamedNote;
import com.team3316.orchestra.Pitch;
import com.team3316.orchestra.interval.Interval;

/**
 * A contextless interval interpreter can transpose raw pitches by intervals.
 * <p>
 * Any interval interpreter which is also a {@link TuningSystem}
 * must ensure that the following equations hold ∀ {@code Pitch p}, {@code Interval i}, {@code NamedNote nn}:
 * <pre>
 * {@link #upInterval upInterval}({@link TuningSystem#interpret interpret}(nn), i) == {@link TuningSystem#interpret interpret}(nn.{@link NamedNote#up up}(i))
 * {@link #downInterval downInterval}({@link TuningSystem#interpret interpret}(nn), i) == {@link TuningSystem#interpret interpret}(nn.{@link NamedNote#down down}(i))
 * </pre>
 */
public interface ContextlessIntervalInterpreter {
    @Contract(pure = true)
    @NotNull
    Pitch upInterval(@NotNull final Pitch original, @NotNull final Interval interval);

    @Contract(pure = true)
    @NotNull
    Pitch downInterval(@NotNull final Pitch original, @NotNull final Interval interval);
}
