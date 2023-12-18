package com.aoc.jdmcmahan.lavaductlagoon.model;

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

    protected static final Table<Long, Long, Position> INTERN = HashBasedTable.create();

    private final long x;
    private final long y;

    public Position left() {
        return valueOf(x - 1, y);
    }

    public Position right() {
        return valueOf(x + 1, y);
    }

    public Position up() {
        return valueOf(x, y - 1);
    }

    public Position down() {
        return valueOf(x, y + 1);
    }

    public Position translate(Direction direction, long distance) {
        return switch (direction) {
            case UP -> valueOf(x, y - distance);
            case DOWN -> valueOf(x, y + distance);
            case RIGHT -> valueOf(x + distance, y);
            case LEFT -> valueOf(x - distance, y);
        };
    }

    public long distance(Position other) {
        return (long) Math.sqrt(Math.pow(other.x - this.x, 2) + Math.pow(other.y - this.y, 2));
    }

    public static Position valueOf(long x, long y) {
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
