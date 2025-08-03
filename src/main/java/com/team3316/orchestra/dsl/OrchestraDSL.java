package com.team3316.orchestra.dsl;

import org.antlr.v4.runtime.CommonTokenStream;
import org.jetbrains.annotations.Nullable;

import com.team3316.orchestra.Piece;
import com.team3316.orchestra.antlr.PieceLexer;
import com.team3316.orchestra.antlr.PieceParser;

/**
 * Utility class for compiling the DSL.
 */
public final class OrchestraDSL {
    /**
     * Read a piece from an existing lexer.
     * @param in Lexer
     * @return Parsed piece, or null on error
     */
    public static @Nullable Piece readPiece(PieceLexer in) {
        final var errors = new SyntaxErrorCounter();
        in.addErrorListener(errors);
        final var tokens = new CommonTokenStream(in);
        final var parser = new PieceParser(tokens);
        parser.addErrorListener(errors);
        final var piece = parser.piece().accept(new PieceBuildVisitor());

        if (errors.getErrors() > 0) return null;
        if (piece instanceof Piece p) return p;
        return null;
    }

    private OrchestraDSL() {}
}
