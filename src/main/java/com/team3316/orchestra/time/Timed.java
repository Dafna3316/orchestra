package com.team3316.orchestra.time;

import java.io.Serializable;

import org.apache.commons.numbers.fraction.Fraction;

import com.team3316.orchestra.pitch.NamedNote;
import com.team3316.orchestra.pitch.Pitch;
import com.team3316.orchestra.tuning.TuningSystem;

/**
 * Generic pitch with note value.
 * @param <P> Wrapped object
 * @param pitch Pitch or note name
 * @param duration Note value
 * @param isRest Whether this represents a rest
 */
public record Timed<P>(P pitch, Fraction duration, boolean isRest) implements Serializable {
    public Timed {
        if (duration.compareTo(Fraction.ZERO) <= 0)
            throw new IllegalArgumentException("Negative duration");
    }

    /**
     * Create a timed object.
     * @param <P> Wrapped object
     * @param pitch Object
     * @param duration Duration
     * @return Timed object
     */
    public static <P> Timed<P> of(P pitch, Fraction duration) {
        return new Timed<>(pitch, duration, false);
    }

    /**
     * Create a timed rest.
     * @param <P> Anything really
     * @param duration Duration
     * @return Rest with given duration
     */
    public static <P> Timed<P> rest(Fraction duration) {
        return new Timed<>(null, duration, true);
    }

    /**
     * Convert a timed {@link NamedNote} to a timed {@link Pitch}.
     * @param note Timed {@link NamedNote}
     * @param sys {@link TuningSystem} to interpret
     * @return Timed {@link Pitch} corresponding to the note
     */
    public static Timed<Pitch> toPitch(Timed<NamedNote> note, TuningSystem sys) {
        return new Timed<>(note.isRest ? Pitch.of(0.0) : sys.interpret(note.pitch), note.duration, note.isRest);
    }

    /**
     * Convert a timed {@link Pitch} to a {@link TimedFrequency}.
     * @param pitch Timed {@link Pitch}
     * @return {@link TimedFrequency} corresponding to the pitch
     */
    public static TimedFrequency toFrequency(Timed<Pitch> pitch) {
        return new TimedFrequency(pitch.isRest ? 0 : pitch.pitch.frequency(), pitch.duration);
    }

    /**
     * Convert a timed {@link NamedNote} to a {@link TimedFrequency}.
     * @param note Timed {@link NamedNote}
     * @param sys {@link TuningSystem} to interpret
     * @return {@link TimedFrequency} corresponding to the note
     */
    public static TimedFrequency toFrequency(Timed<NamedNote> note, TuningSystem sys) {
        return new TimedFrequency(note.isRest ? 0 : sys.interpret(note.pitch).frequency(), note.duration);
    }
}
