package com.team3316.orchestra.tuning.temperament;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.team3316.orchestra.pitch.Accidental;
import com.team3316.orchestra.pitch.Diatonic;
import com.team3316.orchestra.pitch.NamedNote;
import com.team3316.orchestra.tuning.TuningSystem;

public class Equal24Test {
    @Test
    @DisplayName("BWV 847/2")
    void melody() {
        final TuningSystem sys = new Equal24();
        final NamedNote[] notes = {
            NamedNote.of(Diatonic.C, 2),
            NamedNote.of(Diatonic.B, 1),
            NamedNote.of(Diatonic.C, 2),
            NamedNote.of(Diatonic.G, 1),
            NamedNote.of(Diatonic.A, Accidental.FLAT, 1),
            NamedNote.of(Diatonic.C, 2),
            NamedNote.of(Diatonic.B, 1),
            NamedNote.of(Diatonic.C, 2),
            NamedNote.of(Diatonic.D, 2),
            NamedNote.of(Diatonic.G, 1),
            NamedNote.of(Diatonic.C, 2),
            NamedNote.of(Diatonic.B, 1),
            NamedNote.of(Diatonic.C, 2),
            NamedNote.of(Diatonic.D, 2),
            NamedNote.of(Diatonic.F, 1),
            NamedNote.of(Diatonic.G, 1),
            NamedNote.of(Diatonic.A, Accidental.FLAT, 1),
            NamedNote.of(Diatonic.G, 1),
            NamedNote.of(Diatonic.F, 1),
            NamedNote.of(Diatonic.E, Accidental.FLAT, 1),
        };

        for (final var nn : notes) {
            System.out.println(sys.interpret(nn).frequency());
        }
    }
}
