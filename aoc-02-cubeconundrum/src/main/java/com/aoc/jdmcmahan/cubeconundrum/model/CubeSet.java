package com.aoc.jdmcmahan.cubeconundrum.model;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
@Builder
@Value
public class CubeSet {

    int redCount;
    int greenCount;
    int blueCount;

    public boolean contains(CubeSet other) {
        return this.redCount >= other.redCount
                && this.greenCount >= other.greenCount
                && this.blueCount >= other.blueCount;
    }

    public int getPower() {
        return redCount * greenCount * blueCount;
    }
}
