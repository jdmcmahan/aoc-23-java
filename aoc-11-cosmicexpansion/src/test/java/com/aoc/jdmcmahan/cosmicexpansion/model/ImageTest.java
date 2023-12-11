package com.aoc.jdmcmahan.cosmicexpansion.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ImageTest {

    @Test
    void distance() {
        List<Galaxy> galaxies = List.of(
                new Galaxy(3, 0),
                new Galaxy(7, 1),
                new Galaxy(0, 2),
                new Galaxy(6, 4),
                new Galaxy(1, 5),
                new Galaxy(9, 6),
                new Galaxy(7, 8),
                new Galaxy(0, 9),
                new Galaxy(4, 9)
        );

        Image image = Image.builder()
                .galaxies(galaxies)
                .build();

        assertEquals(9, image.distance(galaxies.get(1), galaxies.get(6), 2));
        assertEquals(5, image.distance(galaxies.get(7), galaxies.get(8), 2));
        assertEquals(9, image.distance(galaxies.get(4), galaxies.get(8), 2));
        assertEquals(15, image.distance(galaxies.get(0), galaxies.get(6), 2));
        assertEquals(17, image.distance(galaxies.get(2), galaxies.get(5), 2));
    }
}
