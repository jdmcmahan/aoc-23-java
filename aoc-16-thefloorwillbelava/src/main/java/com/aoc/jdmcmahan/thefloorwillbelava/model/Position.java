package com.aoc.jdmcmahan.thefloorwillbelava.model;

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

    public Position left() {
        return this.next(Direction.LEFT);
    }

    public Position right() {
        return this.next(Direction.RIGHT);
    }

    public Position up() {
        return this.next(Direction.UP);
    }

    public Position down() {
        return this.next(Direction.DOWN);
    }

    public Position next(Direction direction) {
        return switch (direction) {
            case UP -> valueOf(x, y - 1);
            case DOWN -> valueOf(x, y + 1);
            case LEFT -> valueOf(x - 1, y);
            case RIGHT -> valueOf(x + 1, y);
        };
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public static Position valueOf(int x, int y) {
        Position position = INTERN.get(x, y);
        if (position == null) {
            position = new Position(x, y);
            INTERN.put(x, y, position);
        }

        return position;
    }
}
