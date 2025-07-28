package com.team3316.orchestra.tuning.temperament;

import java.io.Serializable;

import org.jetbrains.annotations.NotNull;

import com.team3316.orchestra.pitch.Diatonic;
import com.team3316.orchestra.pitch.NamedNote;
import com.team3316.orchestra.pitch.Pitch;
import com.team3316.orchestra.tuning.WellTemperament;

/**
 * Werckmeister IV temperament.
 * @param referenceNote Base note name of the system
 * @param referencePitch Pitch that the base note should be mapped to
 * @see <a href="https://en.wikipedia.org/wiki/Werckmeister_temperament#Werckmeister_II_(IV):_another_temperament_included_in_the_Orgelprobe,_divided_up_through_1%2F3_comma">Wikipedia</a>
 */
public record Werckmeister4(NamedNote referenceNote, Pitch referencePitch) implements WellTemperament, Serializable {
    @Override
    public @NotNull Pitch byHalfsteps(Pitch base, int halfsteps) {
        return switch (halfsteps) {
            case 0 -> base;
            case 1 -> base.upRatio(16384, 19683).upSemis(4);
            case 2 -> base.upRatio(8, 9).upSemis(4);
            case 3 -> base.upRatio(32, 27);
            case 4 -> base.upRatio(64, 81).upSemis(8);
            case 5 -> base.upRatio(4, 3);
            case 6 -> base.upRatio(1024, 729);
            case 7 -> base.upRatio(32, 27).upSemis(4);
            case 8 -> base.upRatio(8192, 6561).upSemis(4);
            case 9 -> base.upRatio(256, 243).upSemis(8);
            case 10 -> base.upRatio(9, 4).downSemis(4);
            case 11 -> base.upRatio(4096, 2187);
            default -> throw new WellTemperament.IllegalHalfstepException();
        };
    }

    public Werckmeister4 {
        if (referenceNote.accidental().halfsteps.abs().getDenominator() != 1) {
            throw new UnsupportedOperationException("Werckmeister IV can't interpret half-accidentals");
        }
    }

    /**
     * Construct a Werckmeister IV based on middle C at 264Hz.
     */
    public Werckmeister4() {
        this(NamedNote.of(Diatonic.C), Pitch.of(440.0).upRatio(6, 10));
    }
}
