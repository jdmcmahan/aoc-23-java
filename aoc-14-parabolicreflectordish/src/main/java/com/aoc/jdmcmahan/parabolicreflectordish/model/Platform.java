package com.aoc.jdmcmahan.parabolicreflectordish.model;

import lombok.Builder;
import lombok.Singular;

import java.util.*;

public class Platform {

    private final Set<Position> roundRockPositions = new HashSet<>();
    private final Set<Position> cubeRockPositions = new HashSet<>();

    private final int width;
    private final int height;

    @Builder
    protected Platform(@Singular Collection<Position> roundRockPositions, @Singular Collection<Position> cubeRockPositions) {
        int width = 0;
        int height = 0;

        for (Position position : roundRockPositions) {
            width = Math.max(width, position.getX() + 1);
            height = Math.max(height, position.getY() + 1);

            this.roundRockPositions.add(position);
        }

        for (Position position : cubeRockPositions) {
            width = Math.max(width, position.getX() + 1);
            height = Math.max(height, position.getY() + 1);

            this.cubeRockPositions.add(position);
        }

        this.width = width;
        this.height = height;
    }

    public int load() {
        return roundRockPositions.stream()
                .mapToInt(position -> height - position.getY())
                .sum();
    }

    public void spinCycle(int cycles) {
        Map<Set<Position>, Integer> cache = new HashMap<>();

        for (int i = 0; i < cycles; i++) {
            this.tiltNorth();
            this.tiltWest();
            this.tiltSouth();
            this.tiltEast();

            if (cache.containsKey(roundRockPositions)) {
                int remaining = cycles - i;
                int period = i - cache.get(roundRockPositions);

                i = cycles - (remaining % period);
            }

            cache.put(roundRockPositions, i);
        }
    }

    public void tiltNorth() {
        this.moveRocks(Direction.NORTH);
    }

    public void tiltSouth() {
        this.moveRocks(Direction.SOUTH);
    }

    public void tiltEast() {
        this.moveRocks(Direction.EAST);
    }

    public void tiltWest() {
        this.moveRocks(Direction.WEST);
    }

    protected void moveRocks(Direction direction) {
        if (direction == Direction.NORTH) {
            for (int x = 0; x < width; x++) {
                int blockIndex = 0;

                for (int y = 0; y < height; y++) {
                    Position current = Position.valueOf(x, y);

                    if (roundRockPositions.remove(current)) {
                        roundRockPositions.add(Position.valueOf(x, blockIndex));
                        blockIndex++;
                    } else if (cubeRockPositions.contains(current)) {
                        blockIndex = y + 1;
                    }
                }
            }
        } else if (direction == Direction.SOUTH) {
            for (int x = 0; x < width; x++) {
                int blockIndex = height - 1;

                for (int y = height - 1; y >= 0; y--) {
                    Position current = Position.valueOf(x, y);

                    if (roundRockPositions.remove(current)) {
                        roundRockPositions.add(Position.valueOf(x, blockIndex));
                        blockIndex--;
                    } else if (cubeRockPositions.contains(current)) {
                        blockIndex = y - 1;
                    }
                }
            }
        } else if (direction == Direction.WEST) {
            for (int y = 0; y < height; y++) {
                int blockIndex = 0;

                for (int x = 0; x < width; x++) {
                    Position current = Position.valueOf(x, y);

                    if (roundRockPositions.remove(current)) {
                        roundRockPositions.add(Position.valueOf(blockIndex, y));
                        blockIndex++;
                    } else if (cubeRockPositions.contains(current)) {
                        blockIndex = x + 1;
                    }
                }
            }
        } else if (direction == Direction.EAST) {
            for (int y = 0; y < height; y++) {
                int blockIndex = width - 1;

                for (int x = width - 1; x >= 0; x--) {
                    Position current = Position.valueOf(x, y);

                    if (roundRockPositions.remove(current)) {
                        roundRockPositions.add(Position.valueOf(blockIndex, y));
                        blockIndex--;
                    } else if (cubeRockPositions.contains(current)) {
                        blockIndex = x - 1;
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(System.lineSeparator());
        for (int y = 0; y < height; y++) {
            StringBuilder sb = new StringBuilder();

            for (int x = 0; x < width; x++) {
                Position position = Position.valueOf(x, y);
                if (roundRockPositions.contains(position)) {
                    sb.append('O');
                } else if (cubeRockPositions.contains(position)) {
                    sb.append('#');
                } else {
                    sb.append('.');
                }
            }

            sj.add(sb.toString());
        }

        return sj.toString();
    }

    protected enum Direction {
        NORTH, SOUTH, EAST, WEST
    }
}
