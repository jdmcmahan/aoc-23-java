package com.aoc.jdmcmahan.lavaductlagoon;

import com.aoc.jdmcmahan.lavaductlagoon.model.Lagoon;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class LagoonFillTest {

    @Test
    void testLagoonArea_withExampleInput() throws IOException {
        try (InputStream input = LagoonFillTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            StandardDigPlanParser parser = new StandardDigPlanParser();
            Lagoon lagoon = parser.parse(input);

            long result = lagoon.getArea();

            assertEquals(62, result);
        }
    }

    @Test
    void testLagoonArea_withChallengeInput() throws IOException {
        try (InputStream input = LagoonFillTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            StandardDigPlanParser parser = new StandardDigPlanParser();
            Lagoon lagoon = parser.parse(input);

            long result = lagoon.getArea();

            assertEquals(40745, result);
        }
    }

    @Test
    void testHexadecimalLagoonArea_withExampleInput() throws IOException {
        try (InputStream input = LagoonFillTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            HexadecimalDigPlanParser parser = new HexadecimalDigPlanParser();
            Lagoon lagoon = parser.parse(input);

            long result = lagoon.getArea();

            assertEquals(952408144115L, result);
        }
    }

    @Test
    void testHexadecimalLagoonArea_withChallengeInput() throws IOException {
        try (InputStream input = LagoonFillTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            HexadecimalDigPlanParser parser = new HexadecimalDigPlanParser();
            Lagoon lagoon = parser.parse(input);

            long result = lagoon.getArea();

            assertEquals(90111113594927L, result);
        }
    }
}