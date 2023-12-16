package com.aoc.jdmcmahan.thefloorwillbelava.model;

import lombok.Builder;
import lombok.Singular;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Contraption {

    private final Map<Position, Tile> tiles;

    private final BeamProjector projector = new BeamProjector();

    @Builder
    public Contraption(@Singular Collection<Tile> tiles) {
        this.tiles = tiles.stream()
                .collect(Collectors.toMap(Tile::getPosition, Function.identity()));
    }

    public Beam beam(Position start, Direction direction) {
        return projector.project(start, direction);
    }

    public Set<Beam> beams() {
        return tiles.keySet().stream()
                .flatMap(position -> Arrays.stream(Direction.values())
                        .map(direction -> new Vector(position.next(direction), direction.opposite()))
                        .filter(vector -> !tiles.containsKey(vector.from())))
                .map(projector::project)
                .collect(Collectors.toSet());
    }

    protected class BeamProjector {

        public Beam project (Vector vector) {
            return this.project(vector.from(), vector.direction());
        }

        public Beam project(Position start, Direction direction) {
            Beam beam = new Beam();
            Set<Vector> visited = new HashSet<>();

            List<Vector> queue = new LinkedList<>();
            queue.add(new Vector(start, direction));

            while (!queue.isEmpty()) {
                Vector baseVector = queue.removeFirst();

                Direction baseDirection = baseVector.direction();
                Position currentPosition = baseVector.from();

                while (true) {
                    currentPosition = currentPosition.next(baseDirection);

                    Vector currentVector = new Vector(currentPosition, baseDirection);
                    if (visited.contains(currentVector)) {
                        break;
                    }

                    Tile currentTile = Contraption.this.tiles.get(currentPosition);
                    if (currentTile == null) {
                        break;
                    }

                    beam.addTile(currentTile);
                    visited.add(currentVector);

                    Collection<? extends Direction> nextDirections = currentTile.next(baseDirection);
                    if (nextDirections.size() == 1 && nextDirections.iterator().next() == baseDirection) {
                        continue;
                    }

                    for (Direction nextDirection : nextDirections) {
                        Vector vector = new Vector(currentPosition, nextDirection);
                        queue.add(vector);
                    }

                    break;
                }
            }

            return beam;
        }
    }

    protected record Vector(Position from, Direction direction) {
    }
}
