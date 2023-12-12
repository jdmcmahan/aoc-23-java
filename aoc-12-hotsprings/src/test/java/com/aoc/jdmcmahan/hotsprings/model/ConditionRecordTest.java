package com.aoc.jdmcmahan.hotsprings.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConditionRecordTest {

    @Test
    void testArrangements_withNoUnknownSpringConditions() {
        ConditionRecord record = new ConditionRecord("#.#.###", List.of(1, 1, 3));
        assertEquals(1, record.arrangements());

        record = new ConditionRecord(".#...#....###.", List.of(1, 1, 3));
        assertEquals(1, record.arrangements());

        record = new ConditionRecord(".#.###.#.######", List.of(1, 3, 1, 6));
        assertEquals(1, record.arrangements());

        record = new ConditionRecord("####.#...#...", List.of(4, 1, 1));
        assertEquals(1, record.arrangements());

        record = new ConditionRecord("#....######..#####.", List.of(1, 6, 5));
        assertEquals(1, record.arrangements());

        record = new ConditionRecord(".###.##....#", List.of(3, 2, 1));
        assertEquals(1, record.arrangements());
    }

    @Test
    void testArrangements_withDamagedRecords() {
        ConditionRecord record = new ConditionRecord("???.###", List.of(1, 1, 3));
        assertEquals(1, record.arrangements());

        record = new ConditionRecord(".??..??...?##.", List.of(1, 1, 3));
        assertEquals(4, record.arrangements());

        record = new ConditionRecord("?#?#?#?#?#?#?#?", List.of(1, 3, 1, 6));
        assertEquals(1, record.arrangements());

        record = new ConditionRecord("????.#...#...", List.of(4, 1, 1));
        assertEquals(1, record.arrangements());

        record = new ConditionRecord("????.######..#####.", List.of(1, 6, 5));
        assertEquals(4, record.arrangements());

        record = new ConditionRecord("?###????????", List.of(3, 2, 1));
        assertEquals(10, record.arrangements());
    }
}