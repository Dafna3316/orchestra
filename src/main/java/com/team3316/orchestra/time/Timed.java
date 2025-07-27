package com.team3316.orchestra.time;

import org.apache.commons.numbers.fraction.Fraction;

import com.team3316.orchestra.pitch.NamedNote;
import com.team3316.orchestra.pitch.Pitch;
import com.team3316.orchestra.tuning.TuningSystem;

public record Timed<P>(P pitch, Fraction duration) {
    public Timed {
        if (duration.compareTo(Fraction.ZERO) <= 0)
            throw new IllegalArgumentException("Negative duration");
    }

    public static Timed<Pitch> toPitch(Timed<NamedNote> note, TuningSystem sys) {
        return new Timed<>(sys.interpret(note.pitch), note.duration);
    }

    public static TimedFrequency toFrequency(Timed<Pitch> pitch) {
        return new TimedFrequency(pitch.pitch.frequency(), pitch.duration);
    }

    public static TimedFrequency toFrequency(Timed<NamedNote> pitch, TuningSystem sys) {
        return new TimedFrequency(sys.interpret(pitch.pitch).frequency(), pitch.duration);
    }
}
