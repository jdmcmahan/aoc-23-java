package com.aoc.jdmcmahan.pipemaze.model;

import java.util.Set;

public class NorthWestBendPipe extends AbstractPipe {

    public NorthWestBendPipe(Position position) {
        super(position);
    }

    @Override
    public Position route(Position from) {
        if (from.equals(position.north())) {
            return position.west();
        } else if (from.equals(position.west())) {
            return position.north();
        }

        return null;
    }

    @Override
    public Set<Position> getConnectionPoints() {
        return Set.of(position.north(), position.west());
    }
}
