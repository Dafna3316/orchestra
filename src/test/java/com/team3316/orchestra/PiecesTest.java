package com.team3316.orchestra;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
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
}
