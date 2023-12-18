package com.aoc.jdmcmahan.clumsycrucible.model;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
public class Position {

    protected static final Table<Integer, Integer, Position> INTERN = HashBasedTable.create();

    private final int x;
    private final int y;

    public Position west() {
        return valueOf(x - 1, y);
    }

    public Position east() {
        return valueOf(x + 1, y);
    }

    public Position north() {
        return valueOf(x, y - 1);
    }

    public Position south() {
        return valueOf(x, y + 1);
    }

    public Position next(Direction direction) {
        return switch (direction) {
            case NORTH -> this.north();
            case SOUTH -> this.south();
            case EAST -> this.east();
            case WEST -> this.west();
        };
    }

    public static Position valueOf(int x, int y) {
        Position position = INTERN.get(x, y);
        if (position == null) {
            position = new Position(x, y);
            INTERN.put(x, y, position);
        }

        return position;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
