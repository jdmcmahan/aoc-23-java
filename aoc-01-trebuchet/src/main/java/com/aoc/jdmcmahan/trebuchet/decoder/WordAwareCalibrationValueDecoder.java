package com.aoc.jdmcmahan.trebuchet.decoder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordAwareCalibrationValueDecoder implements CalibrationValueDecoder {

    private static final String TOKENS = "([0-9]|one|two|three|four|five|six|seven|eight|nine)";

    private static final Pattern FIRST_DIGIT_PATTERN = Pattern.compile("^.*?" + TOKENS);
    private static final Pattern LAST_DIGIT_PATTERN = Pattern.compile(".*" + TOKENS + ".*?$");

    @Override
    public int decode(String input) {
        return (this.findDigit(input, FIRST_DIGIT_PATTERN) * 10) + this.findDigit(input, LAST_DIGIT_PATTERN);
    }

    protected int findDigit(String input, Pattern pattern) {
        Matcher matcher = pattern.matcher(input);
        if (!matcher.find()) {
            throw new IllegalArgumentException("digit not found for pattern " + pattern + " and input " + input);
        }

        return this.decodeDigit(matcher.group(1));
    }

    protected int decodeDigit(String digit) {
        return switch (digit) {
            case "one" -> 1;
            case "two" -> 2;
            case "three" -> 3;
            case "four" -> 4;
            case "five" -> 5;
            case "six" -> 6;
            case "seven" -> 7;
            case "eight" -> 8;
            case "nine" -> 9;
            default -> Integer.parseInt(digit);
        };
    }
}
