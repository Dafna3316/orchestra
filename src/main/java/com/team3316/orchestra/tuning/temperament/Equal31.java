package com.team3316.orchestra.tuning.temperament;

import org.apache.commons.numbers.fraction.Fraction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.ApiStatus;

import com.team3316.orchestra.pitch.Diatonic;
import com.team3316.orchestra.pitch.NamedNote;
import com.team3316.orchestra.pitch.Pitch;
import com.team3316.orchestra.pitch.interval.Interval;
import com.team3316.orchestra.pitch.interval.IntervalDiscriminator;
import com.team3316.orchestra.tuning.ContextlessIntervalInterpreter;
import com.team3316.orchestra.tuning.TuningSystem;

/**
 * 31-tone equal temperament.
 */
@ApiStatus.Experimental
public class Equal31 implements TuningSystem, ContextlessIntervalInterpreter {
    private final NamedNote referenceNote;
    private final Pitch referencePitch;

    private static final Fraction DIESIS_TO_SEMITONE = Fraction.of(12, 31);

    @Override
    public @NotNull Pitch upInterval(@NotNull Pitch original, @NotNull Interval interval) {
        return original.upSemis(DIESIS_TO_SEMITONE.multiply(diesesOf(interval)));
    }

    @Override
    public @NotNull Pitch downInterval(@NotNull Pitch original, @NotNull Interval interval) {
        return original.downSemis(DIESIS_TO_SEMITONE.multiply(diesesOf(interval)));
    }

    /**
     * Warning: this doesn't work in general
     */
    @Override
    @ApiStatus.Experimental
    public @NotNull Pitch interpret(@NotNull NamedNote note) {
        // TODO Do this properly
        return referencePitch.upSemis(
            DIESIS_TO_SEMITONE
                .multiply(diesesOf(referenceNote.intervalTo(note)))
                .multiply(note.compareTo(referenceNote) < 0 ? -1 : 1)
        );
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
        return switch (disc) {
            case PERFECT -> 0;
            case AUGMENTED -> 2;
            case DIMINISHED -> -2;
            default -> throw new IllegalArgumentException("Imperfect discriminator passed to perfect");
        };
    }

    // Relative to major
    private static int imperfectDelta(IntervalDiscriminator disc) {
        return switch (disc) {
            case MAJOR -> 0;
            case MINOR -> -2;
            case AUGMENTED -> 2;
            case DIMINISHED -> -4;
            default -> throw new IllegalArgumentException("Perfect discriminator passed to imperfect");
        };
    }

    /**
     * Create a new 31TET.
     * @param referenceNote Reference note
     * @param referencePitch Pitch correspondign to reference note
     */
    public Equal31(NamedNote referenceNote, Pitch referencePitch) {
        this.referenceNote = referenceNote;
        this.referencePitch = referencePitch;
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
