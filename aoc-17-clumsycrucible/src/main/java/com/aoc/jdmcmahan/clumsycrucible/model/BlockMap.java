package com.aoc.jdmcmahan.clumsycrucible.model;

import lombok.*;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BlockMap {

    private final Map<Position, Block> blocks;

    @Builder
    public BlockMap(@Singular Collection<Block> blocks) {
        this.blocks = blocks.stream()
                .collect(Collectors.toMap(Block::position, Function.identity()));
    }

    public Position northwestPosition() {
        return blocks.keySet().stream()
                .min(Comparator.comparingInt(Position::getY)
                        .thenComparingInt(Position::getX))
                .orElse(null);
    }

    public Position southeastPosition() {
        return blocks.keySet().stream()
                .max(Comparator.comparingInt(Position::getY)
                        .thenComparingInt(Position::getX))
                .orElse(null);
    }

    public int minimumHeatLoss(Position from, Position to, int minConsecutive, int maxConsecutive) {
        PathNode startingNode = PathNode.builder()
                .previous(null)
                .position(from)
                .direction(null)
                .stepCount(0)
                .heatLoss(0)
                .build();

        Queue<PathNode> queue = new PriorityQueue<>();
        queue.add(startingNode);

        Map<PathNode, Integer> visited = new HashMap<>();

        while (!queue.isEmpty()) {
            PathNode currentNode = queue.poll();

            if (currentNode.getPosition().equals(to)) {
                return currentNode.getHeatLoss();
            }

            if (visited.containsKey(currentNode)) {
                continue;
            }

            visited.put(currentNode, currentNode.getHeatLoss());

            List<Direction> nextDirections = Optional.ofNullable(currentNode.getDirection())
                    .map(direction -> List.of(direction.left(), direction.right()))
                    .orElse(Arrays.asList(Direction.values()));

            for (Direction direction : nextDirections) {
                int runningHeatLoss = currentNode.getHeatLoss();
                Position nextPosition = currentNode.getPosition();
                PathNode nextNode = currentNode;

                for (int i = 1; i <= maxConsecutive; i++) {
                    nextPosition = nextPosition.next(direction);

                    if (!blocks.containsKey(nextPosition)) {
                        continue;
                    }

                    Block nextBlock = blocks.get(nextPosition);
                    runningHeatLoss += nextBlock.heatLossFactor();

                    if (i < minConsecutive) {
                        continue;
                    }

                    nextNode = PathNode.builder()
                            .previous(nextNode)
                            .position(nextPosition)
                            .direction(direction)
                            .stepCount(i)
                            .heatLoss(runningHeatLoss)
                            .build();

                    if (!visited.containsKey(nextNode) || runningHeatLoss < visited.get(nextNode)) {
                        queue.add(nextNode);
                    }
                }
            }
        }

        return -1;
    }

    @RequiredArgsConstructor
    @Builder
    @Value
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    protected static class PathNode implements Comparable<PathNode> {

        PathNode previous;

        @EqualsAndHashCode.Include
        Position position;

        @EqualsAndHashCode.Include
        Direction direction;

        int stepCount;

        int heatLoss;

        public List<PathNode> unwind() {
            List<PathNode> nodes = new LinkedList<>();
            if (this.previous != null) {
                nodes.addAll(this.previous.unwind());
            }

            nodes.add(this);
            return nodes;
        }

        @Override
        public int compareTo(@NonNull PathNode other) {
            return Integer.compare(this.getHeatLoss(), other.getHeatLoss());
        }
    }
}
