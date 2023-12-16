package com.aoc.jdmcmahan.thefloorwillbelava;

import com.aoc.jdmcmahan.thefloorwillbelava.model.*;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class ContraptionParser {

    public Contraption parse(InputStream input) {
        LineNumberReader reader = new LineNumberReader(new InputStreamReader(input));
        Contraption.ContraptionBuilder builder = Contraption.builder();

        reader.lines()
                .forEach(row -> {
                    int y = reader.getLineNumber() - 1;

                    for (int x = 0; x < row.length(); x++) {
                        Position currentPosition = Position.valueOf(x, y);
                        char currentToken = row.charAt(x);

                        Tile tile = switch (currentToken) {
                            case '.' -> new EmptyTile(currentPosition);
                            case '-' -> new HorizontalSplitter(currentPosition);
                            case '|' -> new VerticalSplitter(currentPosition);
                            case '\\' -> new DescendingMirror(currentPosition);
                            case '/' -> new AscendingMirror(currentPosition);
                            default -> throw new IllegalArgumentException("Unrecognized tile: " + currentToken);
                        };

                        builder.tile(tile);
                    }
                });

        return builder.build();
    }
}
