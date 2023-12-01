package com.aoc.jdmcmahan.trebuchet.decoder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WordAwareCalibrationValueDecoderTest {

    private WordAwareCalibrationValueDecoder decoder;

    @BeforeEach
    void createDecoder() {
        this.decoder = new WordAwareCalibrationValueDecoder();
    }

    @Test
    void decodeReturnsCalibrationValue_withWordsAtStartAndEnd() {
        assertEquals(29, decoder.decode("two1nine"));
        assertEquals(83, decoder.decode("eightwothree"));
    }

    @Test
    void decodeReturnsCalibrationValue_withInternalWords() {
        assertEquals(13, decoder.decode("abcone2threexyz"));
        assertEquals(24, decoder.decode("xtwone3four"));
    }

    @Test
    void decodeReturnsCalibrationValue_withDigitsAtStartAndEnd() {
        assertEquals(42, decoder.decode("4nineeightseven2"));
    }

    @Test
    void decodeReturnsCalibrationValue_withMixOfDigitsAndWords() {
        assertEquals(14, decoder.decode("zoneight234"));
    }

    @Test
    void decodeReturnsCalibrationValue_withOutOfBoundsWords() {
        assertEquals(76, decoder.decode("7pqrstsixteen"));
    }
}