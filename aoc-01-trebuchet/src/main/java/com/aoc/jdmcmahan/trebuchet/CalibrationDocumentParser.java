package com.aoc.jdmcmahan.trebuchet;

import com.aoc.jdmcmahan.trebuchet.decoder.CalibrationValueDecoder;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

@RequiredArgsConstructor
public class CalibrationDocumentParser {

    private final CalibrationValueDecoder valueDecoder;

    public int decode(InputStream input) throws IOException {
        try (LineNumberReader reader = new LineNumberReader(new InputStreamReader(input))) {
            int total = 0;

            String line;
            while ((line = reader.readLine()) != null) {
                int value = valueDecoder.decode(line);
                total += value;
            }

            return total;
        }
    }
}
