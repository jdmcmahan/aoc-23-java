package com.aoc.jdmcmahan.ifyougiveaseedafertilizer.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Seed implements Category {

    private final long id;

    private Soil soil;

    public void setSoil(Soil soil) {
        if (this.soil != soil) {
            this.soil = soil;
            this.soil.setSeed(this);
        }
    }
}
