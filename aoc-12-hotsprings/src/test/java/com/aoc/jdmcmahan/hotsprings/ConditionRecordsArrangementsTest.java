package com.aoc.jdmcmahan.hotsprings;

import com.aoc.jdmcmahan.hotsprings.model.ConditionRecord;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConditionRecordsArrangementsTest {

    @Test
    void testArrangements_withExampleInput() throws IOException {
        try (InputStream input = ConditionRecordsArrangementsTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            ConditionRecordsParser parser = new ConditionRecordsParser();
            List<ConditionRecord> records = parser.parse(input);

            long result = records.stream()
                    .mapToLong(ConditionRecord::arrangements)
                    .sum();

            assertEquals(21, result);
        }
    }

    @Test
    void testArrangements_withChallengeInput() throws IOException {
        try (InputStream input = ConditionRecordsArrangementsTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            ConditionRecordsParser parser = new ConditionRecordsParser();
            List<ConditionRecord> records = parser.parse(input);

            long result = records.stream()
                    .mapToLong(ConditionRecord::arrangements)
                    .sum();

            assertEquals(7110, result);
        }
    }

    @Test
    void testUnfoldedArrangements_withExampleInput() throws IOException {
        try (InputStream input = ConditionRecordsArrangementsTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            ConditionRecordsParser parser = new ConditionRecordsParser();
            List<ConditionRecord> records = parser.parse(input);

            long result = records.stream()
                    .map(record -> this.unfold(record, 5))
                    .mapToLong(ConditionRecord::arrangements)
                    .sum();

            assertEquals(525152, result);
        }
    }

    @Test
    void testUnfoldedArrangements_withChallengeInput() throws IOException {
        try (InputStream input = ConditionRecordsArrangementsTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            ConditionRecordsParser parser = new ConditionRecordsParser();
            List<ConditionRecord> records = parser.parse(input);

            long result = records.stream()
                    .map(record -> this.unfold(record, 5))
                    .mapToLong(ConditionRecord::arrangements)
                    .sum();

            assertEquals(1566786613613L, result);
        }
    }

    private ConditionRecord unfold(ConditionRecord record, int folds) {
        StringJoiner conditionJoiner = new StringJoiner("?");
        List<Integer> groups = new LinkedList<>();

        for (int i = 0; i < folds; i++) {
            conditionJoiner.add(record.condition());
            groups.addAll(record.groups());
        }

        return new ConditionRecord(conditionJoiner.toString(), groups);
    }
}