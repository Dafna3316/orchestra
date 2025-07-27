package com.team3316.orchestra.input;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;

import javax.sound.midi.InvalidMidiDataException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MidiInputTest {
    @Test
    @DisplayName("MIDI over the internet")
    void midiImport() throws InvalidMidiDataException, IOException {
        // Redirect stderr since we want to test it
        ByteArrayOutputStream fakeErr = new ByteArrayOutputStream();
        PrintStream realErr = System.err;
        System.setErr(new PrintStream(fakeErr));

        final var piece = MidiInput.open(new URL("http://www.jsbach.net/midi/1080-c01.mid"));
        for (final var voice : piece.voices()) {
            System.out.println("======");
            for (final var freq : voice.get().frequencies()) {
                System.out.printf("%d/%d: %f\n", freq.duration().getNumerator(), freq.duration().getDenominator(), freq.frequency());
            }
        }

        System.setErr(realErr);
        System.err.write(fakeErr.toByteArray());
        assertTrue(fakeErr.toString().isEmpty(), "Expected no stderr messages");
    }
}
