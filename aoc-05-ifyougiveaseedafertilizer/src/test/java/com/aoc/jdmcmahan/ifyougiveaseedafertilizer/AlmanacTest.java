package com.aoc.jdmcmahan.ifyougiveaseedafertilizer;

import com.aoc.jdmcmahan.ifyougiveaseedafertilizer.model.Almanac;
import com.aoc.jdmcmahan.ifyougiveaseedafertilizer.model.Location;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class AlmanacTest {

    @Test
    void testLocation_withExampleInput() throws IOException {
        try (InputStream input = AlmanacTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            AlmanacParser parser = new AlmanacParser();
            Almanac almanac = parser.parse(input);

            long result = almanac.getLocations().stream()
                    .mapToLong(Location::getId)
                    .min()
                    .orElse(-1);

            assertEquals(35, result);
        }
    }

    @Test
    void testLocation_withChallengeInput() throws IOException {
        try (InputStream input = AlmanacTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            AlmanacParser parser = new AlmanacParser();
            Almanac almanac = parser.parse(input);

            long result = almanac.getLocations().stream()
                    .mapToLong(Location::getId)
                    .min()
                    .orElse(-1);

            assertEquals(214922730, result);
        }
    }
}