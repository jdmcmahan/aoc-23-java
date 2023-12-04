package com.aoc.jdmcmahan.scratchcards.model;

import lombok.Builder;
import lombok.Singular;

import java.util.HashSet;
import java.util.Set;

@Builder
public record Scratchcard(int id, @Singular Set<Integer> numbers, @Singular Set<Integer> winningNumbers) {

    public Set<Integer> matches() {
        Set<Integer> intersection = new HashSet<>(numbers);
        intersection.retainAll(winningNumbers);
        return intersection;
    }

    public int matchCount() {
        return this.matches().size();
    }

    public int points() {
        return (int) Math.pow(2, this.matchCount() - 1);
    }
}
