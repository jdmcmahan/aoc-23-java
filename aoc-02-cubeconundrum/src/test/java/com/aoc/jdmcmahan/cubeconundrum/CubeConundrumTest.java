package com.aoc.jdmcmahan.cubeconundrum;

import com.aoc.jdmcmahan.cubeconundrum.model.CubeSet;
import com.aoc.jdmcmahan.cubeconundrum.model.Game;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class CubeConundrumTest {

    @Test
    void testCubePossibilities_withExampleInput() throws IOException {
        try (InputStream input = CubeConundrumTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            GameParser parser = new GameParser();

            CubeSet test = CubeSet.builder()
                    .redCount(12)
                    .greenCount(13)
                    .blueCount(14)
                    .build();

            int result = parser.parse(input)
                    .filter(game -> game.possible(test))
                    .mapToInt(Game::getId)
                    .sum();

            assertEquals(8, result);
        }
    }

    @Test
    void testCubePossibilities_withChallengeInput() throws IOException {
        try (InputStream input = CubeConundrumTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            GameParser parser = new GameParser();

            CubeSet test = CubeSet.builder()
                    .redCount(12)
                    .greenCount(13)
                    .blueCount(14)
                    .build();

            int result = parser.parse(input)
                    .filter(game -> game.possible(test))
                    .mapToInt(Game::getId)
                    .sum();

            assertEquals(2879, result);
        }
    }

    @Test
    void testCubeMinimums_withExampleInput() throws IOException {
        try (InputStream input = CubeConundrumTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            GameParser parser = new GameParser();

            int result = parser.parse(input)
                    .map(Game::toMinimum)
                    .mapToInt(CubeSet::getPower)
                    .sum();

            assertEquals(2286, result);
        }
    }

    @Test
    void testCubeMinimums_withChallengeInput() throws IOException {
        try (InputStream input = CubeConundrumTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            GameParser parser = new GameParser();

            int result = parser.parse(input)
                    .map(Game::toMinimum)
                    .mapToInt(CubeSet::getPower)
                    .sum();

            assertEquals(65122, result);
        }
    }
}