package com.aoc.jdmcmahan.pipemaze;

import com.aoc.jdmcmahan.pipemaze.model.*;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class PipeMazeParser {

    public PipeMaze parse(InputStream input) {
        LineNumberReader reader = new LineNumberReader(new InputStreamReader(input));
        PipeMaze.PipeMazeBuilder builder = PipeMaze.builder();

        reader.lines()
                .forEach(row -> {
                    int y = reader.getLineNumber();
                    for (int x = 0; x < row.length(); x++) {
                        char label = row.charAt(x);
                        if (label == '.') {
                            continue;
                        }

                        Position position = Position.valueOf(x, y);

                        if (label == 'S') {
                            builder.start(position);
                        } else {
                            Pipe pipe = switch (label) {
                                case '|' -> new VerticalPipe(position);
                                case '-' -> new HorizontalPipe(position);
                                case 'L' -> new NorthEastBendPipe(position);
                                case 'J' -> new NorthWestBendPipe(position);
                                case '7' -> new SouthWestBendPipe(position);
                                case 'F' -> new SouthEastBendPipe(position);
                                default -> throw new IllegalArgumentException("Unrecognized pipe: " + label);
                            };

                            builder.pipe(pipe);
                        }
                    }
                });

        return builder.build();
    }
}
