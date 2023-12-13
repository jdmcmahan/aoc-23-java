package com.aoc.jdmcmahan.pointofincidence.model;

import lombok.Builder;
import lombok.Singular;
import org.apache.commons.text.similarity.LevenshteinDistance;

import java.util.ArrayList;
import java.util.List;

public class Pattern {

    private static final LevenshteinDistance LEVENSHTEIN_DISTANCE = new LevenshteinDistance(1);

    private final List<String> rows = new ArrayList<>();
    private final List<String> columns = new ArrayList<>();

    private final int smudgeFactor;

    @Builder
    public Pattern(@Singular List<String> rows, int smudgeFactor) {
        this.smudgeFactor = smudgeFactor;

        if (rows.isEmpty()) {
            throw new IllegalArgumentException("No rows provided");
        }

        int width = rows.getFirst().length();
        for (int i = 0; i < rows.size(); i++) {
            String row = rows.get(i);
            if (row.length() != width) {
                throw new IllegalArgumentException("Row \"" + row + "\" at index " + i + " has a width that is inconsistent with previous rows");
            }

            this.rows.add(row);

            if (this.columns.isEmpty()) {
                row.chars()
                        .mapToObj(Character::toString)
                        .forEach(this.columns::add);
            } else {
                for (int j = 0; j < row.length(); j++) {
                    columns.set(j, columns.get(j) + row.charAt(j));
                }
            }
        }
    }

    public double verticalLineOfReflection() {
        return findLineOfReflection(columns, smudgeFactor);
    }

    public double horizontalLineOfReflection() {
        return findLineOfReflection(rows, smudgeFactor);
    }

    protected static double findLineOfReflection(List<String> notes, int smudgeFactor) {
        for (int i = 0; i < notes.size() - 1; i++) {
            if (isReflection(notes.subList(0, i + 1), notes.subList(i + 1, notes.size()), smudgeFactor)) {
                return i + 1.5;
            }
        }

        return -1;
    }

    protected static boolean isReflection(List<String> beforeList, List<String> afterList, int smudgeFactor) {
        int width = Math.min(beforeList.size(), afterList.size());
        int smudges = 0;

        for (int i = 0; i < width; i++) {
            String before = beforeList.reversed().get(i);
            String after = afterList.get(i);

            if (before.equals(after)) {
                continue;
            }

            if (smudges == smudgeFactor) {
                return false;
            }

            int difference = LEVENSHTEIN_DISTANCE.apply(before, after);
            if (difference != 1) {
                return false;
            }

            smudges++;
        }

        return smudges == smudgeFactor;
    }
}
