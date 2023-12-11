package com.aoc.jdmcmahan.pipemaze;

import com.aoc.jdmcmahan.pipemaze.model.PipeMaze;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PipeMazeNavigationTest {

    @Test
    void testNavigation_withExample1Input() throws IOException {
        try (InputStream input = PipeMazeNavigationTest.class.getClassLoader().getResourceAsStream("example1.txt")) {
            PipeMazeParser parser = new PipeMazeParser();
            PipeMaze maze = parser.parse(input);

            long result = (long) Math.ceil(maze.loop().size() / 2.0);

            assertEquals(4, result);
        }
    }

    @Test
    void testNavigation_withExample2Input() throws IOException {
        try (InputStream input = PipeMazeNavigationTest.class.getClassLoader().getResourceAsStream("example2.txt")) {
            PipeMazeParser parser = new PipeMazeParser();
            PipeMaze maze = parser.parse(input);

            long result = (long) Math.ceil(maze.loop().size() / 2.0);

            assertEquals(4, result);
        }
    }

    @Test
    void testNavigation_withExample3Input() throws IOException {
        try (InputStream input = PipeMazeNavigationTest.class.getClassLoader().getResourceAsStream("example3.txt")) {
            PipeMazeParser parser = new PipeMazeParser();
            PipeMaze maze = parser.parse(input);

            long result = (long) Math.ceil(maze.loop().size() / 2.0);

            assertEquals(8, result);
        }
    }

    @Test
    void testNavigation_withChallengeInput() throws IOException {
        try (InputStream input = PipeMazeNavigationTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            PipeMazeParser parser = new PipeMazeParser();
            PipeMaze maze = parser.parse(input);

            long result = (long) Math.ceil(maze.loop().size() / 2.0);

            assertEquals(6649, result);
        }
    }

    @Test
    void testEnclosure_withExample1Input() throws IOException {
        try (InputStream input = PipeMazeNavigationTest.class.getClassLoader().getResourceAsStream("example1.txt")) {
            PipeMazeParser parser = new PipeMazeParser();
            PipeMaze maze = parser.parse(input);

            long result = maze.area();

            assertEquals(1, result);
        }
    }

    @Test
    void testEnclosure_withExample4Input() throws IOException {
        try (InputStream input = PipeMazeNavigationTest.class.getClassLoader().getResourceAsStream("example4.txt")) {
            PipeMazeParser parser = new PipeMazeParser();
            PipeMaze maze = parser.parse(input);

            long result = maze.area();

            assertEquals(4, result);
        }
    }

    @Test
    void testEnclosure_withExample5Input() throws IOException {
        try (InputStream input = PipeMazeNavigationTest.class.getClassLoader().getResourceAsStream("example5.txt")) {
            PipeMazeParser parser = new PipeMazeParser();
            PipeMaze maze = parser.parse(input);

            long result = maze.area();

            assertEquals(8, result);
        }
    }

    @Test
    void testEnclosure_withExample6Input() throws IOException {
        try (InputStream input = PipeMazeNavigationTest.class.getClassLoader().getResourceAsStream("example6.txt")) {
            PipeMazeParser parser = new PipeMazeParser();
            PipeMaze maze = parser.parse(input);

            long result = maze.area();

            assertEquals(10, result);
        }
    }

    @Test
    void testEnclosure_withChallengeInput() throws IOException {
        try (InputStream input = PipeMazeNavigationTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            PipeMazeParser parser = new PipeMazeParser();
            PipeMaze maze = parser.parse(input);

            long result = maze.area();

            assertEquals(601, result);
        }
    }
}