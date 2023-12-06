package com.aoc.jdmcmahan.ifyougiveaseedafertilizer.model;

import java.util.*;

public class Almanac {

    private final Set<Range> seedRanges = new HashSet<>();
    private final Set<Range> soilRanges = new HashSet<>();
    private final Set<Range> fertilizerRanges = new HashSet<>();
    private final Set<Range> waterRanges = new HashSet<>();
    private final Set<Range> lightRanges = new HashSet<>();
    private final Set<Range> temperatureRanges = new HashSet<>();
    private final Set<Range> humidityRanges = new HashSet<>();
    private final Set<Range> locationRanges = new HashSet<>();

    public Collection<Range> getSeedRanges() {
        return Collections.unmodifiableCollection(seedRanges);
    }

    public void addSeedRange(Range seedRange) {
        this.seedRanges.add(seedRange);
    }

    public Collection<Range> getSoilRanges() {
        return Collections.unmodifiableCollection(soilRanges);
    }

    public void addSoilRange(Range soilRange) {
        this.soilRanges.add(soilRange);
    }

    public Collection<Range> getFertilizerRanges() {
        return Collections.unmodifiableCollection(fertilizerRanges);
    }

    public void addFertilizerRange(Range fertilizerRange) {
        this.fertilizerRanges.add(fertilizerRange);
    }

    public Collection<Range> getWaterRanges() {
        return Collections.unmodifiableCollection(waterRanges);
    }

    public void addWaterRange(Range waterRange) {
        this.waterRanges.add(waterRange);
    }

    public Collection<Range> getLightRanges() {
        return Collections.unmodifiableCollection(lightRanges);
    }

    public void addLightRange(Range lightRange) {
        this.lightRanges.add(lightRange);
    }

    public Collection<Range> getTemperatureRanges() {
        return Collections.unmodifiableCollection(temperatureRanges);
    }

    public void addTemperatureRange(Range temperatureRange) {
        this.temperatureRanges.add(temperatureRange);
    }

    public Collection<Range> getHumidityRanges() {
        return Collections.unmodifiableCollection(humidityRanges);
    }

    public void addHumidityRange(Range humidityRange) {
        this.humidityRanges.add(humidityRange);
    }

    public Collection<Range> getLocationRanges() {
        return Collections.unmodifiableCollection(locationRanges);
    }

    public void addLocationRange(Range locationRange) {
        this.locationRanges.add(locationRange);
    }
}
