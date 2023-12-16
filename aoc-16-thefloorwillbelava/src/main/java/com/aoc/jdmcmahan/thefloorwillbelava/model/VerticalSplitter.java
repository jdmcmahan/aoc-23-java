package com.aoc.jdmcmahan.thefloorwillbelava.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@RequiredArgsConstructor
@Getter
public class VerticalSplitter implements Tile {

    private final Position position;

    @Override
    public Collection<Direction> next(Direction inbound) {
        if (inbound == Direction.UP || inbound == Direction.DOWN) {
            return Collections.singleton(inbound);
        }

        return Set.of(Direction.UP, Direction.DOWN);
    }
}
