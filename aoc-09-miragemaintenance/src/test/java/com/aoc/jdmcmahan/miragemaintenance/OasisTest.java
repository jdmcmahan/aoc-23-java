package com.aoc.jdmcmahan.miragemaintenance;

import com.aoc.jdmcmahan.miragemaintenance.model.History;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OasisTest {

    @Test
    void testNextValuePrediction_withExampleInput() throws IOException {
        try (InputStream input = OasisTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            OasisParser parser = new OasisParser();
            List<History> histories = parser.parse(input);

            long result = histories.stream()
                    .mapToLong(History::predictNext)
                    .sum();

            assertEquals(114, result);
        }
    }

    @Test
    void testNextValuePrediction_withChallengeInput() throws IOException {
        try (InputStream input = OasisTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            OasisParser parser = new OasisParser();
            List<History> histories = parser.parse(input);

            long result = histories.stream()
                    .mapToLong(History::predictNext)
                    .sum();

            assertEquals(1901217887, result);
        }
    }

    @Test
    void testPreviousValuePrediction_withExampleInput() throws IOException {
        try (InputStream input = OasisTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            OasisParser parser = new OasisParser();
            List<History> histories = parser.parse(input);

            long result = histories.stream()
                    .mapToLong(History::predictPrevious)
                    .sum();

            assertEquals(2, result);
        }
    }

    @Test
    void testPreviousValuePrediction_withChallengeInput() throws IOException {
        try (InputStream input = OasisTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            OasisParser parser = new OasisParser();
            List<History> histories = parser.parse(input);

            long result = histories.stream()
                    .mapToLong(History::predictPrevious)
                    .sum();

            assertEquals(905, result);
        }
    }
}