package com.aoc.jdmcmahan.pipemaze.model;

import java.util.Set;

public class SouthEastBendPipe extends AbstractPipe {

    public SouthEastBendPipe(Position position) {
        super(position);
    }

    @Override
    public Position route(Position from) {
        if (from.equals(position.south())) {
            return position.east();
        } else if (from.equals(position.east())) {
            return position.south();
        }

        return null;
    }

    @Override
    public Set<Position> getConnectionPoints() {
        return Set.of(position.south(), position.east());
    }
}
