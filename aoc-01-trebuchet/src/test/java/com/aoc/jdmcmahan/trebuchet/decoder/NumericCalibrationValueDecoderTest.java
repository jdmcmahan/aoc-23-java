package com.aoc.jdmcmahan.trebuchet.decoder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NumericCalibrationValueDecoderTest {

    private NumericCalibrationValueDecoder decoder;

    @BeforeEach
    void createDecoder() {
        this.decoder = new NumericCalibrationValueDecoder();
    }

    @Test
    void decodeReturnsCalibrationValue_withDigitsAtStartAndEnd() {
        assertEquals(12, decoder.decode("1abc2"));
    }

    @Test
    void decodeReturnsCalibrationValue_withInternalDigits() {
        assertEquals(38, decoder.decode("pqr3stu8vwx"));
        assertEquals(15, decoder.decode("a1b2c3d4e5f"));
    }

    @Test
    void decodeReturnsCalibrationValue_withSingleDigit() {
        assertEquals(77, decoder.decode("treb7uchet"));
    }
}