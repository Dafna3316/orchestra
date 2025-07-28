package com.team3316.orchestra.tuning.temperament;

import java.io.Serializable;

import org.apache.commons.numbers.fraction.Fraction;
import org.jetbrains.annotations.NotNull;

import com.team3316.orchestra.pitch.Diatonic;
import com.team3316.orchestra.pitch.NamedNote;
import com.team3316.orchestra.pitch.Pitch;
import com.team3316.orchestra.pitch.interval.Interval;
import com.team3316.orchestra.pitch.interval.IntervalDiscriminator;
import com.team3316.orchestra.tuning.EqualTemperament;

/**
 * 31-tone equal temperament.
 * @param referenceNote Reference note
 * @param referencePitch Pitch correspondign to reference note
 */
public record Equal31(NamedNote referenceNote, Pitch referencePitch) implements EqualTemperament, Serializable {
    private static final Fraction DIESIS_TO_SEMITONE = Fraction.of(12, 31);

    @Override
    public @NotNull Fraction semitonesOf(Interval interval) {
        return DIESIS_TO_SEMITONE.multiply(diesesOf(interval));
    }

    private static int diesesOf(Interval interval) {
        // Yes, we're hardcoding everything
        var result = switch (interval.normalizedOrdinal()) {
            case 1 -> 0;
            case 2 -> 5;
            case 3 -> 10;
            case 4 -> 13;
            case 5 -> 18;
            case 6 -> 23;
            case 7 -> 28;
            default -> throw new IllegalStateException();
        } + (IntervalDiscriminator.PERFECT.isApplicableTo(interval.ordinal())
                ? perfectDelta(interval.discriminator())
                : imperfectDelta(interval.discriminator()));
        
        return result + (31 * interval.octaves());
    }

    private static int perfectDelta(IntervalDiscriminator disc) {
        return disc.halfstepsFromPerfect() * 2;
    }

    // Relative to major
    private static int imperfectDelta(IntervalDiscriminator disc) {
        return disc.halfstepsFromMajor() * 2;
    }

    /**
     * Create a new 31TET with the given pitch for {@link Diatonic#A A}.
     * @param referencePitch Reference pitch for A
     */
    public Equal31(Pitch referencePitch) {
        this(NamedNote.of(Diatonic.A), referencePitch);
    }

    /**
     * Create a new 31TET with A440.
     */
    public Equal31() {
        this(NamedNote.of(Diatonic.A), new Pitch());
    }
}
