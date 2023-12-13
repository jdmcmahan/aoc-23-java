package com.aoc.jdmcmahan.pointofincidence;

import com.aoc.jdmcmahan.pointofincidence.model.Pattern;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReflectionTest {

    @Test
    void testReflections_withExampleInput() throws IOException {
        try (InputStream input = ReflectionTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            PatternsParser parser = new PatternsParser();
            List<Pattern> patterns = parser.parse(input, 0);

            long result = patterns.stream()
                    .mapToLong(ReflectionTest::encodeLinesOfReflection)
                    .sum();

            assertEquals(405, result);
        }
    }

    @Test
    void testReflections_withChallengeInput() throws IOException {
        try (InputStream input = ReflectionTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            PatternsParser parser = new PatternsParser();
            List<Pattern> patterns = parser.parse(input, 0);

            long result = patterns.stream()
                    .mapToLong(ReflectionTest::encodeLinesOfReflection)
                    .sum();

            assertEquals(33520, result);
        }
    }

    @Test
    void testSmudgyReflections_withExampleInput() throws IOException {
        try (InputStream input = ReflectionTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            PatternsParser parser = new PatternsParser();
            List<Pattern> patterns = parser.parse(input, 1);

            long result = patterns.stream()
                    .mapToLong(ReflectionTest::encodeLinesOfReflection)
                    .sum();

            assertEquals(400, result);
        }
    }

    @Test
    void testSmudgyReflections_withChallengeInput() throws IOException {
        try (InputStream input = ReflectionTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            PatternsParser parser = new PatternsParser();
            List<Pattern> patterns = parser.parse(input, 1);

            long result = patterns.stream()
                    .mapToLong(ReflectionTest::encodeLinesOfReflection)
                    .sum();

            assertEquals(34824, result);
        }
    }

    private static int encodeLinesOfReflection(Pattern pattern) {
        int value = 0;

        int columns = (int) Math.floor(pattern.verticalLineOfReflection());
        if (columns >= 0) {
            value += columns;
        }

        int rows = (int) Math.floor(pattern.horizontalLineOfReflection());
        if (rows >= 0) {
            value += 100 * rows;
        }

        return value;
    }
}