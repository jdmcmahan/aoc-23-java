package com.aoc.jdmcmahan.ifyougiveaseedafertilizer.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Location implements Category {

    private final long id;

    private Humidity humidity;

    public void setHumidity(Humidity humidity) {
        if (this.humidity != humidity) {
            this.humidity = humidity;
            this.humidity.setLocation(this);
        }
    }
}
