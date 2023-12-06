package com.aoc.jdmcmahan.ifyougiveaseedafertilizer;

import com.aoc.jdmcmahan.ifyougiveaseedafertilizer.model.*;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class AlmanacParser {

    private static final Pattern SEEDS_PATTERN = Pattern.compile("seeds: ([\\d\\s]+)");

    public Almanac parse(InputStream input) throws IOException {
        String document = IOUtils.toString(input, StandardCharsets.UTF_8);

        Matcher seedsMatcher = SEEDS_PATTERN.matcher(document);
        if (!seedsMatcher.find()) {
            throw new IllegalArgumentException("No seeds declaration found");
        }

        Almanac almanac = new Almanac();
        Arrays.stream(seedsMatcher.group(1).split("\\s+"))
                .map(Long::valueOf)
                .map(Seed::new)
                .forEach(almanac::addSeed);

        Map<Seed, Long> seedToSoilMappings = this.parseMappings("seed-to-soil map:", document, almanac.getSeeds());
        this.applyMappings(seedToSoilMappings, Soil::new, Seed::setSoil)
                .forEach(almanac::addSoil);

        Map<Soil, Long> soilToFertilizerMappings = this.parseMappings("soil-to-fertilizer map:", document, almanac.getSoils());
        this.applyMappings(soilToFertilizerMappings, Fertilizer::new, Soil::setFertilizer)
                .forEach(almanac::addFertilizer);

        Map<Fertilizer, Long> fertilizerToWaterMappings = this.parseMappings("fertilizer-to-water map:", document, almanac.getFertilizers());
        this.applyMappings(fertilizerToWaterMappings, Water::new, Fertilizer::setWater)
                .forEach(almanac::addWater);

        Map<Water, Long> waterToLightMappings = this.parseMappings("water-to-light map:", document, almanac.getWaters());
        this.applyMappings(waterToLightMappings, Light::new, Water::setLight)
                .forEach(almanac::addLight);

        Map<Light, Long> lightToTemperatureMappings = this.parseMappings("light-to-temperature map:", document, almanac.getLights());
        this.applyMappings(lightToTemperatureMappings, Temperature::new, Light::setTemperature)
                .forEach(almanac::addTemperature);

        Map<Temperature, Long> temperatureToHumidityMappings = this.parseMappings("temperature-to-humidity map:", document, almanac.getTemperatures());
        this.applyMappings(temperatureToHumidityMappings, Humidity::new, Temperature::setHumidity)
                .forEach(almanac::addHumidity);

        Map<Humidity, Long> humidityToLocationMappings = this.parseMappings("humidity-to-location map:", document, almanac.getHumidities());
        this.applyMappings(humidityToLocationMappings, Location::new, Humidity::setLocation)
                .forEach(almanac::addLocation);

        return almanac;
    }

    protected <S extends Category> Map<S, Long> parseMappings(String header, String document, Collection<S> sources) {
        Pattern pattern = Pattern.compile(header + "\\n([\\d\\s]+)\\n");
        Matcher matcher = pattern.matcher(document);

        if (!matcher.find()) {
            throw new IllegalArgumentException("No mappings found for header " + header);
        }

        String lines = matcher.group(1);

        List<S> queue = new LinkedList<>(sources);
        Map<S, Long> mappings = new HashMap<>();

        Scanner scanner = new Scanner(lines);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] tokens = line.split("\\s+");

            long destinationStart = Long.parseLong(tokens[0]);
            long sourceStart = Long.parseLong(tokens[1]);
            long length = Long.parseLong(tokens[2]);

            for (ListIterator<S> iterator = queue.listIterator(); iterator.hasNext(); ) {
                S current = iterator.next();
                long sourceId = current.getId();

                if (sourceId < sourceStart || sourceId >= sourceStart + length) {
                    continue;
                }

                long destinationId = destinationStart + (sourceId - sourceStart);
                mappings.put(current, destinationId);

                iterator.remove();
            }
        }

        for (S remaining : queue) {
            mappings.put(remaining, remaining.getId());
        }

        return mappings;
    }

    protected <S extends Category, D extends Category> Collection<D> applyMappings(Map<S, Long> mappings, Function<Long, D> destinationCreator, BiConsumer<S, D> mapper) {
        return mappings.entrySet().stream()
                .map(entry -> {
                    S source = entry.getKey();
                    Long destinationId = entry.getValue();

                    D destination = destinationCreator.apply(destinationId);
                    mapper.accept(source, destination);

                    return destination;
                })
                .collect(Collectors.toList());
    }
}
