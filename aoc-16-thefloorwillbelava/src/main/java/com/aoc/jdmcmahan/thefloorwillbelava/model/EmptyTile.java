package com.aoc.jdmcmahan.thefloorwillbelava.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.Collections;

@RequiredArgsConstructor
@Getter
public class EmptyTile implements Tile {

    private final Position position;

    @Override
    public Collection<Direction> next(Direction inbound) {
        return Collections.singleton(inbound);
    }
}
