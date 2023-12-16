package com.aoc.jdmcmahan.thefloorwillbelava.model;

import java.util.Collection;

public interface Tile {

    Position getPosition();

    Collection<? extends Direction> next(Direction inbound);
}
