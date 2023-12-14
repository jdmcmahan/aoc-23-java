package com.aoc.jdmcmahan.parabolicreflectordish;

import com.aoc.jdmcmahan.parabolicreflectordish.model.Platform;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class TiltLoadTest {

    @Test
    void testTiltLoad_withExampleInput() throws IOException {
        try (InputStream input = TiltLoadTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            PlatformParser parser = new PlatformParser();
            Platform platform = parser.parse(input);

            platform.tiltNorth();
            int result = platform.load();

            assertEquals(136, result);
        }
    }

    @Test
    void testTiltLoad_withChallengeInput() throws IOException {
        try (InputStream input = TiltLoadTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            PlatformParser parser = new PlatformParser();
            Platform platform = parser.parse(input);

            platform.tiltNorth();
            int result = platform.load();

            assertEquals(107053, result);
        }
    }

    @Test
    void testSpinCycle_withExampleInput() throws IOException {
        try (InputStream input = TiltLoadTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            PlatformParser parser = new PlatformParser();
            Platform platform = parser.parse(input);

            platform.spinCycle(1000000000);
            int result = platform.load();

            assertEquals(64, result);
        }
    }

    @Test
    void testSpinCycle_withChallengeInput() throws IOException {
        try (InputStream input = TiltLoadTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            PlatformParser parser = new PlatformParser();
            Platform platform = parser.parse(input);

            platform.spinCycle(1000000000);
            int result = platform.load();

            assertEquals(88371, result);
        }
    }
}