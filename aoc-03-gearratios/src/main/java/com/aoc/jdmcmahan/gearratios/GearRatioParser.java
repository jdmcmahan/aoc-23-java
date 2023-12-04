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

public class GearRatioParser {

    private static final Pattern PART_NUMBER_PATTERN = Pattern.compile("(\\d+)");

    public List<Integer> parse(InputStream input) throws IOException {
        try (LineNumberReader reader = new LineNumberReader(new InputStreamReader(input))) {
            List<Integer> gearRatios = new LinkedList<>();

            String previousLine = null;
            String currentLine = reader.readLine();

            while (currentLine != null) {
                String nextLine = reader.readLine();

                int gearIndex = currentLine.indexOf('*');
                while (gearIndex >= 0) {
                    List<Integer> partNumbers = new LinkedList<>();
                    int x = gearIndex;

                    Stream.of(previousLine, currentLine, nextLine)
                            .filter(Objects::nonNull)
                            .forEach(line -> {
                                int start = Math.max(x - 1, 0);
                                int end = Math.min(x + 1, line.length() - 1);

                                String adjacent = line.substring(start, end + 1);

                                Matcher partNumberMatcher = PART_NUMBER_PATTERN.matcher(adjacent);
                                while (partNumberMatcher.find()) {
                                    partNumbers.add(this.extractPartNumber(line, start + partNumberMatcher.start(1)));
                                }
                            });

                    if (partNumbers.size() == 2) {
                        gearRatios.add(partNumbers.get(0) * partNumbers.get(1));
                    }

                    gearIndex = currentLine.indexOf('*', gearIndex + 1);
                }

                previousLine = currentLine;
                currentLine = nextLine;
            }

            return gearRatios;
        }
    }

    protected int extractPartNumber(String s, int startIndex) {
        StringBuilder sb = new StringBuilder();

        for (int x = startIndex; x >= 0 && Character.isDigit(s.charAt(x)); x--) {
            sb.insert(0, s.charAt(x));
        }

        for (int x = startIndex + 1; x < s.length() && Character.isDigit(s.charAt(x)); x++) {
            sb.append(s.charAt(x));
        }

        return Integer.parseInt(sb.toString());
    }
}
