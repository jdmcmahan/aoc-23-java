package com.aoc.jdmcmahan.thefloorwillbelava.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.Collections;

@RequiredArgsConstructor
@Getter
public class DescendingMirror implements Tile {

    private final Position position;

    @Override
    public Collection<Direction> next(Direction inbound) {
        Direction outbound = switch (inbound) {
            case UP -> Direction.LEFT;
            case DOWN -> Direction.RIGHT;
            case LEFT -> Direction.UP;
            case RIGHT -> Direction.DOWN;
        };

        return Collections.singleton(outbound);
    }
}
