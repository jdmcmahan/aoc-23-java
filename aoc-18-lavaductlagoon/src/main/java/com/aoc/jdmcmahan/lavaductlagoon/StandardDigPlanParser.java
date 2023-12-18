package com.aoc.jdmcmahan.lavaductlagoon;

import com.aoc.jdmcmahan.lavaductlagoon.model.Direction;
import com.aoc.jdmcmahan.lavaductlagoon.model.Lagoon;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StandardDigPlanParser {

    private static final Pattern DIG_PLAN_PATTERN = Pattern.compile("^([UDLR])\\s+(\\d+)\\s+\\((#[0-9a-f]+)\\)$");

    public Lagoon parse(InputStream input) {
        LineNumberReader reader = new LineNumberReader(new InputStreamReader(input));
        Lagoon.DigPlan digger = Lagoon.plan();

        reader.lines()
                .forEach(row -> {
                    Matcher matcher = DIG_PLAN_PATTERN.matcher(row);
                    if (!matcher.find()) {
                        throw new IllegalArgumentException("Invalid input: " + row);
                    }

                    String directionLabel = matcher.group(1);
                    Direction direction = switch (directionLabel) {
                        case "U" -> Direction.UP;
                        case "D" -> Direction.DOWN;
                        case "L" -> Direction.LEFT;
                        case "R" -> Direction.RIGHT;
                        default -> throw new IllegalStateException("Unrecognized direction: " + directionLabel);
                    };

                    int distance = Integer.parseInt(matcher.group(2));

                    digger.trench(direction, distance);
                });

        return digger.dig();
    }
}
