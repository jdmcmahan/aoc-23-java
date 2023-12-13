package com.aoc.jdmcmahan.pointofincidence.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PatternTest {

    @Test
    void verticalLineOfReflection() {
        Pattern pattern = Pattern.builder()
                .row("#.##..##.")
                .row("..#.##.#.")
                .row("##......#")
                .row("##......#")
                .row("..#.##.#.")
                .row("..##..##.")
                .row("#.#.##.#.")
                .smudgeFactor(0)
                .build();

        assertEquals(-1, pattern.horizontalLineOfReflection());
        assertEquals(5.5, pattern.verticalLineOfReflection());
    }

    @Test
    void verticalLineOfReflection_withSmudge() {
        Pattern pattern = Pattern.builder()
                .row("#.##..##.")
                .row("..#.##.#.")
                .row("##......#")
                .row("##......#")
                .row("..#.##.#.")
                .row("..##..##.")
                .row("#.#.##.#.")
                .smudgeFactor(1)
                .build();

        assertEquals(3.5, pattern.horizontalLineOfReflection());
        assertEquals(-1, pattern.verticalLineOfReflection());
    }

    @Test
    void horizontalLineOfReflection() {
        Pattern pattern = Pattern.builder()
                .row("#...##..#")
                .row("#....#..#")
                .row("..##..###")
                .row("#####.##.")
                .row("#####.##.")
                .row("..##..###")
                .row("#....#..#")
                .smudgeFactor(0)
                .build();

        assertEquals(4.5, pattern.horizontalLineOfReflection());
        assertEquals(-1, pattern.verticalLineOfReflection());
    }

    @Test
    void horizontalLineOfReflection_withSmudge() {
        Pattern pattern = Pattern.builder()
                .row("#...##..#")
                .row("#....#..#")
                .row("..##..###")
                .row("#####.##.")
                .row("#####.##.")
                .row("..##..###")
                .row("#....#..#")
                .smudgeFactor(1)
                .build();

        assertEquals(1.5, pattern.horizontalLineOfReflection());
        assertEquals(-1, pattern.verticalLineOfReflection());
    }
}