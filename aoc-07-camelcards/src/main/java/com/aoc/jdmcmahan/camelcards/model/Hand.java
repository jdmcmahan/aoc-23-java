package com.aoc.jdmcmahan.camelcards.model;

import lombok.*;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
public class Hand implements Comparable<Hand> {

    private final List<Card> cards;
    private final int bid;

    private Type type;

    @Builder
    public Hand(@Singular List<Card> cards, int bid) {
        this.cards = cards;
        this.bid = bid;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public Card getCardAt(int index) {
        return cards.get(index);
    }

    public Type getType() {
        if (type == null) {
            type = Type.valueOf(this);
        }

        return type;
    }

    @Override
    public int compareTo(@NonNull Hand other) {
        int typeComparison = this.getType().compareTo(other.getType());
        if (typeComparison != 0) {
            return typeComparison;
        }

        return IntStream.range(0, cards.size())
                .map(i -> this.getCardAt(i).compareTo(other.getCardAt(i)))
                .filter(comparison -> comparison != 0)
                .findFirst()
                .orElse(0);
    }

    public enum Type implements Comparable<Type> {
        HIGH_CARD,
        ONE_PAIR,
        TWO_PAIR,
        THREE_OF_A_KIND,
        FULL_HOUSE,
        FOUR_OF_A_KIND,
        FIVE_OF_A_KIND;

        public static Type valueOf(Hand hand) {
            Map<Card, Long> cardCounts = hand.getCards().stream()
                    .collect(Collectors.groupingBy(Function.identity(), () -> new EnumMap<>(Card.class), Collectors.counting()));

            Iterator<Long> sortedCounts = cardCounts.entrySet().stream()
                    .filter(entry -> entry.getKey() != Card.JOKER)
                    .map(Map.Entry::getValue)
                    .sorted(Comparator.reverseOrder())
                    .iterator();

            long bestCount = sortedCounts.hasNext()
                    ? sortedCounts.next()
                    : 0;

            bestCount += Optional.ofNullable(cardCounts.get(Card.JOKER))
                    .orElse(0L);

            long secondBestCount = sortedCounts.hasNext()
                    ? sortedCounts.next()
                    : 0;

            if (bestCount == 5) {
                return FIVE_OF_A_KIND;
            } else if (bestCount == 4) {
                return FOUR_OF_A_KIND;
            } else if (bestCount == 3) {
                if (secondBestCount == 2) {
                    return FULL_HOUSE;
                } else {
                    return THREE_OF_A_KIND;
                }
            } else if (bestCount == 2) {
                if (secondBestCount == 2) {
                    return TWO_PAIR;
                } else {
                    return ONE_PAIR;
                }
            }

            return HIGH_CARD;
        }
    }
}
