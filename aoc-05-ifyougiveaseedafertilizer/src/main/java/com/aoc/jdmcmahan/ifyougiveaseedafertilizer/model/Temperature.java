package com.aoc.jdmcmahan.ifyougiveaseedafertilizer.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Temperature implements Category {

    private final long id;

    private Light light;
    private Humidity humidity;

    public void setLight(Light light) {
        if (this.light != light) {
            this.light = light;
            this.light.setTemperature(this);
        }
    }

    public void setHumidity(Humidity humidity) {
        if (this.humidity != humidity) {
            this.humidity = humidity;
            this.humidity.setTemperature(this);
        }
    }
}
