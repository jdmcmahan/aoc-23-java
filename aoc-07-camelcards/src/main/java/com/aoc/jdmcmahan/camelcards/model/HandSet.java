package com.aoc.jdmcmahan.camelcards.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HandSet {

    private final List<Hand> hands = new ArrayList<>();

    public List<Hand> getHands() {
        return Collections.unmodifiableList(hands);
    }

    public void addHand(Hand hand) {
        int index = Collections.binarySearch(hands, hand);
        if (index < 0) {
            index = -index - 1;
        }

        hands.add(index, hand);
    }
}
