package com.aoc.jdmcmahan.ifyougiveaseedafertilizer.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Water implements Category {

    private final long id;

    private Fertilizer fertilizer;
    private Light light;

    public void setFertilizer(Fertilizer fertilizer) {
        if (this.fertilizer != fertilizer) {
            this.fertilizer = fertilizer;
            this.fertilizer.setWater(this);
        }
    }

    public void setLight(Light light) {
        if (this.light != light) {
            this.light = light;
            this.light.setWater(this);
        }
    }
}
