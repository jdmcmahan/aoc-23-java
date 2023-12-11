package com.aoc.jdmcmahan.pipemaze.model;

import java.util.Set;

public class VerticalPipe extends AbstractPipe {

    public VerticalPipe(Position position) {
        super(position);
    }

    @Override
    public Position route(Position from) {
        if (from.equals(position.south())) {
            return position.north();
        } else if (from.equals(position.north())) {
            return position.south();
        }

        return null;
    }

    @Override
    public Set<Position> getConnectionPoints() {
        return Set.of(position.north(), position.south());
    }
}
