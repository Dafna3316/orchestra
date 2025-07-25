package com.team3316.orchestra;

import org.apache.commons.lang3.NotImplementedException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import com.team3316.orchestra.interval.Interval;

/**
 * A note with a name and an accidental.
 * <p>
 * This doesn't store any frequencies, only an abstract pitch which is to be
 * interpreted by a tuning system.
 */
public record NamedNote(Diatonic name, Accidental accidental, int octave) {
    @Contract(pure = true)
    @NotNull
    public NamedNote up(@NotNull final Interval interval) {
        final var octave = this.octave + ((interval.ordinal() - 1) / Interval.NUM_INTERVALS);
        final var name = Diatonic.byOrdinal(this.name.ordinal() + interval.ordinal() - 1);
        throw new NotImplementedException();
    }

    @Contract(pure = true)
    @NotNull
    public NamedNote down(@NotNull final Interval interval) {
        throw new NotImplementedException();
    }
}
