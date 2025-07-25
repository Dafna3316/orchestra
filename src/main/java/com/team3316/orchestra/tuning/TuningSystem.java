package com.team3316.orchestra.tuning;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import com.team3316.orchestra.NamedNote;
import com.team3316.orchestra.Pitch;

/**
 * A tuning system can interpret a {@link NamedNote} into a {@link Pitch}.
 *
 * <p>
 * Any tuning system which is also a {@link ContextlessIntervalInterpreter}
 * must ensure that the following equations hold ∀ {@code Pitch p}, {@code Interval i}, {@code NamedNote nn}:
 * <pre>
 * {@link ContextlessIntervalInterpreter#upInterval upInterval}({@link #interpret interpret}(nn), i) == {@link #interpret interpret}(nn.{@link NamedNote#up up}(i))
 * {@link ContextlessIntervalInterpreter#downInterval downInterval}({@link #interpret interpret}(nn), i) == {@link #interpret interpret}(nn.{@link NamedNote#down down}(i))
 * </pre>
 */
public interface TuningSystem {
    @Contract(pure = true)
    @NotNull
    Pitch interpret(@NotNull final NamedNote note);
}
