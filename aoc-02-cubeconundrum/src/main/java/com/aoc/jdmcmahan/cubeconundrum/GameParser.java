package com.aoc.jdmcmahan.cubeconundrum;

import com.aoc.jdmcmahan.cubeconundrum.model.CubeSet;
import com.aoc.jdmcmahan.cubeconundrum.model.Game;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class GameParser {

    private static final Pattern GAME_TOKEN_PATTERN = Pattern.compile("^Game (\\d+): (.*)$");
    private static final Pattern CUBE_COUNT_PATTERN = Pattern.compile("^(\\d+) (red|blue|green)$");

    public Stream<Game> parse(InputStream input) {
        LineNumberReader reader = new LineNumberReader(new InputStreamReader(input));
        return reader.lines()
                .map(row -> {
                    Matcher gameTokenMatcher = GAME_TOKEN_PATTERN.matcher(row);
                    if (!gameTokenMatcher.find()) {
                        throw new IllegalArgumentException("Invalid input: " + row);
                    }

                    Game.GameBuilder gameBuilder = Game.builder()
                            .id(Integer.parseInt(gameTokenMatcher.group(1)));

                    Arrays.stream(gameTokenMatcher.group(2).split("; "))
                            .map(cubeSetString -> {
                                CubeSet.CubeSetBuilder cubeSetBuilder = CubeSet.builder();

                                Arrays.stream(cubeSetString.split(", "))
                                        .forEach(cubeCountString -> {
                                            Matcher cubeCountMatcher = CUBE_COUNT_PATTERN.matcher(cubeCountString);

                                            if (!cubeCountMatcher.find()) {
                                                throw new IllegalArgumentException("Invalid input: " + cubeCountString);
                                            }

                                            int count = Integer.parseInt(cubeCountMatcher.group(1));

                                            String color = cubeCountMatcher.group(2);
                                            switch (color) {
                                                case "red" -> cubeSetBuilder.redCount(count);
                                                case "blue" -> cubeSetBuilder.blueCount(count);
                                                case "green" -> cubeSetBuilder.greenCount(count);
                                            }
                                        });

                                return cubeSetBuilder.build();
                            })
                            .forEach(gameBuilder::cubeSet);

                    return gameBuilder.build();
                });
    }
}
