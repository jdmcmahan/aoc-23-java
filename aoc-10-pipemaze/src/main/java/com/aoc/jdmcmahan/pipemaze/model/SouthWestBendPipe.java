package com.aoc.jdmcmahan.pipemaze.model;

import java.util.Set;

public class SouthWestBendPipe extends AbstractPipe {

    public SouthWestBendPipe(Position position) {
        super(position);
    }

    @Override
    public Position route(Position from) {
        if (from.equals(position.south())) {
            return position.west();
        } else if (from.equals(position.west())) {
            return position.south();
        }

        return null;
    }

    @Override
    public Set<Position> getConnectionPoints() {
        return Set.of(position.south(), position.west());
    }
}
