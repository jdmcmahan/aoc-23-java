package com.aoc.jdmcmahan.clumsycrucible.model;

public enum Direction {
    NORTH, EAST, SOUTH, WEST;

    // Two lefts don't make a right... but three do
    public Direction left() {
        return switch (this) {
            case NORTH -> WEST;
            case SOUTH -> EAST;
            case EAST -> NORTH;
            case WEST -> SOUTH;
        };
    }

    public Direction right() {
        return switch (this) {
            case NORTH -> EAST;
            case SOUTH -> WEST;
            case EAST -> SOUTH;
            case WEST -> NORTH;
        };
    }
}
