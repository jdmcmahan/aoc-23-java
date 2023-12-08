package com.aoc.jdmcmahan.hauntedwasteland;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NetworkNavigationTest {

    @Test
    void testNavigation_withExample1Input() throws IOException {
        try (InputStream input = NetworkNavigationTest.class.getClassLoader().getResourceAsStream("example1.txt")) {
            NetworkParser parser = new NetworkParser();
            Network network = parser.parse(input);

            long result = network.navigator().navigate();
            assertEquals(2, result);
        }
    }

    @Test
    void testNavigation_withExample2Input() throws IOException {
        try (InputStream input = NetworkNavigationTest.class.getClassLoader().getResourceAsStream("example2.txt")) {
            NetworkParser parser = new NetworkParser();
            Network network = parser.parse(input);

            long result = network.navigator().navigate();
            assertEquals(6, result);
        }
    }

    @Test
    void testNavigation_withChallengeInput() throws IOException {
        try (InputStream input = NetworkNavigationTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            NetworkParser parser = new NetworkParser();
            Network network = parser.parse(input);

            long result = network.navigator().navigate();
            assertEquals(13771, result);
        }
    }

    @Test
    void testGhostNavigation_withExample3Input() throws IOException {
        try (InputStream input = NetworkNavigationTest.class.getClassLoader().getResourceAsStream("example3.txt")) {
            NetworkParser parser = new NetworkParser();
            Network network = parser.parse(input);

            long result = network.ghostNavigator().navigate();
            assertEquals(6, result);
        }
    }

    @Test
    void testGhostNavigation_withChallengeInput() throws IOException {
        try (InputStream input = NetworkNavigationTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            NetworkParser parser = new NetworkParser();
            Network network = parser.parse(input);

            long result = network.ghostNavigator().navigate();
            assertEquals(13129439557681L, result);
        }
    }
}