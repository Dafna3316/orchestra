package com.team3316.orchestra.tuning.temperament;

import java.io.Serializable;

import org.jetbrains.annotations.NotNull;

import com.team3316.orchestra.pitch.Diatonic;
import com.team3316.orchestra.pitch.NamedNote;
import com.team3316.orchestra.pitch.Pitch;
import com.team3316.orchestra.tuning.WellTemperament;

/**
 * Werckmeister III temperament.
 * @param referenceNote Base note name of the system
 * @param referencePitch Pitch that the base note should be mapped to
 * @see <a href="https://en.wikipedia.org/wiki/Werckmeister_temperament#Werckmeister_I_(III):_%22correct_temperament%22_based_on_1%2F4_comma_divisions">Wikipedia</a>
 */
public record Werckmeister3(NamedNote referenceNote, Pitch referencePitch) implements WellTemperament, Serializable {
    @Override
    public @NotNull Pitch byHalfsteps(Pitch base, int halfsteps) {
        return switch (halfsteps) {
            case 0 -> base;
            case 1 -> base.upRatio(256, 243);
            case 2 -> base.upRatio(64, 81).upSemis(6);
            case 3 -> base.upRatio(32, 27);
            case 4 -> base.upRatio(256, 243).upSemis(3);
            case 5 -> base.upRatio(4, 3);
            case 6 -> base.upRatio(1024, 729);
            case 7 -> base.upRatio(8, 9).upSemis(9);
            case 8 -> base.upRatio(128, 81);
            case 9 -> base.upRatio(1024, 729).upSemis(3);
            case 10 -> base.upRatio(16, 9);
            case 11 -> base.upRatio(128, 81).upSemis(3);
            default -> throw new WellTemperament.IllegalHalfstepException();
        };
    }

    public Werckmeister3 {
        if (referenceNote.accidental().halfsteps.abs().getDenominator() != 1) {
            throw new UnsupportedOperationException("Werckmeister III can't interpret half-accidentals");
        }
    }

    /**
     * Construct a Werckmeister III based on middle C at 264Hz.
     */
    public Werckmeister3() {
        this(NamedNote.of(Diatonic.C), Pitch.of(440.0).upRatio(6, 10));
    }
}
