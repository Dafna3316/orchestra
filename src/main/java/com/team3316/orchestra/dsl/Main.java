package com.team3316.orchestra.dsl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.commons.io.FilenameUtils;
import org.antlr.v4.runtime.CharStreams;

import com.team3316.orchestra.Piece;
import com.team3316.orchestra.antlr.PieceLexer;
import com.team3316.orchestra.antlr.PieceParser;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            outputPiece(new PieceLexer(CharStreams.fromStream(System.in)), new ObjectOutputStream(System.out));
            return;
        }

        for (var file : args) {
            outputPiece(new PieceLexer(CharStreams.fromFileName(file)), new ObjectOutputStream(new FileOutputStream(FilenameUtils.removeExtension(file))));
        }
    }

    public static void outputPiece(PieceLexer in, ObjectOutputStream out) throws IOException {
        var tokens = new CommonTokenStream(in);
        var parser = new PieceParser(tokens);
        var piece = parser.piece().accept(new PieceBuildVisitor());
        if (piece instanceof Piece p) {
            out.writeObject(p);
            out.close();
        } else {
            System.err.println("Parsing didn't return piece! (returned " + piece.getClass().getName() + ")");
            System.exit(1);
        }
    }
}
