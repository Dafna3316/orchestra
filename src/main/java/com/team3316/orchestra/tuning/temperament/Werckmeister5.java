package com.team3316.orchestra.tuning.temperament;

import java.io.Serializable;

import org.jetbrains.annotations.NotNull;

import com.team3316.orchestra.pitch.Diatonic;
import com.team3316.orchestra.pitch.NamedNote;
import com.team3316.orchestra.pitch.Pitch;
import com.team3316.orchestra.tuning.WellTemperament;

/**
 * Werckmeister V temperament.
 * @param referenceNote Base note name of the system
 * @param referencePitch Pitch that the base note should be mapped to
 * @see <a href="https://en.wikipedia.org/wiki/Werckmeister_temperament#Werckmeister_III_(V):_an_additional_temperament_divided_up_through_⁠1/4⁠_comma>Wikipedia</a>
 */
public record Werckmeister5(NamedNote referenceNote, Pitch referencePitch) implements WellTemperament, Serializable {
    @Override
    public @NotNull Pitch byHalfsteps(Pitch base, int halfsteps) {
        return switch (halfsteps) {
            case 0 -> base;
            case 1 -> base.upRatio(8, 9).upSemis(3);
            case 2 -> base.upRatio(9, 8);
            case 3 -> base.upSemis(3);
            case 4 -> base.upRatio(8, 9).upSemis(6);
            case 5 -> base.upRatio(9, 8).upSemis(3);
            case 6 -> base.upSemis(6);
            case 7 -> base.upRatio(3, 2);
            case 8 -> base.upRatio(128, 81);
            case 9 -> base.upSemis(9);
            case 10 -> base.upRatio(3).downSemis(9);
            case 11 -> base.upRatio(4, 3).upSemis(6);
            default -> throw new WellTemperament.IllegalHalfstepException();
        };
    }

    public Werckmeister5 {
        if (referenceNote.accidental().halfsteps.abs().getDenominator() != 1) {
            throw new UnsupportedOperationException("Werckmeister V can't interpret half-accidentals");
        }
    }

    /**
     * Construct a Werckmeister V based on middle C at 264Hz.
     */
    public Werckmeister5() {
        this(NamedNote.of(Diatonic.C), Pitch.of(440.0).upRatio(6, 10));
    }
}
