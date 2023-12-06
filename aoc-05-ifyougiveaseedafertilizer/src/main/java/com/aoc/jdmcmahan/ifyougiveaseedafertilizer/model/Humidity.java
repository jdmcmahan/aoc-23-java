package com.aoc.jdmcmahan.ifyougiveaseedafertilizer.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Humidity implements Category {

    private final long id;

    private Temperature temperature;
    private Location location;

    public void setTemperature(Temperature temperature) {
        if (this.temperature != temperature) {
            this.temperature = temperature;
            this.temperature.setHumidity(this);
        }
    }

    public void setLocation(Location location) {
        if (this.location != location) {
            this.location = location;
            this.location.setHumidity(this);
        }
    }
}
