package com.aoc.jdmcmahan.pipemaze.model;

import java.util.Set;

public interface Pipe {

    Position getPosition();

    Position route(Position from);

    Set<Position> getConnectionPoints();

    default boolean isConnectedTo(Pipe other) {
        return this.getConnectionPoints().contains(other.getPosition())
                && other.getConnectionPoints().contains(this.getPosition());
    }
}
