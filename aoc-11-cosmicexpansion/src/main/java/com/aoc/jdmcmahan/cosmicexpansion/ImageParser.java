package com.aoc.jdmcmahan.cosmicexpansion;

import com.aoc.jdmcmahan.cosmicexpansion.model.Galaxy;
import com.aoc.jdmcmahan.cosmicexpansion.model.Image;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class ImageParser {

    public Image parse(InputStream input) throws IOException {
        try (LineNumberReader reader = new LineNumberReader(new InputStreamReader(input))) {
            String line;

            Image.ImageBuilder builder = Image.builder();
            while ((line = reader.readLine()) != null) {
                int y = reader.getLineNumber();

                int x = -1;
                while ((x = line.indexOf('#', x + 1)) >= 0) {
                    builder.galaxy(new Galaxy(x, y));
                }
            }

            return builder.build();
        }
    }
}
