package com.team3316.orchestra.tuning;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import com.team3316.orchestra.pitch.NamedNote;
import com.team3316.orchestra.pitch.Pitch;
import com.team3316.orchestra.pitch.interval.Interval;

/**
 * A contextless interval interpreter can transpose {@link Pitch raw pitches} by {@link Interval intervals}.
 * <p>
 * Any interval interpreter which is also a {@link TuningSystem}
 * must satisfy the following equations ∀ {@code Interval i}, {@code NamedNote nn}:
 * <pre>
 * {@link #upInterval upInterval}({@link TuningSystem#interpret interpret}(nn), i) == {@link TuningSystem#interpret interpret}(nn.{@link NamedNote#up up}(i))
 * {@link #downInterval downInterval}({@link TuningSystem#interpret interpret}(nn), i) == {@link TuningSystem#interpret interpret}(nn.{@link NamedNote#down down}(i))
 * </pre>
 * Note: pitch equality/equivalence isn't defined, so is impossible to check.
 * The equations above relate to sound alone.
 */
public interface ContextlessIntervalInterpreter {
    /**
     * Transpose a pitch up by an interval.
     * 
     * @param original Source pitch
     * @param interval Interval
     * @return Transposed pitch
     */
    @Contract(pure = true)
    @NotNull
    Pitch upInterval(@NotNull Pitch original, @NotNull Interval interval);

    /**
     * Transpose a pitch down by an interval.
     * 
     * @param original Source pitch
     * @param interval Interval
     * @return Transposed pitch
     */
    @Contract(pure = true)
    @NotNull
    Pitch downInterval(@NotNull Pitch original, @NotNull Interval interval);
}
