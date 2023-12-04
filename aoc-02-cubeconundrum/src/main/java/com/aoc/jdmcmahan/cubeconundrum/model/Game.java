package com.aoc.jdmcmahan.cubeconundrum.model;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Singular;

import java.util.List;

@RequiredArgsConstructor
@Builder
public record Game(int id, @Singular List<CubeSet> cubeSets) {

    public boolean possible(CubeSet cubeSet) {
        return cubeSets.stream()
                .allMatch(cubeSet::contains);
    }

    public CubeSet toMinimum() {
        int redCount = 0;
        int greenCount = 0;
        int blueCount = 0;

        for (CubeSet cubeSet : cubeSets) {
            redCount = Math.max(redCount, cubeSet.redCount());
            greenCount = Math.max(greenCount, cubeSet.greenCount());
            blueCount = Math.max(blueCount, cubeSet.blueCount());
        }

        return CubeSet.builder()
                .redCount(redCount)
                .greenCount(greenCount)
                .blueCount(blueCount)
                .build();
    }
}