package com.aoc.jdmcmahan.thefloorwillbelava.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.Collections;

@RequiredArgsConstructor
@Getter
public class AscendingMirror implements Tile {

    private final Position position;

    @Override
    public Collection<Direction> next(Direction inbound) {
        Direction outbound = switch (inbound) {
            case UP -> Direction.RIGHT;
            case DOWN -> Direction.LEFT;
            case LEFT -> Direction.DOWN;
            case RIGHT -> Direction.UP;
        };

        return Collections.singleton(outbound);
    }
}
