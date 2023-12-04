package com.aoc.jdmcmahan.scratchcards;

import com.aoc.jdmcmahan.scratchcards.model.Scratchcard;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ScratchcardCompounder {

    public Map<Integer, Integer> process(Collection<Scratchcard> pile) {
        Map<Integer, Integer> counts = new HashMap<>();

        for (Scratchcard current : pile) {
            int currentId = current.id();

            counts.merge(currentId, 1, Integer::sum);
            int matchCount = current.matchCount();

            int multiple = counts.get(currentId);

            for (int i = 1; i <= matchCount; i++) {
                counts.merge(currentId + i, multiple, Integer::sum);
            }
        }

        return counts;
    }
}
