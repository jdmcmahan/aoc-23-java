package com.aoc.jdmcmahan.pipemaze.model;

import java.util.Set;

public class NorthEastBendPipe extends AbstractPipe {

    public NorthEastBendPipe(Position position) {
        super(position);
    }

    @Override
    public Position route(Position from) {
        if (from.equals(position.north())) {
            return position.east();
        } else if (from.equals(position.east())) {
            return position.north();
        }

        return null;
    }

    @Override
    public Set<Position> getConnectionPoints() {
        return Set.of(position.north(), position.east());
    }
}
