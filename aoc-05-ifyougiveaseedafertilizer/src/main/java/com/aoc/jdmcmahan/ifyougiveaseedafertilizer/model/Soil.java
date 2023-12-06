package com.aoc.jdmcmahan.ifyougiveaseedafertilizer.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Soil implements Category {

    private final long id;

    private Seed seed;
    private Fertilizer fertilizer;

    public void setSeed(Seed seed) {
        if (this.seed != seed) {
            this.seed = seed;
            this.seed.setSoil(this);
        }
    }

    public void setFertilizer(Fertilizer fertilizer) {
        if (this.fertilizer != fertilizer) {
            this.fertilizer = fertilizer;
            this.fertilizer.setSoil(this);
        }
    }
}
