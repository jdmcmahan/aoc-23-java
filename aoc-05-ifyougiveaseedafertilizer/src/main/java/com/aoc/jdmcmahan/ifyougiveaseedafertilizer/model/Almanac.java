package com.aoc.jdmcmahan.ifyougiveaseedafertilizer.model;

import java.util.*;

public class Almanac {

    private final TreeMap<Long, Seed> seeds = new TreeMap<>();
    private final TreeMap<Long, Soil> soils = new TreeMap<>();
    private final TreeMap<Long, Fertilizer> fertilizers = new TreeMap<>();
    private final TreeMap<Long, Water> waters = new TreeMap<>();
    private final TreeMap<Long, Light> lights = new TreeMap<>();
    private final TreeMap<Long, Temperature> temperatures = new TreeMap<>();
    private final TreeMap<Long, Humidity> humidities = new TreeMap<>();
    private final TreeMap<Long, Location> locations = new TreeMap<>();

    public Seed getSeed(long id) {
        return seeds.get(id);
    }

    public Collection<Seed> getSeeds() {
        return Collections.unmodifiableCollection(seeds.values());
    }

    public void addSeed(Seed seed) {
        this.seeds.put(seed.getId(), seed);
    }

    public Soil getSoil(long id) {
        return soils.get(id);
    }

    public Collection<Soil> getSoils() {
        return Collections.unmodifiableCollection(soils.values());
    }

    public void addSoil(Soil soil) {
        this.soils.put(soil.getId(), soil);
    }

    public Fertilizer getFertilizer(long id) {
        return fertilizers.get(id);
    }

    public Collection<Fertilizer> getFertilizers() {
        return Collections.unmodifiableCollection(fertilizers.values());
    }

    public void addFertilizer(Fertilizer fertilizer) {
        this.fertilizers.put(fertilizer.getId(), fertilizer);
    }

    public Water getWater(long id) {
        return waters.get(id);
    }

    public Collection<Water> getWaters() {
        return Collections.unmodifiableCollection(waters.values());
    }

    public void addWater(Water water) {
        this.waters.put(water.getId(), water);
    }

    public Light getLight(long id) {
        return lights.get(id);
    }

    public Collection<Light> getLights() {
        return Collections.unmodifiableCollection(lights.values());
    }

    public void addLight(Light light) {
        this.lights.put(light.getId(), light);
    }

    public Temperature getTemperature(long id) {
        return temperatures.get(id);
    }

    public Collection<Temperature> getTemperatures() {
        return Collections.unmodifiableCollection(temperatures.values());
    }

    public void addTemperature(Temperature temperature) {
        this.temperatures.put(temperature.getId(), temperature);
    }

    public Humidity getHumidity(long id) {
        return humidities.get(id);
    }

    public Collection<Humidity> getHumidities() {
        return Collections.unmodifiableCollection(humidities.values());
    }

    public void addHumidity(Humidity humidity) {
        this.humidities.put(humidity.getId(), humidity);
    }

    public Location getLocation(long id) {
        return locations.get(id);
    }

    public Collection<Location> getLocations() {
        return Collections.unmodifiableCollection(locations.values());
    }

    public void addLocation(Location location) {
        this.locations.put(location.getId(), location);
    }
}
