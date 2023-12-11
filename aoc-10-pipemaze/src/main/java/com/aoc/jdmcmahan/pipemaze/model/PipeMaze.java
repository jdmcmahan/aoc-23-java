package com.aoc.jdmcmahan.pipemaze.model;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Singular;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class PipeMaze {

    private final Pipe start;
    private final Map<Position, Pipe> pipes;

    @Builder
    public PipeMaze(Position start, @Singular Collection<Pipe> pipes) {
        this.start = new StartPipe(start);
        this.pipes = pipes.stream()
                .collect(Collectors.toMap(Pipe::getPosition, Function.identity()));
    }

    public List<Pipe> loop() {
        List<Pipe> path = new LinkedList<>();
        path.add(this.start);

        Position nextPosition = this.start.route(null);
        while (!this.start.getPosition().equals(nextPosition)) {
            Pipe previous = path.getLast();
            Pipe current = this.pipes.get(nextPosition);
            path.add(current);

            nextPosition = current.route(previous.getPosition());
        }

        return path;
    }

    public int area() {
        List<Pipe> loop = new LinkedList<>(this.loop());
        loop.add(this.start);

        int doubleArea = 0;
        for (int i = 1; i < loop.size(); i++) {
            Pipe previous = loop.get(i - 1);
            Pipe current = loop.get(i);

            doubleArea += (previous.getPosition().getX() * current.getPosition().getY()) - (previous.getPosition().getY() * current.getPosition().getX());
        }

        return ((Math.abs(doubleArea) - (loop.size() - 1)) / 2) + 1;
    }

    protected class StartPipe extends AbstractPipe {

        public StartPipe(Position position) {
            super(position);
        }

        @Override
        public Position route(Position from) {
            return this.getConnectionPoints().stream()
                    .filter(PipeMaze.this.pipes::containsKey)
                    .map(PipeMaze.this.pipes::get)
                    .filter(pipe -> pipe.getConnectionPoints().contains(this.position))
                    .map(Pipe::getPosition)
                    .findAny()
                    .orElse(null);
        }

        @Override
        public Set<Position> getConnectionPoints() {
            return Set.of(position.north(), position.south(), position.east(), position.west());
        }
    }
}
