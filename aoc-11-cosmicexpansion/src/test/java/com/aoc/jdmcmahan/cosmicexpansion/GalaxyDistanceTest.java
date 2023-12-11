package com.aoc.jdmcmahan.cosmicexpansion;

import com.aoc.jdmcmahan.cosmicexpansion.model.Galaxy;
import com.aoc.jdmcmahan.cosmicexpansion.model.Image;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GalaxyDistanceTest {

    @Test
    void testDistances_withExampleInput() throws IOException {
        try (InputStream input = GalaxyDistanceTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            ImageParser parser = new ImageParser();
            Image image = parser.parse(input);

            long result = this.calculateDistances(image, 2);
            assertEquals(374, result);
        }
    }

    @Test
    void testDistances_withChallengeInput() throws IOException {
        try (InputStream input = GalaxyDistanceTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            ImageParser parser = new ImageParser();
            Image image = parser.parse(input);

            long result = this.calculateDistances(image, 2);
            assertEquals(9974721, result);
        }
    }

    @Test
    void testOlderDistances_withExampleInput() throws IOException {
        try (InputStream input = GalaxyDistanceTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            ImageParser parser = new ImageParser();
            Image image = parser.parse(input);

            long result = this.calculateDistances(image, 10);
            assertEquals(1030, result);

            result = this.calculateDistances(image, 100);
            assertEquals(8410, result);
        }
    }

    @Test
    void testOlderDistances_withChallengeInput() throws IOException {
        try (InputStream input = GalaxyDistanceTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            ImageParser parser = new ImageParser();
            Image image = parser.parse(input);

            long result = this.calculateDistances(image, 1000000);
            assertEquals(702770569197L, result);
        }
    }

    private long calculateDistances(Image image, int expansionFactor) {
        List<Galaxy> galaxies = new ArrayList<>(image.getGalaxies());
        long sum = 0;

        for (int i = 0; i < galaxies.size() - 1; i++) {
            for (int j = i + 1; j < galaxies.size(); j++) {
                Galaxy galaxy1 = galaxies.get(i);
                Galaxy galaxy2 = galaxies.get(j);

                sum += image.distance(galaxy1, galaxy2, expansionFactor);
            }
        }

        return sum;
    }
}