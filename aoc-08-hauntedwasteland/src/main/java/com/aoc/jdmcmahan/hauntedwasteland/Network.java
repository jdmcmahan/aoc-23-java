package com.aoc.jdmcmahan.hauntedwasteland;

import lombok.*;

import java.util.*;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class Network {

    private final Map<String, Node> nodes = new HashMap<>();

    private final List<Instruction> instructions;

    public Node getNode(String id) {
        return nodes.get(id);
    }

    public Node createNode(String id, String leftId, String rightId) {
        Node node = new Node(id, leftId, rightId);
        nodes.put(id, node);

        return node;
    }

    public Navigator navigator() {
        return new Navigator(this.getNode("AAA"), this.getNode("ZZZ"));
    }

    public GhostNavigator ghostNavigator() {
        Set<Node> startNodes = new HashSet<>();
        Set<Node> endNodes = new HashSet<>();

        this.nodes.forEach((id, node) -> {
            if (id.endsWith("A")) {
                startNodes.add(node);
            }

            if (id.endsWith("Z")) {
                endNodes.add(node);
            }
        });

        return new GhostNavigator(startNodes, endNodes);
    }

    @RequiredArgsConstructor
    @Getter
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    @ToString(onlyExplicitlyIncluded = true)
    public class Node {

        @EqualsAndHashCode.Include
        @ToString.Include
        private final String id;

        private final String leftId;
        private final String rightId;

        public Node getLeft() {
            return Network.this.getNode(this.leftId);
        }

        public Node getRight() {
            return Network.this.getNode(this.rightId);
        }
    }

    @RequiredArgsConstructor
    public class Navigator {

        private final Node startNode;
        private final Node endNode;

        public long navigate() {
            Iterator<Instruction> instructions = Stream.iterate(0, i -> (i + 1) % Network.this.instructions.size())
                    .map(Network.this.instructions::get)
                    .iterator();

            Node currentNode = startNode;
            long distance = 0;

            while (currentNode != endNode) {
                Instruction instruction = instructions.next();
                Node nextNode = instruction.navigate(currentNode);

                distance++;
                currentNode = nextNode;
            }

            return distance;
        }
    }

    @RequiredArgsConstructor
    public class GhostNavigator {

        @Singular
        private final Set<Node> startNodes;

        @Singular
        private final Set<Node> endNodes;

        public long navigate() {
            Iterator<Instruction> instructions = Stream.iterate(0, i -> (i + 1) % Network.this.instructions.size())
                    .map(Network.this.instructions::get)
                    .iterator();

            List<Node> currentNodes = new LinkedList<>(startNodes);
            List<Long> distances = new ArrayList<>(currentNodes.size());

            long distance = 0;

            while (!currentNodes.isEmpty()) {
                Instruction instruction = instructions.next();
                List<Node> nextNodes = new LinkedList<>();

                for (Node currentNode : currentNodes) {
                    if (endNodes.contains(currentNode)) {
                        distances.add(distance);
                    } else {
                        nextNodes.add(instruction.navigate(currentNode));
                    }
                }

                currentNodes = nextNodes;
                distance++;
            }

            return lcm(distances.stream()
                    .mapToLong(Long::longValue)
                    .toArray());
        }

        private static long gcd(long a, long b) {
            if (b == 0) {
                return a;
            }

            return gcd(b, a % b);
        }

        private static long lcm(long a, long b) {
            return a * (b / gcd(a, b));
        }

        private static long lcm(long... values) {
            long result = values[0];
            for(int i = 1; i < values.length; i++) {
                result = lcm(result, values[i]);
            }

            return result;
        }
    }

    @FunctionalInterface
    public interface Instruction {

        Node navigate(Node from);

        Instruction MOVE_LEFT = Node::getLeft;
        Instruction MOVE_RIGHT = Node::getRight;
    }
}
