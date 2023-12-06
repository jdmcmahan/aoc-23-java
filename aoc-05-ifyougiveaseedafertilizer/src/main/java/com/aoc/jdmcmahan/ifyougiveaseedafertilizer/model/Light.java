package com.aoc.jdmcmahan.ifyougiveaseedafertilizer.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Light implements Category {

    private final long id;

    private Water water;
    private Temperature temperature;

    public void setWater(Water water) {
        if (this.water != water) {
            this.water = water;
            this.water.setLight(this);
        }
    }

    public void setTemperature(Temperature temperature) {
        if (this.temperature != temperature) {
            this.temperature = temperature;
            this.temperature.setLight(this);
        }
    }
}
