package com.team3316.orchestra.tuning.temperament;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.team3316.orchestra.pitch.Accidental;
import com.team3316.orchestra.pitch.Diatonic;
import com.team3316.orchestra.pitch.NamedNote;
import com.team3316.orchestra.tuning.TuningSystem;

public class TuningSystemsTest {
    private static final NamedNote[] notes = {
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

    private static void printNotes(TuningSystem sys) {
        System.out.println("===" + sys.getClass().getSimpleName() + "===");
        for (final var nn : notes) {
            System.out.println(sys.interpret(nn).frequency());
        }
    }

    @Test
    @DisplayName("BWV 847/2: 12TET")
    void equal24() {
        printNotes(new Equal24());
    }

    @Test
    @DisplayName("BWV 847/2: 31TET")
    void equal31() {
        printNotes(new Equal31());
    }

    @Test
    @DisplayName("BWV 847/2: Kirnberger III")
    void k3() {
        printNotes(new Kirnberger3());
    }

    @Test
    @DisplayName("BWV 847/2: Werckmeister III")
    void w3() {
        printNotes(new Werckmeister3());
    }

    @Test
    @DisplayName("BWV 847/2: Werckmeister IV")
    void w4() {
        printNotes(new Werckmeister4());
    }

    @Test
    @DisplayName("BWV 847/2: Werckmeister V")
    void w5() {
        printNotes(new Werckmeister5());
    }

    @Test
    @DisplayName("BWV 847/2: Werckmeister VI")
    void w6() {
        printNotes(new Werckmeister6());
    }
}
