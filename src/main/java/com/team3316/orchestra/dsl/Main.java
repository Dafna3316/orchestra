package com.team3316.orchestra.dsl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.apache.commons.io.FilenameUtils;
import org.antlr.v4.runtime.CharStreams;

import com.team3316.orchestra.antlr.PieceLexer;

// Simple wrapper around the lexer & parser
class Main {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            outputPiece(new PieceLexer(CharStreams.fromStream(System.in)), new ObjectOutputStream(System.out));
            return;
        }

        for (var file : args) {
            outputPiece(new PieceLexer(CharStreams.fromFileName(file)), new ObjectOutputStream(new FileOutputStream(FilenameUtils.removeExtension(file) + ".dbop")));
        }
    }

    public static void outputPiece(PieceLexer in, ObjectOutputStream out) throws IOException {
        var piece = OrchestraDSL.readPiece(in);
        if (piece != null) { // it better be
            out.writeObject(piece);
            out.close();
        } else {
            System.err.println("Parsing failed!");
        }
    }
}
