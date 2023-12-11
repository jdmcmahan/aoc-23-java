package com.aoc.jdmcmahan.cosmicexpansion.model;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import lombok.Builder;
import lombok.Singular;

import java.util.Collection;
import java.util.Collections;

public class Image {

    private final Table<Integer, Integer, Galaxy> galaxies = HashBasedTable.create();

    @Builder
    private Image(@Singular Collection<Galaxy> galaxies) {
        galaxies.forEach(galaxy -> this.galaxies.put(galaxy.y(), galaxy.x(), galaxy));
    }

    public Collection<Galaxy> getGalaxies() {
        return Collections.unmodifiableCollection(galaxies.values());
    }

    public int distance(Galaxy from, Galaxy to, int expansionFactor) {
        int distance = 0;

        int minX = Math.min(from.x(), to.x());
        int maxX = Math.max(from.x(), to.x());
        for (int x = minX + 1; x <= maxX; x++) {
            int expansion = galaxies.containsColumn(x) ? 1 : expansionFactor;
            distance += expansion;
        }

        int minY = Math.min(from.y(), to.y());
        int maxY = Math.max(from.y(), to.y());
        for (int y = minY + 1; y <= maxY; y++) {
            int expansion = galaxies.containsRow(y) ? 1 : expansionFactor;
            distance += expansion;
        }

        return distance;
    }
}
