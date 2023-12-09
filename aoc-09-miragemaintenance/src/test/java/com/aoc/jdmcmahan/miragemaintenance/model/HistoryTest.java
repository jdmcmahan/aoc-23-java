package com.aoc.jdmcmahan.miragemaintenance.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HistoryTest {

    @Test
    void predictNext() {
        History history = new History(List.of(0L, 3L, 6L, 9L, 12L, 15L));
        assertEquals(18, history.predictNext());

        history = new History(List.of(1L, 3L, 6L, 10L, 15L, 21L));
        assertEquals(28, history.predictNext());

        history = new History(List.of(10L, 13L, 16L, 21L, 30L, 45L));
        assertEquals(68, history.predictNext());
    }

    @Test
    void predictPrevious() {
        History history = new History(List.of(0L, 3L, 6L, 9L, 12L, 15L));
        assertEquals(-3, history.predictPrevious());

        history = new History(List.of(1L, 3L, 6L, 10L, 15L, 21L));
        assertEquals(0, history.predictPrevious());

        history = new History(List.of(10L, 13L, 16L, 21L, 30L, 45L));
        assertEquals(5, history.predictPrevious());
    }
}