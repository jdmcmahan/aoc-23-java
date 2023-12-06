package com.aoc.jdmcmahan.waitforit;

import com.aoc.jdmcmahan.waitforit.model.Race;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VictoryTest {

    @Test
    void testVictory_withExampleInput() throws IOException {
        try (InputStream input = VictoryTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            RaceParser parser = new RaceParser();
            List<Race> races = parser.parse(input, new RaceParser.TabularTokenizer());

            RaceCalculator calculator = new RaceCalculator();

            long result = races.stream()
                    .mapToInt(calculator::winningOptions)
                    .reduce(1, (i, j) -> i * j);

            assertEquals(288, result);
        }
    }

    @Test
    void testVictory_withChallengeInput() throws IOException {
        try (InputStream input = VictoryTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            RaceParser parser = new RaceParser();
            List<Race> races = parser.parse(input, new RaceParser.TabularTokenizer());

            RaceCalculator calculator = new RaceCalculator();

            long result = races.stream()
                    .mapToInt(calculator::winningOptions)
                    .reduce(1, (i, j) -> i * j);

            assertEquals(449820, result);
        }
    }

    @Test
    void testBigRaceVictory_withExampleInput() throws IOException {
        try (InputStream input = VictoryTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            RaceParser parser = new RaceParser();
            List<Race> races = parser.parse(input, new RaceParser.BadKerningTokenizer());

            RaceCalculator calculator = new RaceCalculator();

            long result = races.stream()
                    .mapToInt(calculator::winningOptions)
                    .reduce(1, (i, j) -> i * j);

            assertEquals(71503, result);
        }
    }

    @Test
    void testBigRaceVictory_withChallengeInput() throws IOException {
        try (InputStream input = VictoryTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            RaceParser parser = new RaceParser();
            List<Race> races = parser.parse(input, new RaceParser.BadKerningTokenizer());

            RaceCalculator calculator = new RaceCalculator();

            long result = races.stream()
                    .mapToInt(calculator::winningOptions)
                    .reduce(1, (i, j) -> i * j);

            assertEquals(42250895, result);
        }
    }
}