package com.aoc.jdmcmahan.camelcards;

import com.aoc.jdmcmahan.camelcards.model.Hand;
import com.aoc.jdmcmahan.camelcards.model.HandSet;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HandRankTest {

    @Test
    void testRank_withExampleInput() throws IOException {
        try (InputStream input = HandRankTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            HandSetParser parser = new HandSetParser();
            HandSet handSet = parser.parse(input, new HandSetParser.DefaultCardMapper());

            List<Hand> hands = handSet.getHands();
            int result = IntStream.range(0, hands.size())
                    .map(i -> (i + 1) * hands.get(i).getBid())
                    .sum();

            assertEquals(6440, result);
        }
    }

    @Test
    void testRank_withChallengeInput() throws IOException {
        try (InputStream input = HandRankTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            HandSetParser parser = new HandSetParser();
            HandSet handSet = parser.parse(input, new HandSetParser.DefaultCardMapper());

            List<Hand> hands = handSet.getHands();
            int result = IntStream.range(0, hands.size())
                    .map(i -> (i + 1) * hands.get(i).getBid())
                    .sum();

            assertEquals(252052080, result);
        }
    }

    @Test
    void testJokersRank_withExampleInput() throws IOException {
        try (InputStream input = HandRankTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            HandSetParser parser = new HandSetParser();
            HandSet handSet = parser.parse(input, new HandSetParser.JokerCardMapper());

            List<Hand> hands = handSet.getHands();
            int result = IntStream.range(0, hands.size())
                    .map(i -> (i + 1) * hands.get(i).getBid())
                    .sum();

            assertEquals(5905, result);
        }
    }

    @Test
    void testJokersRank_withChallengeInput() throws IOException {
        try (InputStream input = HandRankTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            HandSetParser parser = new HandSetParser();
            HandSet handSet = parser.parse(input, new HandSetParser.JokerCardMapper());

            List<Hand> hands = handSet.getHands();
            int result = IntStream.range(0, hands.size())
                    .map(i -> (i + 1) * hands.get(i).getBid())
                    .sum();

            assertEquals(252898370, result);
        }
    }
}