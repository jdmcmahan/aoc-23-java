package com.aoc.jdmcmahan.lenslibrary;

import com.aoc.jdmcmahan.lenslibrary.model.Box;
import com.aoc.jdmcmahan.lenslibrary.model.InitializationSequence;
import com.aoc.jdmcmahan.lenslibrary.model.Lens;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HashTest {

    @Test
    void testHash_withExampleInput() throws IOException {
        try (InputStream input = HashTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            InitializationSequenceParser parser = new InitializationSequenceParser();
            InitializationSequence initializationSequence = parser.parse(input);

            int result = initializationSequence.steps().stream()
                    .mapToInt(InitializationSequence::hash)
                    .sum();

            assertEquals(1320, result);
        }
    }

    @Test
    void testHash_withChallengeInput() throws IOException {
        try (InputStream input = HashTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            InitializationSequenceParser parser = new InitializationSequenceParser();
            InitializationSequence initializationSequence = parser.parse(input);

            int result = initializationSequence.steps().stream()
                    .mapToInt(InitializationSequence::hash)
                    .sum();

            assertEquals(519041, result);
        }
    }

    @Test
    void testLenses_withExampleInput() throws IOException {
        try (InputStream input = HashTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            InitializationSequenceParser parser = new InitializationSequenceParser();
            InitializationSequence initializationSequence = parser.parse(input);

            List<Box> boxes = initializationSequence.generateBoxes();
            long result = 0;
            for (int boxIndex = 0; boxIndex < boxes.size(); boxIndex++) {
                Box box = boxes.get(boxIndex);
                List<Lens> lenses = box.getLenses();

                for (int lensIndex = 0; lensIndex < lenses.size(); lensIndex++) {
                    Lens lens = lenses.get(lensIndex);
                    result += (long) (1 + boxIndex) * (1 + lensIndex) * lens.getFocalLength();
                }
            }

            assertEquals(145, result);
        }
    }

    @Test
    void testLenses_withChallengeInput() throws IOException {
        try (InputStream input = HashTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            InitializationSequenceParser parser = new InitializationSequenceParser();
            InitializationSequence initializationSequence = parser.parse(input);

            List<Box> boxes = initializationSequence.generateBoxes();
            long result = 0;
            for (int boxIndex = 0; boxIndex < boxes.size(); boxIndex++) {
                Box box = boxes.get(boxIndex);
                List<Lens> lenses = box.getLenses();

                for (int lensIndex = 0; lensIndex < lenses.size(); lensIndex++) {
                    Lens lens = lenses.get(lensIndex);
                    result += (long) (1 + boxIndex) * (1 + lensIndex) * lens.getFocalLength();
                }
            }

            assertEquals(260530, result);
        }
    }
}