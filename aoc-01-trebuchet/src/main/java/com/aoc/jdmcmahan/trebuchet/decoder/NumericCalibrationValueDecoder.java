package com.aoc.jdmcmahan.trebuchet.decoder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumericCalibrationValueDecoder implements CalibrationValueDecoder {

    private static final Pattern FIRST_DIGIT_PATTERN = Pattern.compile("^.*?([0-9])");
    private static final Pattern LAST_DIGIT_PATTERN = Pattern.compile(".*([0-9]).*?$");

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
        return Integer.parseInt(digit);
    }
}
