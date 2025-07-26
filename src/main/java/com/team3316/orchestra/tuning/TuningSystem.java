package com.team3316.orchestra.tuning;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import com.team3316.orchestra.pitch.NamedNote;
import com.team3316.orchestra.pitch.Pitch;

/**
 * A tuning system can interpret a {@link NamedNote} into a {@link Pitch}.
 *
 * <p>
 * Any tuning system which is also a {@link ContextlessIntervalInterpreter}
 * must satisfy the following equations ∀ {@code Interval i}, {@code NamedNote nn}:
 * <pre>
 * {@link ContextlessIntervalInterpreter#upInterval upInterval}({@link #interpret interpret}(nn), i) == {@link #interpret interpret}(nn.{@link NamedNote#up up}(i))
 * {@link ContextlessIntervalInterpreter#downInterval downInterval}({@link #interpret interpret}(nn), i) == {@link #interpret interpret}(nn.{@link NamedNote#down down}(i))
 * </pre>
 * Note: pitch equality/equivalence isn't defined, so is impossible to check.
 * The equations above relate to sound alone.
 */
public interface TuningSystem {
    /**
     * Tune a note.
     * <p>
     * This method shall interpret a note name and octave into an actual frequency.
     * 
     * @param note Semantic note
     * @return Corresponding pitch
     */
    @Contract(pure = true)
    @NotNull
    Pitch interpret(@NotNull NamedNote note);
}
