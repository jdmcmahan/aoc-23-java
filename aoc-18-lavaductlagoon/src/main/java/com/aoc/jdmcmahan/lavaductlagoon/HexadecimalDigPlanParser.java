package com.aoc.jdmcmahan.lavaductlagoon;

import com.aoc.jdmcmahan.lavaductlagoon.model.Direction;
import com.aoc.jdmcmahan.lavaductlagoon.model.Lagoon;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HexadecimalDigPlanParser {

    private static final Pattern HEXADECIMAL_DIG_PLAN_PATTERN = Pattern.compile("^[UDLR]\\s+\\d+\\s+\\(#([0-9a-f]+?)([0-9a-f])\\)$");

    public Lagoon parse(InputStream input) {
        LineNumberReader reader = new LineNumberReader(new InputStreamReader(input));
        Lagoon.DigPlan digger = Lagoon.plan();

        reader.lines()
                .forEach(row -> {
                    Matcher matcher = HEXADECIMAL_DIG_PLAN_PATTERN.matcher(row);
                    if (!matcher.find()) {
                        throw new IllegalArgumentException("Invalid input: " + row);
                    }

                    long distance = Long.parseLong(matcher.group(1), 16);

                    String directionLabel = matcher.group(2);
                    Direction direction = switch (directionLabel) {
                        case "0" -> Direction.RIGHT;
                        case "1" -> Direction.DOWN;
                        case "2" -> Direction.LEFT;
                        case "3" -> Direction.UP;
                        default -> throw new IllegalStateException("Unrecognized direction: " + directionLabel);
                    };

                    digger.trench(direction, distance);
                });

        return digger.dig();
    }
}
