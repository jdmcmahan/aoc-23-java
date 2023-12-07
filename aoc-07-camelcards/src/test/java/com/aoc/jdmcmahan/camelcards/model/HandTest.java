package com.aoc.jdmcmahan.camelcards.model;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HandTest {

    @Test
    @SuppressWarnings("EqualsWithItself")
    void compareTo_withoutJokers() {
        Hand hand1 = new Hand(List.of(Card.THREE, Card.TWO, Card.TEN, Card.THREE, Card.KING), 0);
        Hand hand2 = new Hand(List.of(Card.KING, Card.TEN, Card.JACK, Card.JACK, Card.TEN), 0);
        Hand hand3 = new Hand(List.of(Card.KING, Card.KING, Card.SIX, Card.SEVEN, Card.SEVEN), 0);
        Hand hand4 = new Hand(List.of(Card.TEN, Card.FIVE, Card.FIVE, Card.JACK, Card.FIVE), 0);
        Hand hand5 = new Hand(List.of(Card.QUEEN, Card.QUEEN, Card.QUEEN, Card.JACK, Card.ACE), 0);

        assertEquals(0, Math.signum(hand1.compareTo(hand1)));
        assertEquals(-1, Math.signum(hand1.compareTo(hand2)));
        assertEquals(-1, Math.signum(hand1.compareTo(hand3)));
        assertEquals(-1, Math.signum(hand1.compareTo(hand4)));
        assertEquals(-1, Math.signum(hand1.compareTo(hand5)));

        assertEquals(1, Math.signum(hand2.compareTo(hand1)));
        assertEquals(0, Math.signum(hand2.compareTo(hand2)));
        assertEquals(-1, Math.signum(hand2.compareTo(hand3)));
        assertEquals(-1, Math.signum(hand2.compareTo(hand4)));
        assertEquals(-1, Math.signum(hand2.compareTo(hand5)));

        assertEquals(1, Math.signum(hand3.compareTo(hand1)));
        assertEquals(1, Math.signum(hand3.compareTo(hand2)));
        assertEquals(0, Math.signum(hand3.compareTo(hand3)));
        assertEquals(-1, Math.signum(hand3.compareTo(hand4)));
        assertEquals(-1, Math.signum(hand3.compareTo(hand5)));

        assertEquals(1, Math.signum(hand4.compareTo(hand1)));
        assertEquals(1, Math.signum(hand4.compareTo(hand2)));
        assertEquals(1, Math.signum(hand4.compareTo(hand3)));
        assertEquals(0, Math.signum(hand4.compareTo(hand4)));
        assertEquals(-1, Math.signum(hand4.compareTo(hand5)));

        assertEquals(1, Math.signum(hand5.compareTo(hand1)));
        assertEquals(1, Math.signum(hand5.compareTo(hand2)));
        assertEquals(1, Math.signum(hand5.compareTo(hand3)));
        assertEquals(1, Math.signum(hand5.compareTo(hand4)));
        assertEquals(0, Math.signum(hand5.compareTo(hand5)));
    }

    @Test
    @SuppressWarnings("EqualsWithItself")
    void compareTo_withJokers() {
        Hand hand1 = new Hand(List.of(Card.THREE, Card.TWO, Card.TEN, Card.THREE, Card.KING), 0);
        Hand hand2 = new Hand(List.of(Card.KING, Card.KING, Card.SIX, Card.SEVEN, Card.SEVEN), 0);
        Hand hand3 = new Hand(List.of(Card.TEN, Card.FIVE, Card.FIVE, Card.JOKER, Card.FIVE), 0);
        Hand hand4 = new Hand(List.of(Card.QUEEN, Card.QUEEN, Card.QUEEN, Card.JOKER, Card.ACE), 0);
        Hand hand5 = new Hand(List.of(Card.KING, Card.TEN, Card.JOKER, Card.JOKER, Card.TEN), 0);

        assertEquals(0, Math.signum(hand1.compareTo(hand1)));
        assertEquals(-1, Math.signum(hand1.compareTo(hand2)));
        assertEquals(-1, Math.signum(hand1.compareTo(hand3)));
        assertEquals(-1, Math.signum(hand1.compareTo(hand4)));
        assertEquals(-1, Math.signum(hand1.compareTo(hand5)));

        assertEquals(1, Math.signum(hand2.compareTo(hand1)));
        assertEquals(0, Math.signum(hand2.compareTo(hand2)));
        assertEquals(-1, Math.signum(hand2.compareTo(hand3)));
        assertEquals(-1, Math.signum(hand2.compareTo(hand4)));
        assertEquals(-1, Math.signum(hand2.compareTo(hand5)));

        assertEquals(1, Math.signum(hand3.compareTo(hand1)));
        assertEquals(1, Math.signum(hand3.compareTo(hand2)));
        assertEquals(0, Math.signum(hand3.compareTo(hand3)));
        assertEquals(-1, Math.signum(hand3.compareTo(hand4)));
        assertEquals(-1, Math.signum(hand3.compareTo(hand5)));

        assertEquals(1, Math.signum(hand4.compareTo(hand1)));
        assertEquals(1, Math.signum(hand4.compareTo(hand2)));
        assertEquals(1, Math.signum(hand4.compareTo(hand3)));
        assertEquals(0, Math.signum(hand4.compareTo(hand4)));
        assertEquals(-1, Math.signum(hand4.compareTo(hand5)));

        assertEquals(1, Math.signum(hand5.compareTo(hand1)));
        assertEquals(1, Math.signum(hand5.compareTo(hand2)));
        assertEquals(1, Math.signum(hand5.compareTo(hand3)));
        assertEquals(1, Math.signum(hand5.compareTo(hand4)));
        assertEquals(0, Math.signum(hand5.compareTo(hand5)));
    }

    @Nested
    class Type {

        @Test
        void valueOfHand_forFiveOfAKind() {
            Hand hand = new Hand(List.of(Card.ACE, Card.ACE, Card.ACE, Card.ACE, Card.ACE), 0);
            assertEquals(Hand.Type.FIVE_OF_A_KIND, Hand.Type.valueOf(hand));

            hand = new Hand(List.of(Card.JOKER, Card.JOKER, Card.JOKER, Card.JOKER, Card.JOKER), 0);
            assertEquals(Hand.Type.FIVE_OF_A_KIND, Hand.Type.valueOf(hand));

            hand = new Hand(List.of(Card.ACE, Card.ACE, Card.JOKER, Card.JOKER, Card.JOKER), 0);
            assertEquals(Hand.Type.FIVE_OF_A_KIND, Hand.Type.valueOf(hand));
        }

        @Test
        void valueOfHand_forFourOfAKind() {
            Hand hand = new Hand(List.of(Card.ACE, Card.ACE, Card.EIGHT, Card.ACE, Card.ACE), 0);
            assertEquals(Hand.Type.FOUR_OF_A_KIND, Hand.Type.valueOf(hand));

            hand = new Hand(List.of(Card.JOKER, Card.JOKER, Card.EIGHT, Card.ACE, Card.ACE), 0);
            assertEquals(Hand.Type.FOUR_OF_A_KIND, Hand.Type.valueOf(hand));
        }

        @Test
        void valueOfHand_forFullHouse() {
            Hand hand = new Hand(List.of(Card.TWO, Card.THREE, Card.THREE, Card.THREE, Card.TWO), 0);
            assertEquals(Hand.Type.FULL_HOUSE, Hand.Type.valueOf(hand));

            hand = new Hand(List.of(Card.TWO, Card.THREE, Card.THREE, Card.JOKER, Card.TWO), 0);
            assertEquals(Hand.Type.FULL_HOUSE, Hand.Type.valueOf(hand));
        }

        @Test
        void valueOfHand_forThreeOfAKind() {
            Hand hand = new Hand(List.of(Card.TEN, Card.TEN, Card.TEN, Card.NINE, Card.EIGHT), 0);
            assertEquals(Hand.Type.THREE_OF_A_KIND, Hand.Type.valueOf(hand));

            hand = new Hand(List.of(Card.TEN, Card.TEN, Card.JOKER, Card.NINE, Card.EIGHT), 0);
            assertEquals(Hand.Type.THREE_OF_A_KIND, Hand.Type.valueOf(hand));
        }

        @Test
        void valueOfHand_forTwoPair() {
            Hand hand = new Hand(List.of(Card.TWO, Card.THREE, Card.FOUR, Card.THREE, Card.TWO), 0);
            assertEquals(Hand.Type.TWO_PAIR, Hand.Type.valueOf(hand));
        }

        @Test
        void valueOfHand_forOnePair() {
            Hand hand = new Hand(List.of(Card.ACE, Card.TWO, Card.THREE, Card.ACE, Card.FOUR), 0);
            assertEquals(Hand.Type.ONE_PAIR, Hand.Type.valueOf(hand));

            hand = new Hand(List.of(Card.ACE, Card.TWO, Card.THREE, Card.JOKER, Card.FOUR), 0);
            assertEquals(Hand.Type.ONE_PAIR, Hand.Type.valueOf(hand));
        }

        @Test
        void valueOfHand_forHighCard() {
            Hand hand = new Hand(List.of(Card.TWO, Card.THREE, Card.FOUR, Card.FIVE, Card.SIX), 0);
            assertEquals(Hand.Type.HIGH_CARD, Hand.Type.valueOf(hand));
        }
    }
}