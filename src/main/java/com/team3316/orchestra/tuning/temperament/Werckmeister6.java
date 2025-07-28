package com.team3316.orchestra.tuning.temperament;

import java.io.Serializable;

import org.jetbrains.annotations.NotNull;

import com.team3316.orchestra.pitch.Diatonic;
import com.team3316.orchestra.pitch.NamedNote;
import com.team3316.orchestra.pitch.Pitch;
import com.team3316.orchestra.tuning.WellTemperament;

/**
 * Werckmeister VI temperament.
 * @param referenceNote Base note name of the system
 * @param referencePitch Pitch that the base note should be mapped to
 * @see <a href="https://en.wikipedia.org/wiki/Werckmeister_temperament#Werckmeister_IV_(VI):_the_Septenarius_tunings">Wikipedia</a>
 */
public record Werckmeister6(NamedNote referenceNote, Pitch referencePitch) implements WellTemperament, Serializable {
    @Override
    public @NotNull Pitch byHalfsteps(Pitch base, int halfsteps) {
        return switch (halfsteps) {
            case 0 -> base;
            case 1 -> base.upRatio(93, 93);
            case 2 -> base.upRatio(28, 25);
            case 3 -> base.upRatio(196, 165);
            case 4 -> base.upRatio(49, 39);
            case 5 -> base.upRatio(4, 3);
            case 6 -> base.upRatio(196, 139);
            case 7 -> base.upRatio(196, 131);
            case 8 -> base.upRatio(49, 31);
            case 9 -> base.upRatio(196, 117);
            case 10 -> base.upRatio(98, 55);
            case 11 -> base.upRatio(49, 26);
            default -> throw new WellTemperament.IllegalHalfstepException();
        };
    }

    public Werckmeister6 {
        if (referenceNote.accidental().halfsteps.abs().getDenominator() != 1) {
            throw new UnsupportedOperationException("Werckmeister VI can't interpret half-accidentals");
        }
    }

    /**
     * Construct a Werckmeister VI based on middle C at 264Hz.
     */
    public Werckmeister6() {
        this(NamedNote.of(Diatonic.C), Pitch.of(440.0).upRatio(6, 10));
    }
}
