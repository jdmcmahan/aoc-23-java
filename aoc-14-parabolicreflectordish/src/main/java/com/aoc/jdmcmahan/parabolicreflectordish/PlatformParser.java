package com.aoc.jdmcmahan.parabolicreflectordish;

import com.aoc.jdmcmahan.parabolicreflectordish.model.Platform;
import com.aoc.jdmcmahan.parabolicreflectordish.model.Position;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class PlatformParser {

    public Platform parse(InputStream input) {
        LineNumberReader reader = new LineNumberReader(new InputStreamReader(input));
        Platform.PlatformBuilder builder = Platform.builder();

        reader.lines()
                .forEach(row -> {
                    int y = reader.getLineNumber() - 1;
                    for (int x = 0; x < row.length(); x++) {
                        char label = row.charAt(x);
                        Position position = Position.valueOf(x, y);

                        if (label == 'O') {
                            builder.roundRockPosition(position);
                        } else if (label == '#') {
                            builder.cubeRockPosition(position);
                        }
                    }
                });

        return builder.build();
    }
}
