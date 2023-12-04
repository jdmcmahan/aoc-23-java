package com.aoc.jdmcmahan.scratchcards;

import com.aoc.jdmcmahan.scratchcards.model.Scratchcard;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ScratchcardParser {

    private static final Pattern SCRATCHCARD_PATTERN = Pattern.compile("Card\\s+(\\d+):\\s*([\\d\\s]+)\\s*\\|\\s*([\\d\\s]+)");

    public List<Scratchcard> parse(InputStream input) {
        LineNumberReader reader = new LineNumberReader(new InputStreamReader(input));
        return reader.lines()
                .map(row -> {
                    Matcher cardMatcher = SCRATCHCARD_PATTERN.matcher(row);
                    if (!cardMatcher.find()) {
                        throw new IllegalArgumentException("Invalid input: " + row);
                    }

                    Scratchcard.ScratchcardBuilder builder = Scratchcard.builder();
                    builder.id(Integer.parseInt(cardMatcher.group(1)));

                    Arrays.stream(cardMatcher.group(2).split("\\s+"))
                            .map(Integer::valueOf)
                            .forEach(builder::winningNumber);

                    Arrays.stream(cardMatcher.group(3).split("\\s+"))
                            .map(Integer::valueOf)
                            .forEach(builder::number);

                    return builder.build();
                })
                .collect(Collectors.toList());
    }
}
