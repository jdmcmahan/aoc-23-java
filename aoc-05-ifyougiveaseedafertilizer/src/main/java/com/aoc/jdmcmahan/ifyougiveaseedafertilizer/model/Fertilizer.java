package com.aoc.jdmcmahan.ifyougiveaseedafertilizer.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Fertilizer implements Category {

    private final long id;

    private Soil soil;
    private Water water;

    public void setSoil(Soil soil) {
        if (this.soil != soil) {
            this.soil = soil;
            this.soil.setFertilizer(this);
        }
    }

    public void setWater(Water water) {
        if (this.water != water) {
            this.water = water;
            this.water.setFertilizer(this);
        }
    }
}
