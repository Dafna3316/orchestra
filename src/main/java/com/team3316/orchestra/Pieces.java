package com.team3316.orchestra;

import java.util.List;

import org.apache.commons.numbers.fraction.Fraction;

import com.team3316.orchestra.pitch.NamedNote;
import com.team3316.orchestra.time.Tempo;
import com.team3316.orchestra.time.Timed;
import com.team3316.orchestra.tuning.TuningSystem;
import com.team3316.orchestra.tuning.temperament.Equal31;
import com.team3316.orchestra.voice.Voice;

import static com.team3316.orchestra.pitch.Diatonic.A;
import static com.team3316.orchestra.pitch.Diatonic.B;
import static com.team3316.orchestra.pitch.Diatonic.C;
import static com.team3316.orchestra.pitch.Diatonic.D;
import static com.team3316.orchestra.pitch.Diatonic.E;
import static com.team3316.orchestra.pitch.Diatonic.F;
import static com.team3316.orchestra.pitch.Diatonic.G;

import static com.team3316.orchestra.pitch.Accidental.SHARP;
import static com.team3316.orchestra.pitch.Accidental.FLAT;

/**
 * A collection of built-in pieces
 */
public final class Pieces {
    private static final TuningSystem sys = new Equal31();
    /**
     * כשאור דולק בחלונך
     */
    public static final Piece OR = new Piece(new Tempo(Fraction.of(1, 4), Tempo.BPM.of(96)), List.of(
        Voice.NoteBuilder.of(List.of(
            Timed.of(NamedNote.of(D, 2), Fraction.of(1, 4)),

            Timed.of(NamedNote.of(A, 2), Fraction.of(3, 8)),
            Timed.of(NamedNote.of(G, 2), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(F, 2), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(G, 2), Fraction.of(1, 8)),

            Timed.of(NamedNote.of(A, 2), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(G, 2), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(A, 2), Fraction.of(1, 4)),
            Timed.of(NamedNote.of(E, 2), Fraction.of(1, 4)),

            Timed.of(NamedNote.of(G, 2), Fraction.of(3, 8)),
            Timed.of(NamedNote.of(G, 2), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(F, 2), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(E, 2), Fraction.of(1, 8)),

            Timed.of(NamedNote.of(D, 2), Fraction.of(1, 2)),
            Timed.rest(Fraction.of(1, 4)),

            Timed.rest(Fraction.of(1, 4)),
            Timed.of(NamedNote.of(E, 1), Fraction.of(1, 2)),

            Timed.rest(Fraction.of(1, 4)),
            Timed.of(NamedNote.of(G, 1), Fraction.of(1, 2)),

            Timed.rest(Fraction.of(3, 8)),
            Timed.of(NamedNote.of(A, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(G, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(A, 1), Fraction.of(1, 8)),

            Timed.of(NamedNote.of(B, 1), Fraction.of(3, 8)),
            Timed.of(NamedNote.of(A, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(G, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(A, 1), Fraction.of(1, 8)),

            Timed.of(NamedNote.of(B, 1), Fraction.of(3, 8)),
            Timed.of(NamedNote.of(D, 2), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(A, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(C, SHARP, 2), Fraction.of(1, 8)),

            Timed.of(NamedNote.of(B, 1), Fraction.of(1, 4)),
            Timed.of(NamedNote.of(B, 1), Fraction.of(7, 8)),
            Timed.of(NamedNote.of(A, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(G, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(A, 1), Fraction.of(1, 8)),

            Timed.of(NamedNote.of(B, 1), Fraction.of(3, 8)),
            Timed.of(NamedNote.of(B, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(A, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(D, 2), Fraction.of(1, 8)),

            Timed.of(NamedNote.of(C, SHARP, 2), Fraction.of(3, 8)),
            Timed.of(NamedNote.of(C, SHARP, 2), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(A, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(E, 2), Fraction.of(1, 8)),

            Timed.of(NamedNote.of(D, 2), Fraction.of(9, 8)),
            Timed.of(NamedNote.of(D, 2), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(C, 2), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(D, 2), Fraction.of(1, 8)),

            Timed.of(NamedNote.of(E, 2), Fraction.of(3, 8)),
            Timed.of(NamedNote.of(D, 2), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(C, 2), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(D, 2), Fraction.of(1, 8)),

            Timed.of(NamedNote.of(E, 2), Fraction.of(3, 8)),
            Timed.of(NamedNote.of(G, 2), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(D, 2), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(F, 2), Fraction.of(1, 8)),

            Timed.of(NamedNote.of(E, 2), Fraction.of(1, 4)),
            Timed.of(NamedNote.of(E, 2), Fraction.of(7, 8)),
            Timed.of(NamedNote.of(D, 2), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(E, 2), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(D, 2), Fraction.of(1, 8)),

            Timed.of(NamedNote.of(A, 2), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(G, 2), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(F, 2), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(D, 2), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(B, FLAT, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(A, 1), Fraction.of(1, 8)),

            Timed.of(NamedNote.of(E, 2), Fraction.of(5, 8)),
            Timed.of(NamedNote.of(E, 2), Fraction.of(1, 8)),

            Timed.of(NamedNote.of(D, 2), Fraction.of(10, 8)),
            Timed.of(NamedNote.of(D, 2), Fraction.of(1, 4)),

            Timed.of(NamedNote.of(A, 2), Fraction.of(3, 8)),
            Timed.of(NamedNote.of(G, 2), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(F, 2), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(G, 2), Fraction.of(1, 8)),

            Timed.of(NamedNote.of(A, 2), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(G, 2), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(A, 2), Fraction.of(1, 4)),
            Timed.of(NamedNote.of(E, 2), Fraction.of(1, 4)),

            Timed.of(NamedNote.of(G, 2), Fraction.of(3, 8)),
            Timed.of(NamedNote.of(G, 2), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(F, 2), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(E, 2), Fraction.of(1, 8)),

            Timed.of(NamedNote.of(D, 2), Fraction.of(1, 2)),
            Timed.of(NamedNote.of(D, 2), Fraction.of(1, 4)),

            Timed.of(NamedNote.of(G, 2), Fraction.of(3, 8)),
            Timed.of(NamedNote.of(F, 2), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(G, 2), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(D, 2), Fraction.of(1, 8)),

            Timed.of(NamedNote.of(F, 2), Fraction.of(3, 8)),
            Timed.of(NamedNote.of(E, 2), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(C, SHARP, 2), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(A, 1), Fraction.of(1, 8)),

            Timed.of(NamedNote.of(E, 2), Fraction.of(10, 8)),
            Timed.rest(Fraction.of(1, 4)),

            Timed.of(NamedNote.of(F, 2), Fraction.of(1, 16)),
            Timed.of(NamedNote.of(E, 2), Fraction.of(1, 16)),
            Timed.of(NamedNote.of(D, 2), Fraction.of(1, 16)),
            Timed.of(NamedNote.of(C, 2), Fraction.of(9, 16)),

            Timed.of(NamedNote.of(D, 2), Fraction.of(1, 16)),
            Timed.of(NamedNote.of(E, 2), Fraction.of(1, 16)),
            Timed.of(NamedNote.of(F, 2), Fraction.of(1, 16)),
            Timed.of(NamedNote.of(G, 2), Fraction.of(9, 16)),

            Timed.of(NamedNote.of(E, 2), Fraction.of(1, 16)),
            Timed.of(NamedNote.of(D, 2), Fraction.of(1, 16)),
            Timed.of(NamedNote.of(E, 2), Fraction.of(1, 16)),
            Timed.of(NamedNote.of(C, 2), Fraction.of(9, 16)),

            Timed.of(NamedNote.of(D, 2), Fraction.of(1, 16)),
            Timed.of(NamedNote.of(C, 2), Fraction.of(1, 16)),
            Timed.of(NamedNote.of(D, 2), Fraction.of(1, 16)),
            Timed.of(NamedNote.of(A, 1), Fraction.of(5, 16)),
            Timed.of(NamedNote.of(D, 2), Fraction.of(1, 4)),

            Timed.of(NamedNote.of(G, 2), Fraction.of(3, 8)),
            Timed.of(NamedNote.of(F, 2), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(G, 2), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(D, 2), Fraction.of(1, 8)),

            Timed.of(NamedNote.of(F, 2), Fraction.of(6, 8))
        ), sys),

        Voice.NoteBuilder.of(List.of(
            Timed.rest(Fraction.of(1, 4)),

            Timed.of(NamedNote.of(D, 2), Fraction.of(3, 4)),
            Timed.of(NamedNote.of(D, FLAT, 2), Fraction.of(3, 4)),
            Timed.of(NamedNote.of(C, 2), Fraction.of(3, 4)),
            Timed.of(NamedNote.of(B, 1), Fraction.of(1, 2)),
            Timed.rest(Fraction.of(1, 4)),

            Timed.rest(Fraction.of(1, 4)),
            Timed.of(NamedNote.of(D, 1), Fraction.of(1, 2)),

            Timed.rest(Fraction.of(1, 4)),
            Timed.of(NamedNote.of(C, SHARP, 1), Fraction.of(1, 2)),

            Timed.rest(Fraction.of(3, 4)),

            Timed.of(NamedNote.of(F, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(E, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(F, 1), Fraction.of(5, 8)),
            Timed.of(NamedNote.of(E, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(F, 1), Fraction.of(1, 2)),

            Timed.rest(Fraction.of(1, 8)),
            Timed.of(NamedNote.of(B, 0), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(E, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(D, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(C, SHARP, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(B, 0), Fraction.of(1, 8)),

            Timed.of(NamedNote.of(C, SHARP, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(A, 0), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(B, 0), Fraction.of(10, 8)),

            Timed.of(NamedNote.of(C, SHARP, 1), Fraction.of(3, 4)),

            Timed.rest(Fraction.of(1, 8)),
            Timed.of(NamedNote.of(D, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(C, 2), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(B, FLAT, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(A, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(G, 1), Fraction.of(1, 8)),

            Timed.of(NamedNote.of(F, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(E, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(D, 1), Fraction.of(1, 2)),

            Timed.of(NamedNote.of(B, FLAT, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(A, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(B, FLAT, 1), Fraction.of(5, 8)),
            Timed.of(NamedNote.of(A, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(B, FLAT, 1), Fraction.of(1, 2)),

            Timed.rest(Fraction.of(1, 8)),
            Timed.of(NamedNote.of(D, 2), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(C, 2), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(B, FLAT, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(A, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(G, 1), Fraction.of(1, 8)),

            Timed.of(NamedNote.of(F, 1), Fraction.of(1, 4)),
            Timed.of(NamedNote.of(E, 1), Fraction.of(1, 2)),

            Timed.rest(Fraction.of(3, 4)),

            Timed.rest(Fraction.of(1, 8)),
            Timed.of(NamedNote.of(B, FLAT, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(A, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(B, FLAT, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(A, 1), Fraction.of(1, 4)),

            Timed.of(NamedNote.of(C, 2), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(B, FLAT, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(B, FLAT, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(A, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(A, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(G, 1), Fraction.of(1, 8)),

            Timed.of(NamedNote.of(F, SHARP, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(G, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(A, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(B, FLAT, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(C, 2), Fraction.of(1, 4)),

            Timed.of(NamedNote.of(B, FLAT, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(B, FLAT, 1), Fraction.of(1, 4)),
            Timed.of(NamedNote.of(B, FLAT, 1), Fraction.of(3, 8)),

            Timed.of(NamedNote.of(B, FLAT, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(B, FLAT, 1), Fraction.of(1, 4)),
            Timed.of(NamedNote.of(B, FLAT, 1), Fraction.of(3, 8)),

            Timed.of(NamedNote.of(C, 2), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(C, 2), Fraction.of(1, 4)),
            Timed.of(NamedNote.of(C, 2), Fraction.of(3, 8)),

            Timed.of(NamedNote.of(A, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(A, 1), Fraction.of(1, 4)),
            Timed.of(NamedNote.of(A, 1), Fraction.of(3, 8)),

            Timed.of(NamedNote.of(B, FLAT, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(B, FLAT, 1), Fraction.of(1, 4)),
            Timed.of(NamedNote.of(B, FLAT, 1), Fraction.of(3, 8)),

            Timed.of(NamedNote.of(A, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(B, FLAT, 1), Fraction.of(1, 4)),
            Timed.of(NamedNote.of(A, 1), Fraction.of(3, 8)),

            Timed.rest(Fraction.of(1, 8)),
            Timed.of(NamedNote.of(A, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(C, 2), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(B, FLAT, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(B, FLAT, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(A, 1), Fraction.of(1, 8)),

            Timed.of(NamedNote.of(A, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(G, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(F, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(E, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(D, 1), Fraction.of(1, 4)),

            Timed.of(NamedNote.of(A, 1), Fraction.of(3, 8)),
            Timed.of(NamedNote.of(G, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(F, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(G, 1), Fraction.of(1, 8)),

            Timed.of(NamedNote.of(A, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(G, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(A, 1), Fraction.of(1, 4)),
            Timed.of(NamedNote.of(E, 1), Fraction.of(1, 4)),

            Timed.of(NamedNote.of(G, 1), Fraction.of(3, 8)),
            Timed.of(NamedNote.of(G, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(F, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(E, 1), Fraction.of(1, 8)),

            Timed.of(NamedNote.of(D, 1), Fraction.of(1, 2)),
            Timed.of(NamedNote.of(D, 1), Fraction.of(1, 4)),

            Timed.of(NamedNote.of(G, 1), Fraction.of(3, 8)),
            Timed.of(NamedNote.of(F, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(G, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(D, 1), Fraction.of(1, 8)),

            Timed.of(NamedNote.of(C, SHARP, 1), Fraction.of(3, 4))
        ), sys),

        new Voice.NoteBuilder(List.of(
            Timed.rest(Fraction.of(1, 4)),
            Timed.of(NamedNote.of(B, FLAT, 1), Fraction.of(3, 4)),
            Timed.of(NamedNote.of(B, FLAT, 1), Fraction.of(3, 4)),
            Timed.of(NamedNote.of(A, 1), Fraction.of(3, 4)),
            Timed.of(NamedNote.of(F, 1), Fraction.of(1, 2)),
            Timed.of(NamedNote.of(D, 0), Fraction.of(1, 4)),

            Timed.of(NamedNote.of(G, 0), Fraction.of(3, 8)),
            Timed.of(NamedNote.of(F, 0), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(G, 0), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(D, 0), Fraction.of(1, 8)),

            Timed.of(NamedNote.of(F, 0), Fraction.of(3, 4)),

            Timed.rest(Fraction.of(3, 4)),

            Timed.rest(Fraction.of(1, 4)),
            Timed.of(NamedNote.of(A, 0), Fraction.of(1, 8)),
            Timed.rest(Fraction.of(1, 8)),
            Timed.rest(Fraction.of(1, 4)),

            Timed.rest(Fraction.of(1, 4)),
            Timed.of(NamedNote.of(B, 0), Fraction.of(1, 8)),
            Timed.rest(Fraction.of(1, 8)),
            Timed.rest(Fraction.of(1, 4)),

            Timed.rest(Fraction.of(1, 4)),
            Timed.of(NamedNote.of(F, 0), Fraction.of(1, 8)),
            Timed.rest(Fraction.of(1, 8)),
            Timed.rest(Fraction.of(1, 4)),

            Timed.rest(Fraction.of(1, 4)),
            Timed.of(NamedNote.of(A, 0), Fraction.of(1, 8)),
            Timed.rest(Fraction.of(1, 8)),
            Timed.rest(Fraction.of(1, 4)),

            Timed.rest(Fraction.of(1, 4)),
            Timed.of(NamedNote.of(D, 1), Fraction.of(1, 8)),
            Timed.rest(Fraction.of(1, 8)),
            Timed.rest(Fraction.of(1, 4)),

            Timed.rest(Fraction.of(1, 4)),
            Timed.of(NamedNote.of(E, 1), Fraction.of(1, 8)),
            Timed.rest(Fraction.of(1, 8)),
            Timed.rest(Fraction.of(1, 4)),

            Timed.rest(Fraction.of(1, 4)),
            Timed.of(NamedNote.of(F, 0), Fraction.of(1, 8)),
            Timed.rest(Fraction.of(1, 8)),
            Timed.rest(Fraction.of(1, 4)),

            Timed.rest(Fraction.of(1, 4)),
            Timed.of(NamedNote.of(A, 0), Fraction.of(1, 8)),
            Timed.rest(Fraction.of(1, 8)),
            Timed.rest(Fraction.of(1, 4)),

            Timed.rest(Fraction.of(1, 4)),
            Timed.of(NamedNote.of(B, FLAT, 0), Fraction.of(1, 8)),
            Timed.rest(Fraction.of(1, 8)),
            Timed.rest(Fraction.of(1, 4)),

            Timed.rest(Fraction.of(1, 4)),
            Timed.of(NamedNote.of(B, FLAT, 0), Fraction.of(1, 8)),
            Timed.rest(Fraction.of(1, 8)),
            Timed.rest(Fraction.of(1, 4)),

            Timed.rest(Fraction.of(1, 8)),
            Timed.of(NamedNote.of(B, FLAT, 0), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(D, 1), Fraction.of(1, 8)),
            Timed.rest(Fraction.of(1, 8)),
            Timed.of(NamedNote.of(B, FLAT, 0), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(D, 1), Fraction.of(1, 8)),

            Timed.rest(Fraction.of(1, 8)),
            Timed.of(NamedNote.of(B, FLAT, 0), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(D, 1), Fraction.of(1, 8)),
            Timed.rest(Fraction.of(3, 8)),

            Timed.rest(Fraction.of(1, 8)),
            Timed.of(NamedNote.of(G, 0), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(D, 1), Fraction.of(1, 8)),
            Timed.rest(Fraction.of(1, 8)),
            Timed.of(NamedNote.of(B, FLAT, 0), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(F, 1), Fraction.of(1, 8)),

            Timed.rest(Fraction.of(1, 8)),
            Timed.of(NamedNote.of(G, 1), Fraction.of(5, 8)),

            Timed.of(NamedNote.of(A, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(G, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(G, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(F, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(F, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(E, 1), Fraction.of(1, 8)),

            Timed.of(NamedNote.of(D, 1), Fraction.of(3, 4)),

            Timed.of(NamedNote.of(F, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(F, 1), Fraction.of(1, 4)),
            Timed.of(NamedNote.of(F, 1), Fraction.of(3, 8)),

            Timed.of(NamedNote.of(E, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(E, 1), Fraction.of(1, 4)),
            Timed.of(NamedNote.of(E, 1), Fraction.of(3, 8)),

            Timed.of(NamedNote.of(A, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(A, 1), Fraction.of(1, 4)),
            Timed.of(NamedNote.of(A, 1), Fraction.of(3, 8)),

            Timed.of(NamedNote.of(E, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(B, FLAT, 1), Fraction.of(1, 4)),
            Timed.of(NamedNote.of(E, 1), Fraction.of(3, 8)),

            Timed.of(NamedNote.of(A, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(D, 1), Fraction.of(1, 4)),
            Timed.of(NamedNote.of(A, 1), Fraction.of(3, 8)),

            Timed.of(NamedNote.of(C, SHARP, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(C, SHARP, 1), Fraction.of(1, 4)),
            Timed.of(NamedNote.of(C, SHARP, 1), Fraction.of(3, 8)),

            Timed.rest(Fraction.of(1, 4)),
            Timed.of(NamedNote.of(F, 1), Fraction.of(10, 8)),

            Timed.rest(Fraction.of(1, 4)),
            Timed.of(NamedNote.of(D, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(C, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(D, 1), Fraction.of(1, 8)),
            Timed.rest(Fraction.of(1, 8)),

            Timed.rest(Fraction.of(1, 4)),
            Timed.of(NamedNote.of(D, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(C, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(D, 1), Fraction.of(1, 8)),
            Timed.rest(Fraction.of(1, 8)),

            Timed.rest(Fraction.of(1, 4)),
            Timed.of(NamedNote.of(C, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(B, FLAT, 0), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(C, 1), Fraction.of(1, 8)),
            Timed.rest(Fraction.of(1, 8)),

            Timed.rest(Fraction.of(1, 4)),
            Timed.of(NamedNote.of(C, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(B, FLAT, 0), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(C, 1), Fraction.of(1, 8)),
            Timed.rest(Fraction.of(1, 8)),

            Timed.rest(Fraction.of(1, 8)),
            Timed.of(NamedNote.of(C, 2), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(D, 2), Fraction.of(1, 2)),

            Timed.of(NamedNote.of(B, FLAT, 1), Fraction.of(3, 4))
        ), sys),

        new Voice.NoteBuilder(List.of(
            Timed.of(NamedNote.of(D, 1), Fraction.of(1, 4)),

            Timed.of(NamedNote.of(A, 1), Fraction.of(3, 8)),
            Timed.of(NamedNote.of(G, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(F, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(G, 1), Fraction.of(1, 8)),

            Timed.of(NamedNote.of(A, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(G, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(A, 1), Fraction.of(1, 4)),
            Timed.of(NamedNote.of(E, 1), Fraction.of(1, 4)),

            Timed.of(NamedNote.of(G, 1), Fraction.of(3, 8)),
            Timed.of(NamedNote.of(G, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(F, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(E, 1), Fraction.of(1, 8)),

            Timed.of(NamedNote.of(D, 1), Fraction.of(1, 2)),
            Timed.rest(Fraction.of(1, 4)),

            Timed.of(NamedNote.of(E, -1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(B, FLAT, -1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(B, FLAT, 0), Fraction.of(1, 2)),

            Timed.of(NamedNote.of(C, SHARP, -1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(A, -1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(A, 0), Fraction.of(1, 2)),

            Timed.rest(Fraction.of(3, 4)),

            Timed.of(NamedNote.of(D, -1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(A, -1), Fraction.of(1, 8)),
            Timed.rest(Fraction.of(2, 4)),

            Timed.of(NamedNote.of(G, -1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(F, 0), Fraction.of(1, 8)),
            Timed.rest(Fraction.of(2, 4)),

            Timed.of(NamedNote.of(D, -1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(A, -1), Fraction.of(1, 8)),
            Timed.rest(Fraction.of(2, 4)),

            Timed.of(NamedNote.of(F, -1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(D, 0), Fraction.of(1, 8)),
            Timed.rest(Fraction.of(2, 4)),

            Timed.of(NamedNote.of(G, -1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(D, 0), Fraction.of(1, 8)),
            Timed.rest(Fraction.of(2, 4)),

            Timed.of(NamedNote.of(A, -1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(E, 0), Fraction.of(1, 8)),
            Timed.rest(Fraction.of(2, 4)),

            Timed.of(NamedNote.of(D, -1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(A, -1), Fraction.of(1, 8)),
            Timed.rest(Fraction.of(2, 4)),

            Timed.of(NamedNote.of(F, -1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(D, 0), Fraction.of(1, 8)),
            Timed.rest(Fraction.of(2, 4)),

            Timed.of(NamedNote.of(G, -1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(D, 0), Fraction.of(1, 8)),
            Timed.rest(Fraction.of(2, 4)),

            Timed.of(NamedNote.of(F, -1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(D, 0), Fraction.of(1, 8)),
            Timed.rest(Fraction.of(2, 4)),

            Timed.of(NamedNote.of(E, -1), Fraction.of(1, 8)),
            Timed.rest(Fraction.of(1, 4)),
            Timed.of(NamedNote.of(F, -1), Fraction.of(1, 8)),
            Timed.rest(Fraction.of(1, 4)),

            Timed.of(NamedNote.of(G, -1), Fraction.of(1, 8)),
            Timed.rest(Fraction.of(5, 8)),

            Timed.of(NamedNote.of(B, FLAT, -1), Fraction.of(1, 8)),
            Timed.rest(Fraction.of(1, 4)),
            Timed.of(NamedNote.of(D, 0), Fraction.of(1, 8)),
            Timed.rest(Fraction.of(1, 4)),

            Timed.rest(Fraction.of(1, 8)),
            Timed.of(NamedNote.of(D, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(C, SHARP, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(D, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(C, SHARP, 1), Fraction.of(1, 4)),

            Timed.of(NamedNote.of(F, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(E, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(E, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(D, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(D, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(A, 0), Fraction.of(1, 8)),

            Timed.of(NamedNote.of(D, 0), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(E, FLAT, 0), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(F, SHARP, 0), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(G, 0), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(A, 0), Fraction.of(1, 4)),

            Timed.of(NamedNote.of(G, 0), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(G, 0), Fraction.of(1, 4)),
            Timed.of(NamedNote.of(G, 0), Fraction.of(3, 8)),

            Timed.of(NamedNote.of(G, 0), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(G, 0), Fraction.of(1, 4)),
            Timed.of(NamedNote.of(G, 0), Fraction.of(3, 8)),

            Timed.of(NamedNote.of(G, 0), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(G, 0), Fraction.of(1, 4)),
            Timed.of(NamedNote.of(G, 0), Fraction.of(3, 8)),

            Timed.of(NamedNote.of(F, 0), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(F, 0), Fraction.of(1, 4)),
            Timed.of(NamedNote.of(F, 0), Fraction.of(3, 8)),

            Timed.of(NamedNote.of(E, 0), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(E, 0), Fraction.of(1, 4)),
            Timed.of(NamedNote.of(E, 0), Fraction.of(3, 8)),

            Timed.of(NamedNote.of(G, 0), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(G, 0), Fraction.of(1, 4)),
            Timed.of(NamedNote.of(G, 0), Fraction.of(3, 8)),

            Timed.rest(Fraction.of(1, 8)),
            Timed.of(NamedNote.of(A, 0), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(E, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(D, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(D, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(C, 1), Fraction.of(1, 8)),

            Timed.of(NamedNote.of(C, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(B, FLAT, 0), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(A, 0), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(G, 0), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(F, 0), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(E, 0), Fraction.of(1, 8)),

            Timed.of(NamedNote.of(G, -1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(D, 0), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(B, FLAT, 0), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(A, 0), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(B, FLAT, 0), Fraction.of(1, 8)),
            Timed.rest(Fraction.of(1, 8)),

            Timed.of(NamedNote.of(C, -1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(G, -1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(B, FLAT, 0), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(A, 0), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(B, FLAT, 0), Fraction.of(1, 8)),
            Timed.rest(Fraction.of(1, 8)),

            Timed.of(NamedNote.of(F, -1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(C, 0), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(A, 0), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(G, 0), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(A, 0), Fraction.of(1, 8)),
            Timed.rest(Fraction.of(1, 8)),

            Timed.of(NamedNote.of(B, FLAT, -1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(F, 0), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(A, 0), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(G, 0), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(A, 0), Fraction.of(1, 8)),
            Timed.rest(Fraction.of(1, 8)),

            Timed.rest(Fraction.of(1, 8)),
            Timed.of(NamedNote.of(A, 1), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(B, FLAT, 1), Fraction.of(1, 2)),

            Timed.of(NamedNote.of(G, 1), Fraction.of(3, 4))
        ), sys)
    ));

    public static final Piece HYACINTH = new Piece(new Tempo(Fraction.of(1, 4), Tempo.BPM.of(60)), List.of(
        Voice.NoteBuilder.of(List.of(
            Timed.of(NamedNote.of(A), Fraction.of(1, 4)),
            Timed.of(NamedNote.of(C, SHARP, 2), Fraction.of(1, 4)),
            Timed.of(NamedNote.of(E, 2), Fraction.of(1, 4)),
            Timed.of(NamedNote.of(C, SHARP, 2), Fraction.of(1, 4)),
            Timed.of(NamedNote.of(B), Fraction.of(1, 4)),
            Timed.of(NamedNote.of(A), Fraction.of(1, 4)),
            Timed.of(NamedNote.of(F, SHARP), Fraction.of(1, 4)),
            Timed.of(NamedNote.of(A), Fraction.of(1, 4)),
            Timed.of(NamedNote.of(B), Fraction.of(1, 4)),
            Timed.of(NamedNote.of(B), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(C, SHARP, 2), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(A), Fraction.of(1, 2)),

            Timed.of(NamedNote.of(A), Fraction.of(1, 4)),
            Timed.of(NamedNote.of(C, SHARP, 2), Fraction.of(1, 4)),
            Timed.of(NamedNote.of(E, 2), Fraction.of(1, 4)),
            Timed.of(NamedNote.of(C, SHARP, 2), Fraction.of(1, 4)),
            Timed.of(NamedNote.of(F, SHARP, 2), Fraction.of(1, 4)),
            Timed.of(NamedNote.of(E, 2), Fraction.of(1, 4)),
            Timed.of(NamedNote.of(B), Fraction.of(1, 4)),
            Timed.of(NamedNote.of(C, SHARP, 2), Fraction.of(1, 4)),
            Timed.of(NamedNote.of(E, 2), Fraction.of(1, 4)),
            Timed.of(NamedNote.of(E, 2), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(B), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(E, 2), Fraction.of(1, 2)),

            Timed.of(NamedNote.of(B), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(C, SHARP, 2), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(D, 2), Fraction.of(1, 4)),
            Timed.of(NamedNote.of(B), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(C, SHARP, 2), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(D, 2), Fraction.of(1, 4)),
            Timed.of(NamedNote.of(E, 2), Fraction.of(1, 4 * 2)),
            Timed.rest(Fraction.of(1, 4 * 2)),
            Timed.of(NamedNote.of(E, 2), Fraction.of(1, 4 * 2)),
            Timed.rest(Fraction.of(1, 4 * 2)),
            Timed.of(NamedNote.of(C, SHARP, 2), Fraction.of(1, 2)),
            Timed.of(NamedNote.of(A), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(B), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(C, SHARP, 2), Fraction.of(1, 4)),
            Timed.of(NamedNote.of(A), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(B), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(C, SHARP, 2), Fraction.of(1, 4)),
            Timed.of(NamedNote.of(C, SHARP, 2), Fraction.of(1, 4)),
            Timed.of(NamedNote.of(B), Fraction.of(1, 4)),
            Timed.of(NamedNote.of(F, SHARP), Fraction.of(1, 2)),

            Timed.of(NamedNote.of(E), Fraction.of(1, 4)),
            Timed.of(NamedNote.of(A), Fraction.of(1, 4)),
            Timed.of(NamedNote.of(B), Fraction.of(1, 4)),
            Timed.of(NamedNote.of(C, SHARP, 2), Fraction.of(1, 4)),
            Timed.of(NamedNote.of(B), Fraction.of(1, 4)),
            Timed.of(NamedNote.of(A), Fraction.of(1, 4)),
            Timed.of(NamedNote.of(F, SHARP), Fraction.of(1, 4)),
            Timed.of(NamedNote.of(A), Fraction.of(1, 4)),
            Timed.of(NamedNote.of(B), Fraction.of(1, 4)),
            Timed.of(NamedNote.of(B), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(C, SHARP, 2), Fraction.of(1, 8)),
            Timed.of(NamedNote.of(A), Fraction.of(3, 2))
        ), sys),

        Voice.NoteBuilder.of(List.of(
            Timed.of(NamedNote.of(E), Fraction.of(1,4)),
            Timed.of(NamedNote.of(A), Fraction.of(1,4)),
            Timed.of(NamedNote.of(C, SHARP, 2), Fraction.of(1,4)),
            Timed.of(NamedNote.of(A), Fraction.of(1,4)),
            Timed.of(NamedNote.of(E), Fraction.of(1,4)),
            Timed.of(NamedNote.of(F, SHARP), Fraction.of(1,8)),
            Timed.of(NamedNote.of(G, SHARP), Fraction.of(1,8)),
            Timed.of(NamedNote.of(F, SHARP), Fraction.of(1,4)),
            Timed.of(NamedNote.of(E), Fraction.of(1,4)),
            Timed.of(NamedNote.of(E), Fraction.of(1,4)),
            Timed.of(NamedNote.of(A), Fraction.of(1,8)),
            Timed.of(NamedNote.of(G, SHARP), Fraction.of(1,8)),
            Timed.of(NamedNote.of(E), Fraction.of(1,2)),

            Timed.of(NamedNote.of(E), Fraction.of(1,4)),
            Timed.of(NamedNote.of(A), Fraction.of(1,4)),
            Timed.of(NamedNote.of(B), Fraction.of(1,4)),
            Timed.of(NamedNote.of(A), Fraction.of(1,4)),
            Timed.of(NamedNote.of(A), Fraction.of(1,8)),
            Timed.of(NamedNote.of(G, SHARP), Fraction.of(1,8)),
            Timed.of(NamedNote.of(A), Fraction.of(1,4)),
            Timed.of(NamedNote.of(G, SHARP), Fraction.of(1,4)),
            Timed.of(NamedNote.of(A), Fraction.of(1,4)),
            Timed.of(NamedNote.of(G, SHARP), Fraction.of(1,4)),
            Timed.of(NamedNote.of(G, SHARP), Fraction.of(1,8)),
            Timed.of(NamedNote.of(A), Fraction.of(1,8)),
            Timed.of(NamedNote.of(G, SHARP), Fraction.of(1,2)),

            Timed.of(NamedNote.of(E), Fraction.of(1,4)),
            Timed.of(NamedNote.of(G, SHARP), Fraction.of(1,4)),
            Timed.of(NamedNote.of(E), Fraction.of(1,4)),
            Timed.of(NamedNote.of(G, SHARP), Fraction.of(1,4)),
            Timed.of(NamedNote.of(A), Fraction.of(1,8)),
            Timed.of(NamedNote.of(B), Fraction.of(1,8)),
            Timed.of(NamedNote.of(C, SHARP, 2), Fraction.of(1,4)),
            Timed.of(NamedNote.of(A), Fraction.of(1,8)),
            Timed.of(NamedNote.of(B), Fraction.of(1,8)),
            Timed.of(NamedNote.of(C, SHARP, 2), Fraction.of(1,4)),
            Timed.of(NamedNote.of(F, SHARP), Fraction.of(1,4)),
            Timed.of(NamedNote.of(F, SHARP), Fraction.of(1,4)),
            Timed.of(NamedNote.of(F, SHARP), Fraction.of(1,4)),
            Timed.of(NamedNote.of(F, SHARP), Fraction.of(1,8)),
            Timed.of(NamedNote.of(G, SHARP), Fraction.of(1,8)),
            Timed.of(NamedNote.of(F, SHARP), Fraction.of(1,8)),
            Timed.of(NamedNote.of(E), Fraction.of(1,8)),
            Timed.of(NamedNote.of(D), Fraction.of(1,8)),
            Timed.of(NamedNote.of(C, SHARP), Fraction.of(1,8)),
            Timed.of(NamedNote.of(B, 0), Fraction.of(1,2)),

            Timed.of(NamedNote.of(B, 0), Fraction.of(1,4)),
            Timed.of(NamedNote.of(E), Fraction.of(1,4)),
            Timed.of(NamedNote.of(D), Fraction.of(1,4)),
            Timed.of(NamedNote.of(E), Fraction.of(1,4)),
            Timed.of(NamedNote.of(F, SHARP), Fraction.of(1,8)),
            Timed.of(NamedNote.of(E), Fraction.of(1,8)),
            Timed.of(NamedNote.of(F, SHARP), Fraction.of(1,4)),
            Timed.of(NamedNote.of(D), Fraction.of(1,4)),
            Timed.of(NamedNote.of(F, SHARP), Fraction.of(1,8)),
            Timed.of(NamedNote.of(G, SHARP), Fraction.of(1,8)),
            Timed.of(NamedNote.of(A), Fraction.of(1,4)),
            Timed.of(NamedNote.of(G, SHARP), Fraction.of(1,4)),
            Timed.of(NamedNote.of(A), Fraction.of(3,2))
        ), sys),

        Voice.NoteBuilder.of(List.of(
            Timed.of(NamedNote.of(C, SHARP), Fraction.of(1,4)),
            Timed.of(NamedNote.of(E), Fraction.of(1,4)),
            Timed.of(NamedNote.of(A), Fraction.of(1,4)),
            Timed.of(NamedNote.of(E), Fraction.of(1,4)),
            Timed.of(NamedNote.of(D), Fraction.of(1,4)),
            Timed.of(NamedNote.of(C, SHARP), Fraction.of(1,4)),
            Timed.of(NamedNote.of(D), Fraction.of(1,4)),
            Timed.of(NamedNote.of(E), Fraction.of(1,4)),
            Timed.of(NamedNote.of(D), Fraction.of(1,8)),
            Timed.of(NamedNote.of(C, SHARP), Fraction.of(1,8)),
            Timed.of(NamedNote.of(B, 0), Fraction.of(1,4)),
            Timed.of(NamedNote.of(C, SHARP), Fraction.of(1,2)),

            Timed.of(NamedNote.of(C, SHARP), Fraction.of(1,4)),
            Timed.of(NamedNote.of(E), Fraction.of(1,4)),
            Timed.of(NamedNote.of(E), Fraction.of(1,4)),
            Timed.of(NamedNote.of(E), Fraction.of(1,4)),
            Timed.of(NamedNote.of(D), Fraction.of(1,4)),
            Timed.of(NamedNote.of(E), Fraction.of(1,4)),
            Timed.of(NamedNote.of(D), Fraction.of(1,4)),
            Timed.of(NamedNote.of(E), Fraction.of(1,8)),
            Timed.of(NamedNote.of(F, SHARP), Fraction.of(1,8)),
            Timed.of(NamedNote.of(E), Fraction.of(1,4)),
            Timed.of(NamedNote.of(E), Fraction.of(1,8)),
            Timed.of(NamedNote.of(F, SHARP), Fraction.of(1,8)),
            Timed.of(NamedNote.of(B, 0), Fraction.of(1,2)),

            Timed.of(NamedNote.of(E), Fraction.of(1,4)),
            Timed.of(NamedNote.of(E), Fraction.of(1,4)),
            Timed.of(NamedNote.of(E), Fraction.of(1,4)),
            Timed.of(NamedNote.of(E), Fraction.of(1,4)),
            Timed.of(NamedNote.of(E), Fraction.of(1,4*2)),
            Timed.rest(Fraction.of(1,4*2)),
            Timed.of(NamedNote.of(E), Fraction.of(1,4*2)),
            Timed.rest(Fraction.of(1,4*2)),
            Timed.of(NamedNote.of(E), Fraction.of(1,4)),
            Timed.of(NamedNote.of(E, SHARP), Fraction.of(1,4)),
            Timed.of(NamedNote.of(F, SHARP), Fraction.of(1,4)),
            Timed.of(NamedNote.of(C, SHARP), Fraction.of(1,4)),
            Timed.of(NamedNote.of(C, SHARP), Fraction.of(1,4)),
            Timed.of(NamedNote.of(C, SHARP), Fraction.of(1,8)),
            Timed.of(NamedNote.of(B, 0), Fraction.of(1,8)),
            Timed.of(NamedNote.of(A, 0), Fraction.of(1,4)),
            Timed.of(NamedNote.of(A, 0), Fraction.of(1,4)),
            Timed.of(NamedNote.of(B, 0), Fraction.of(1,8)),
            Timed.of(NamedNote.of(C, SHARP), Fraction.of(1,8)),
            Timed.of(NamedNote.of(B, 0), Fraction.of(1,8)),
            Timed.of(NamedNote.of(A, 0), Fraction.of(1,8)),

            Timed.of(NamedNote.of(G, SHARP, 0), Fraction.of(1,4)),
            Timed.of(NamedNote.of(A, 0), Fraction.of(1,4)),
            Timed.of(NamedNote.of(G, SHARP, 0), Fraction.of(1,4)),
            Timed.of(NamedNote.of(A, 0), Fraction.of(1,4)),
            Timed.of(NamedNote.of(A, 0), Fraction.of(1,4)),
            Timed.of(NamedNote.of(A, 0), Fraction.of(1,4)),
            Timed.of(NamedNote.of(A, 0), Fraction.of(1,4)),
            Timed.of(NamedNote.of(D), Fraction.of(1,4)),
            Timed.of(NamedNote.of(F, SHARP), Fraction.of(1,4)),
            Timed.of(NamedNote.of(E), Fraction.of(1,8)),
            Timed.of(NamedNote.of(D), Fraction.of(1,8)),
            Timed.of(NamedNote.of(C, SHARP), Fraction.of(3,2))
        ), sys),

        Voice.NoteBuilder.of(List.of(
            Timed.of(NamedNote.of(A, -1), Fraction.of(9,8)),
            Timed.of(NamedNote.of(G, SHARP, 0), Fraction.of(1,8)),
            Timed.of(NamedNote.of(F, SHARP, 0), Fraction.of(1,8)),
            Timed.of(NamedNote.of(E, 0), Fraction.of(1,8)),
            Timed.of(NamedNote.of(D, 0), Fraction.of(1,4)),
            Timed.of(NamedNote.of(C, SHARP, 0), Fraction.of(1,4)),
            Timed.of(NamedNote.of(D, 0), Fraction.of(1,4)),
            Timed.of(NamedNote.of(E, 0), Fraction.of(1,4)),
            Timed.of(NamedNote.of(A, -1), Fraction.of(1,2)),

            Timed.of(NamedNote.of(A, -1), Fraction.of(1,2)),
            Timed.of(NamedNote.of(G, SHARP, -1), Fraction.of(1,4)),
            Timed.of(NamedNote.of(A, -1), Fraction.of(1,4)),
            Timed.of(NamedNote.of(D, 0), Fraction.of(1,4)),
            Timed.of(NamedNote.of(C, SHARP, 0), Fraction.of(1,4)),
            Timed.of(NamedNote.of(B, -1), Fraction.of(1,4)),
            Timed.of(NamedNote.of(A, -1), Fraction.of(1,4)),
            Timed.of(NamedNote.of(E, 0), Fraction.of(3,8)),
            Timed.of(NamedNote.of(D, SHARP, 0), Fraction.of(1,8)),
            Timed.of(NamedNote.of(E, 0), Fraction.of(1,2)),

            Timed.of(NamedNote.of(G, SHARP, 0), Fraction.of(1,4)),
            Timed.of(NamedNote.of(B), Fraction.of(1,4)),
            Timed.of(NamedNote.of(G, SHARP, 0), Fraction.of(1,4)),
            Timed.of(NamedNote.of(B), Fraction.of(1,4)),
            Timed.of(NamedNote.of(C, SHARP), Fraction.of(1,4*2)),
            Timed.rest(Fraction.of(1,4*2)),
            Timed.of(NamedNote.of(C, SHARP), Fraction.of(1,4*2)),
            Timed.rest(Fraction.of(1,4*2)),
            Timed.of(NamedNote.of(A, 0), Fraction.of(1,4)),
            Timed.of(NamedNote.of(G, SHARP, 0), Fraction.of(1,4)),
            Timed.of(NamedNote.of(F, SHARP, 0), Fraction.of(1,4)),
            Timed.of(NamedNote.of(A, 0), Fraction.of(1,4)),
            Timed.of(NamedNote.of(F, SHARP, 0), Fraction.of(1,4)),
            Timed.of(NamedNote.of(A, 0), Fraction.of(1,4)),
            Timed.of(NamedNote.of(D, 0), Fraction.of(3,8)),
            Timed.of(NamedNote.of(D, 0), Fraction.of(1,8)),
            Timed.of(NamedNote.of(D, SHARP, 0), Fraction.of(1,2)),

            Timed.of(NamedNote.of(E, 0), Fraction.of(1,8)),
            Timed.of(NamedNote.of(D, 0), Fraction.of(1,8)),
            Timed.of(NamedNote.of(C, SHARP, 0), Fraction.of(1,4)),
            Timed.of(NamedNote.of(B, -1), Fraction.of(1,4)),
            Timed.of(NamedNote.of(A, -1), Fraction.of(1,4)),
            Timed.of(NamedNote.of(D, 0), Fraction.of(1,8)),
            Timed.of(NamedNote.of(C, SHARP, 0), Fraction.of(1,8)),
            Timed.of(NamedNote.of(D, 0), Fraction.of(1,4)),
            Timed.of(NamedNote.of(D, 0), Fraction.of(1,4)),
            Timed.of(NamedNote.of(D, 0), Fraction.of(1,4)),
            Timed.of(NamedNote.of(D, SHARP, 0), Fraction.of(1,4)),
            Timed.of(NamedNote.of(E, 0), Fraction.of(1,4)),
            Timed.of(NamedNote.of(A, -1), Fraction.of(3,2))
        ), sys)
    ));

    private Pieces() {}
}
