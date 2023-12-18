package com.aoc.jdmcmahan.lavaductlagoon.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Lagoon {

    private final List<Position> trenches;

    private final long perimeter;
    private final long area;

    public List<Position> getTrenches() {
        return Collections.unmodifiableList(trenches);
    }

    public static DigPlan plan() {
        return new DigPlan();
    }

    public static class DigPlan {

        private final List<Position> trenches = new LinkedList<>();

        private Position previous = Position.valueOf(0, 0);
        private long doubleArea = 0;
        private long perimeter = 0;

        public DigPlan trench(Direction direction, long distance) {
            Position next = previous.translate(direction, distance);
            trenches.add(next);

            perimeter += previous.distance(next);
            doubleArea += (previous.getX() * next.getY()) - (previous.getY() * next.getX());

            previous = next;

            return this;
        }

        public Lagoon dig() {
            long area = ((Math.abs(doubleArea) - (perimeter - 1)) / 2) + 1 + perimeter;
            return new Lagoon(trenches, perimeter, area);
        }
    }
}
