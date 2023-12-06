package com.aoc.jdmcmahan.waitforit;

import com.aoc.jdmcmahan.waitforit.model.Race;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RaceParser {

    private static final Pattern TIME_PATTERN = Pattern.compile("Time:\\s+([\\d\\s]+)");
    private static final Pattern DISTANCE_PATTERN = Pattern.compile("Distance:\\s+([\\d\\s]+)");

    public List<Race> parse(InputStream input, Tokenizer tokenizer) throws IOException {
        String document = IOUtils.toString(input, StandardCharsets.UTF_8);

        Matcher timeMatcher = TIME_PATTERN.matcher(document);
        if (!timeMatcher.find()) {
            throw new IllegalArgumentException("No times found");
        }

        Matcher distanceMatcher = DISTANCE_PATTERN.matcher(document);
        if (!distanceMatcher.find()) {
            throw new IllegalArgumentException("No distances found");
        }

        List<String> timeTokens = tokenizer.tokenize(timeMatcher.group(1));
        List<String> distanceTokens = tokenizer.tokenize(distanceMatcher.group(1));

        if (timeTokens.size() != distanceTokens.size()) {
            throw new IllegalArgumentException("Mismatched time count (" + timeTokens.size() + ") and distance count (" + distanceTokens.size() + ")");
        }

        return IntStream.range(0, timeTokens.size())
                .mapToObj(i -> new Race(Long.parseLong(timeTokens.get(i)), Long.parseLong(distanceTokens.get(i))))
                .collect(Collectors.toList());
    }

    public interface Tokenizer {
        List<String> tokenize(String raw);
    }

    public static class TabularTokenizer implements Tokenizer {

        @Override
        public List<String> tokenize(String raw) {
            return Arrays.asList(raw.split("\\s+"));
        }
    }

    public static class BadKerningTokenizer implements Tokenizer {

        @Override
        public List<String> tokenize(String raw) {
            return Collections.singletonList(String.join("", raw.split("\\s+")));
        }
    }
}
