package com.team3316.orchestra;

import java.util.List;

import org.apache.commons.numbers.fraction.Fraction;

import com.team3316.orchestra.pitch.Diatonic;
import com.team3316.orchestra.pitch.NamedNote;
import com.team3316.orchestra.time.Tempo;
import com.team3316.orchestra.time.Timed;
import com.team3316.orchestra.tuning.temperament.Equal24;
import com.team3316.orchestra.voice.Voice;

/**
 * A collection of built-in pieces
 */
public final class Pieces {
    /**
     * כשאור דולק בחלונך
     */
    public static final Piece OR = new Piece(new Tempo(Fraction.of(1, 4), Tempo.BPM.of(96)), List.of(
        Voice.NoteBuilder.of(List.of(
            Timed.of(NamedNote.of(Diatonic.D, 2), Fraction.of(1, 4)),
            Timed.of(NamedNote.of(Diatonic.A, 2), Fraction.of(3, 8)),
            Timed.of(NamedNote.of(Diatonic.G, 2), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(Diatonic.F, 2), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(Diatonic.G, 2), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(Diatonic.A, 2), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(Diatonic.G, 2), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(Diatonic.A, 2), Fraction.of(1, 4)),
            Timed.of(NamedNote.of(Diatonic.E, 2), Fraction.of(1, 4)),
            Timed.of(NamedNote.of(Diatonic.G, 2), Fraction.of(3, 8)),
            Timed.of(NamedNote.of(Diatonic.G, 2), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(Diatonic.F, 2), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(Diatonic.E, 2), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(Diatonic.D, 2), Fraction.of(1, 2)),
            Timed.rest(Fraction.of(1, 4)),
            Timed.rest(Fraction.of(1, 4)),
            Timed.of(NamedNote.of(Diatonic.E, 1), Fraction.of(1, 2)),
            Timed.rest(Fraction.of(1, 4)),
            Timed.of(NamedNote.of(Diatonic.G, 1), Fraction.of(1, 2))
        ), new Equal24()).pitches()
    ));

    private Pieces() {}
}
