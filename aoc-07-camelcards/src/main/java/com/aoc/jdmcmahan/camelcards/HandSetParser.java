package com.aoc.jdmcmahan.camelcards;

import com.aoc.jdmcmahan.camelcards.model.Card;
import com.aoc.jdmcmahan.camelcards.model.Hand;
import com.aoc.jdmcmahan.camelcards.model.HandSet;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HandSetParser {

    private static final Pattern HAND_PATTERN = Pattern.compile("([TJQKA2-9]+)\\s+(\\d+)");

    public HandSet parse(InputStream input, CardMapper mapper) throws IOException {
        try (LineNumberReader reader = new LineNumberReader(new InputStreamReader(input))) {
            HandSet handSet = new HandSet();

            String line;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = HAND_PATTERN.matcher(line);
                if (!matcher.find()) {
                    throw new IllegalArgumentException("Invalid input at line " + reader.getLineNumber() + ": " + line);
                }

                Hand.HandBuilder handBuilder = Hand.builder();

                String handString = matcher.group(1);
                handString.chars()
                        .mapToObj(label -> mapper.map((char) label))
                        .forEachOrdered(handBuilder::card);

                int bid = Integer.parseInt(matcher.group(2));
                handBuilder.bid(bid);

                handSet.addHand(handBuilder.build());
            }

            return handSet;
        }
    }

    public interface CardMapper {

        Card map(char label);
    }

    public static class DefaultCardMapper implements CardMapper {

        @Override
        public Card map(char label) {
            return switch (label) {
                case '2' -> Card.TWO;
                case '3' -> Card.THREE;
                case '4' -> Card.FOUR;
                case '5' -> Card.FIVE;
                case '6' -> Card.SIX;
                case '7' -> Card.SEVEN;
                case '8' -> Card.EIGHT;
                case '9' -> Card.NINE;
                case 'T' -> Card.TEN;
                case 'J' -> Card.JACK;
                case 'Q' -> Card.QUEEN;
                case 'K' -> Card.KING;
                case 'A' -> Card.ACE;
                default -> throw new IllegalArgumentException("No card matches label " + label);
            };
        }
    }

    public static class JokerCardMapper extends DefaultCardMapper {

        @Override
        public Card map(char label) {
            if (label == 'J') {
                return Card.JOKER;
            }

            return super.map(label);
        }
    }
}
