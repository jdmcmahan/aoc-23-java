package com.aoc.jdmcmahan.trebuchet;

import com.aoc.jdmcmahan.trebuchet.decoder.NumericCalibrationValueDecoder;
import com.aoc.jdmcmahan.trebuchet.decoder.WordAwareCalibrationValueDecoder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrebuchetTest {

    @Test
    void testCalibration_withExampleInput() throws IOException {
        try (InputStream input = TrebuchetTest.class.getClassLoader().getResourceAsStream("example1.txt")) {
            CalibrationDocumentParser parser = new CalibrationDocumentParser(new NumericCalibrationValueDecoder());
            int result = parser.decode(input);

            assertEquals(142, result);
        }
    }

    @Test
    void testCalibration_withChallengeInput() throws IOException {
        try (InputStream input = TrebuchetTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            CalibrationDocumentParser parser = new CalibrationDocumentParser(new NumericCalibrationValueDecoder());
            int result = parser.decode(input);

            assertEquals(54940, result);
        }
    }

    @Test
    void testCalibrationWithWords_withExampleInput() throws IOException {
        try (InputStream input = TrebuchetTest.class.getClassLoader().getResourceAsStream("example2.txt")) {
            CalibrationDocumentParser parser = new CalibrationDocumentParser(new WordAwareCalibrationValueDecoder());
            int result = parser.decode(input);

            assertEquals(281, result);
        }
    }

    @Test
    void testCalibrationWithWords_withChallengeInput() throws IOException {
        try (InputStream input = TrebuchetTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            CalibrationDocumentParser parser = new CalibrationDocumentParser(new WordAwareCalibrationValueDecoder());
            int result = parser.decode(input);

            assertEquals(54208, result);
        }
    }
}
