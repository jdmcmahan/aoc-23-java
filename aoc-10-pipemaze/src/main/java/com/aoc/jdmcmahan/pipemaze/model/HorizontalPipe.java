package com.aoc.jdmcmahan.pipemaze.model;

import java.util.Set;

public class HorizontalPipe extends AbstractPipe {

    public HorizontalPipe(Position position) {
        super(position);
    }

    @Override
    public Position route(Position from) {
        if (from.equals(position.east())) {
            return position.west();
        } else if (from.equals(position.west())) {
            return position.east();
        }

        return null;
    }

    @Override
    public Set<Position> getConnectionPoints() {
        return Set.of(position.east(), position.west());
    }
}
