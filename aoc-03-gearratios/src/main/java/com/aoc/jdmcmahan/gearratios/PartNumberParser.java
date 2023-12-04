package com.aoc.jdmcmahan.gearratios;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class PartNumberParser {

    private static final Pattern PART_NUMBER_PATTERN = Pattern.compile("(\\d+)");

    public List<Integer> parse(InputStream input) throws IOException {
        try (LineNumberReader reader = new LineNumberReader(new InputStreamReader(input))) {
            List<Integer> partNumbers = new LinkedList<>();

            String previousLine = null;
            String currentLine = reader.readLine();

            while (currentLine != null) {
                String nextLine = reader.readLine();

                Matcher partNumberMatcher = PART_NUMBER_PATTERN.matcher(currentLine);
                while (partNumberMatcher.find()) {
                    boolean adjacentToSymbol = Stream.of(previousLine, currentLine, nextLine)
                            .filter(Objects::nonNull)
                            .anyMatch(line -> {
                                int start = Math.max(partNumberMatcher.start() - 1, 0);
                                int end = Math.min(partNumberMatcher.end() + 1, line.length() - 1);

                                return this.containsSymbol(line.substring(start, end));
                            });

                    if (adjacentToSymbol) {
                        partNumbers.add(Integer.valueOf(partNumberMatcher.group(1)));
                    }
                }

                previousLine = currentLine;
                currentLine = nextLine;
            }

            return partNumbers;
        }
    }

    protected boolean containsSymbol(String s) {
        return s.chars().anyMatch(c -> c != '.' && !Character.isDigit(c));
    }
}
