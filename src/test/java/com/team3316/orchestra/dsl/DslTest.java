package com.team3316.orchestra.dsl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.team3316.orchestra.Piece;
import com.team3316.orchestra.PiecesTest;
import com.team3316.orchestra.antlr.PieceLexer;
import com.team3316.orchestra.antlr.PieceParser;

public class DslTest {
    private final String input = """
        \\tuning "Equal31" a' 440
        \\storage note
        \\tempo 4 = 124
        \\relative c' {
            c4 d e c |
            c d e c |
            e f g2 |
            e4 f g2 |
            g8 a g f e4 c |
            g'8 a g f e4 c |
            c g c2 |
            c4 g c2
            %% Line comment
            %{
            block
            comment
            %}
        }
        \\\\
        {
            e'8 e'16 b e'8 fis' gis'8 gis'16 e' fis'8 a' |
            gis'4 b' gis' b'
        }
        \\\\
        \\relative c' {
            c4+8 d8 e4. c8 |
            e4 c e4 r |
            d4*3/2 e8 f f e d |
            f1 |
            e4. f8 g4. e8 |
            g4 e g2 |
            f4. g8 a a g f |
            a1
        }
        """;

    @Test
    @DisplayName("Example piece")
    void dslTest() {
        var piece = OrchestraDSL.readPiece(new PieceLexer(CharStreams.fromString(input)));
        assertTrue(piece != null, "Parsing failed");
        PiecesTest.logPiece(piece);
    }
}
