package com.aoc.jdmcmahan.ifyougiveaseedafertilizer;

import com.aoc.jdmcmahan.ifyougiveaseedafertilizer.model.Almanac;
import com.aoc.jdmcmahan.ifyougiveaseedafertilizer.model.Range;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AlmanacParser {

    private static final Pattern SEEDS_PATTERN = Pattern.compile("seeds: ([\\d\\s]+)");

    public Almanac parse(InputStream input, SeedRangeParser seedRangeParser) throws IOException {
        String document = IOUtils.toString(input, StandardCharsets.UTF_8);

        Matcher seedsMatcher = SEEDS_PATTERN.matcher(document);
        if (!seedsMatcher.find()) {
            throw new IllegalArgumentException("No seeds declaration found");
        }

        Almanac almanac = new Almanac();

        long[] seedRangeValues = Arrays.stream(seedsMatcher.group(1).split("\\s+"))
                .mapToLong(Long::parseLong)
                .toArray();

        seedRangeParser.parseSeedRanges(seedRangeValues)
                .forEach(almanac::addSeedRange);

        this.parseMappings("seed-to-soil map:", document, almanac.getSeedRanges())
                .forEach(almanac::addSoilRange);

        this.parseMappings("soil-to-fertilizer map:", document, almanac.getSoilRanges())
                .forEach(almanac::addFertilizerRange);

        this.parseMappings("fertilizer-to-water map:", document, almanac.getFertilizerRanges())
                .forEach(almanac::addWaterRange);

        this.parseMappings("water-to-light map:", document, almanac.getWaterRanges())
                .forEach(almanac::addLightRange);

        this.parseMappings("light-to-temperature map:", document, almanac.getLightRanges())
                .forEach(almanac::addTemperatureRange);

        this.parseMappings("temperature-to-humidity map:", document, almanac.getTemperatureRanges())
                .forEach(almanac::addHumidityRange);

        this.parseMappings("humidity-to-location map:", document, almanac.getHumidityRanges())
                .forEach(almanac::addLocationRange);

        return almanac;
    }

    protected Set<Range> parseMappings(String header, String document, Collection<Range> sources) {
        Pattern pattern = Pattern.compile(header + "\\n([\\d\\s]+)\\n");
        Matcher matcher = pattern.matcher(document);

        if (!matcher.find()) {
            throw new IllegalArgumentException("No mappings found for header " + header);
        }

        String lines = matcher.group(1);

        List<Range> queue = new LinkedList<>(sources);
        Set<Range> mappings = new HashSet<>();

        Scanner scanner = new Scanner(lines);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] tokens = line.split("\\s+");

            long destinationStart = Long.parseLong(tokens[0]);
            long sourceStart = Long.parseLong(tokens[1]);
            long length = Long.parseLong(tokens[2]);

            Range mappingRange = new Range(sourceStart, sourceStart + length - 1);

            for (ListIterator<Range> iterator = queue.listIterator(); iterator.hasNext(); ) {
                Range current = iterator.next();
                Range sourceOverlap = current.intersection(mappingRange);

                if (sourceOverlap == null) {
                    continue;
                }

                long offset = sourceOverlap.lowerBound() - sourceStart;
                Range destination = new Range(destinationStart + offset, destinationStart + offset + sourceOverlap.length() - 1);
                mappings.add(destination);

                iterator.remove();

                for (Range leftover : current.difference(sourceOverlap)) {
                    iterator.add(leftover);
                }
            }
        }

        for (Range remaining : queue) {
            mappings.add(new Range(remaining));
        }

        return mappings;
    }

    public interface SeedRangeParser {

        Collection<Range> parseSeedRanges(long... values);
    }

    public static class SingleSeedRangeParser implements SeedRangeParser {

        @Override
        public Collection<Range> parseSeedRanges(long... values) {
            return Arrays.stream(values)
                    .mapToObj(value -> new Range(value, value))
                    .collect(Collectors.toList());
        }
    }

    public static class BountifulSeedRangeParser implements SeedRangeParser {

        @Override
        public Collection<Range> parseSeedRanges(long... values) {
            return IntStream.iterate(0, i -> i < values.length - 1, i -> i + 2)
                    .mapToObj(i -> {
                        long lowerBound = values[i];
                        long length = values[i + 1];

                        return new Range(lowerBound, lowerBound + length - 1);
                    })
                    .collect(Collectors.toList());
        }
    }
}
