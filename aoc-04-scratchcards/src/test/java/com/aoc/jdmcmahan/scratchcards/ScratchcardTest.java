package com.aoc.jdmcmahan.scratchcards;

import com.aoc.jdmcmahan.scratchcards.model.Scratchcard;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScratchcardTest {

    @Test
    void testScratchcardPoints_withExampleInput() throws IOException {
        try (InputStream input = ScratchcardTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            ScratchcardParser parser = new ScratchcardParser();
            List<Scratchcard> pile = parser.parse(input);

            int result = pile.stream()
                    .mapToInt(Scratchcard::points)
                    .sum();

            assertEquals(13, result);
        }
    }

    @Test
    void testScratchcardPoints_withChallengeInput() throws IOException {
        try (InputStream input = ScratchcardTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            ScratchcardParser parser = new ScratchcardParser();
            List<Scratchcard> pile = parser.parse(input);

            int result = pile.stream()
                    .mapToInt(Scratchcard::points)
                    .sum();

            assertEquals(20829, result);
        }
    }

    @Test
    void testCompoundingScratchcards_withExampleInput() throws IOException {
        try (InputStream input = ScratchcardTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            ScratchcardParser parser = new ScratchcardParser();
            List<Scratchcard> pile = parser.parse(input);

            ScratchcardCompounder compounder = new ScratchcardCompounder();
            int result = compounder.process(pile).values().stream()
                    .mapToInt(Integer::intValue)
                    .sum();

            assertEquals(30, result);
        }
    }

    @Test
    void testCompoundingScratchcards_withChallengeInput() throws IOException {
        try (InputStream input = ScratchcardTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            ScratchcardParser parser = new ScratchcardParser();
            List<Scratchcard> pile = parser.parse(input);

            ScratchcardCompounder compounder = new ScratchcardCompounder();
            int result = compounder.process(pile).values().stream()
                    .mapToInt(Integer::intValue)
                    .sum();

            assertEquals(12648035, result);
        }
    }
}