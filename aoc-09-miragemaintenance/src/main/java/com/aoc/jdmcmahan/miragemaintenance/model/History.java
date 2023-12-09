package com.aoc.jdmcmahan.miragemaintenance.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Singular;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class History {

    @Singular
    private final List<Long> values;

    public long predictNext() {
        if (this.values.getFirst() == 0 && this.values.getLast() == 0) {
            return 0;
        }

        History child = new History(differences(this.values));
        return this.values.getLast() + child.predictNext();
    }

    public long predictPrevious() {
        if (this.values.getFirst() == 0 && this.values.getLast() == 0) {
            return 0;
        }

        History child = new History(differences(this.values));
        return this.values.getFirst() - child.predictPrevious();
    }

    protected static List<Long> differences(List<Long> sequence) {
        List<Long> differences = new ArrayList<>(sequence.size() - 1);

        long current = sequence.getFirst();
        for (int i = 1; i < sequence.size(); i++) {
            long next = sequence.get(i);

            long difference = next - current;
            differences.add(difference);

            current = next;
        }

        return differences;
    }
}
