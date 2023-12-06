package com.aoc.jdmcmahan.ifyougiveaseedafertilizer;

import com.aoc.jdmcmahan.ifyougiveaseedafertilizer.model.Almanac;
import com.aoc.jdmcmahan.ifyougiveaseedafertilizer.model.Range;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class AlmanacTest {

    @Test
    void testLocation_withExampleInput() throws IOException {
        try (InputStream input = AlmanacTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            AlmanacParser parser = new AlmanacParser();
            Almanac almanac = parser.parse(input, new AlmanacParser.SingleSeedRangeParser());

            long result = almanac.getLocationRanges().stream()
                    .mapToLong(Range::lowerBound)
                    .min()
                    .orElse(-1);

            assertEquals(35, result);
        }
    }

    @Test
    void testLocation_withChallengeInput() throws IOException {
        try (InputStream input = AlmanacTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            AlmanacParser parser = new AlmanacParser();
            Almanac almanac = parser.parse(input, new AlmanacParser.SingleSeedRangeParser());

            long result = almanac.getLocationRanges().stream()
                    .mapToLong(Range::lowerBound)
                    .min()
                    .orElse(-1);

            assertEquals(214922730, result);
        }
    }

    @Test
    void testBountifulLocation_withExampleInput() throws IOException {
        try (InputStream input = AlmanacTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            AlmanacParser parser = new AlmanacParser();
            Almanac almanac = parser.parse(input, new AlmanacParser.BountifulSeedRangeParser());

            long result = almanac.getLocationRanges().stream()
                    .mapToLong(Range::lowerBound)
                    .min()
                    .orElse(-1);

            assertEquals(46, result);
        }
    }

    @Test
    void testBountifulLocation_withChallengeInput() throws IOException {
        try (InputStream input = AlmanacTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            AlmanacParser parser = new AlmanacParser();
            Almanac almanac = parser.parse(input, new AlmanacParser.BountifulSeedRangeParser());

            long result = almanac.getLocationRanges().stream()
                    .mapToLong(Range::lowerBound)
                    .min()
                    .orElse(-1);

            assertEquals(148041808, result);
        }
    }
}