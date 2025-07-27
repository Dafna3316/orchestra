package com.team3316.orchestra.time;

import org.apache.commons.numbers.fraction.Fraction;

import com.team3316.orchestra.pitch.NamedNote;
import com.team3316.orchestra.pitch.Pitch;
import com.team3316.orchestra.tuning.TuningSystem;

/**
 * Generic pitch with note value.
 * @param pitch Pitch or note name
 * @param duration Note value
 */
public record Timed<P>(P pitch, Fraction duration) {
    public Timed {
        if (duration.compareTo(Fraction.ZERO) <= 0)
            throw new IllegalArgumentException("Negative duration");
    }

    /**
     * Convert a timed {@link NamedNote} to a timed {@link Pitch}.
     * @param note Timed {@link NamedNote}
     * @param sys {@link TuningSystem} to interpret
     * @return Timed {@link Pitch} corresponding to the note
     */
    public static Timed<Pitch> toPitch(Timed<NamedNote> note, TuningSystem sys) {
        return new Timed<>(sys.interpret(note.pitch), note.duration);
    }

    /**
     * Convert a timed {@link Pitch} to a {@link TimedFrequency}.
     * @param pitch Timed {@link Pitch}
     * @return {@link TimedFrequency} corresponding to the pitch
     */
    public static TimedFrequency toFrequency(Timed<Pitch> pitch) {
        return new TimedFrequency(pitch.pitch.frequency(), pitch.duration);
    }

    /**
     * Convert a timed {@link NamedNote} to a {@link TimedFrequency}.
     * @param note Timed {@link NamedNote}
     * @param sys {@link TuningSystem} to interpret
     * @return {@link TimedFrequency} corresponding to the note
     */
    public static TimedFrequency toFrequency(Timed<NamedNote> note, TuningSystem sys) {
        return new TimedFrequency(sys.interpret(note.pitch).frequency(), note.duration);
    }
}
