package com.aoc.jdmcmahan.gearratios;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PartNumberTest {

    @Test
    void testPartNumbers_withExampleInput() throws IOException {
        try (InputStream input = PartNumberTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            PartNumberParser parser = new PartNumberParser();
            List<Integer> partNumbers = parser.parse(input);

            int result = partNumbers.stream()
                    .mapToInt(Integer::intValue)
                    .sum();

            assertEquals(4361, result);
        }
    }

    @Test
    void testPartNumbers_withChallengeInput() throws IOException {
        try (InputStream input = PartNumberTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            PartNumberParser parser = new PartNumberParser();
            List<Integer> partNumbers = parser.parse(input);

            int result = partNumbers.stream()
                    .mapToInt(Integer::intValue)
                    .sum();

            assertEquals(537732, result);
        }
    }

    @Test
    void testGearRatios_withExampleInput() throws IOException {
        try (InputStream input = PartNumberTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            GearRatioParser parser = new GearRatioParser();
            List<Integer> gearRatios = parser.parse(input);

            int result = gearRatios.stream()
                    .mapToInt(Integer::intValue)
                    .sum();

            assertEquals(467835, result);
        }
    }

    @Test
    void testGearRatios_withChallengeInput() throws IOException {
        try (InputStream input = PartNumberTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            GearRatioParser parser = new GearRatioParser();
            List<Integer> gearRatios = parser.parse(input);

            int result = gearRatios.stream()
                    .mapToInt(Integer::intValue)
                    .sum();

            assertEquals(84883664, result);
        }
    }
}