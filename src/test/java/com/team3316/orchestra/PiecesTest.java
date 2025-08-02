package com.team3316.orchestra;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PiecesTest {
    @Test
    @DisplayName("Serialization")
    void serialize() throws FileNotFoundException, IOException, ClassNotFoundException {
        final var baos = new ByteArrayOutputStream();
        final byte[] ba;
        try (final var out = new ObjectOutputStream(baos)) {
            out.writeObject(Pieces.OR);
            ba = baos.toByteArray();
        }
        try (final var in = new ObjectInputStream(new ByteArrayInputStream(ba))) {
            Piece p = (Piece) in.readObject();
            assertEquals(Pieces.OR, p);
        }
    }

    @Test
    @DisplayName("Log OR")
    void log() {
        logPiece(Pieces.OR);
    }

    public static void logPiece(Piece piece) {
        System.out.println("=== PIECE ===");
        System.out.println("Tempo: " + piece.tempo().duration() + " = " + piece.tempo().frequency().multiply(60));
        for (final var voice : piece.voices()) {
            System.out.println("=== VOICE (" + voice.getClass().getSimpleName() + ") ===");
            for (final var freq : voice.get().frequencies()) {
                System.out.printf("%d/%d: %f\n", freq.duration().getNumerator(), freq.duration().getDenominator(), freq.frequency());
            }
        }
        System.out.println("======");
    }
}
