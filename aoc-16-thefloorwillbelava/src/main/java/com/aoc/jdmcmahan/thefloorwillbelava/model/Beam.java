package com.aoc.jdmcmahan.thefloorwillbelava.model;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class Beam {

    private final Set<Tile> energizedTiles = new LinkedHashSet<>();

    public void addTile(Tile tile) {
        this.energizedTiles.add(tile);
    }

    public Set<Tile> getEnergizedTiles() {
        return Collections.unmodifiableSet(energizedTiles);
    }
}
