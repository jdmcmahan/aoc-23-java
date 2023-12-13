package com.aoc.jdmcmahan.pointofincidence;

import com.aoc.jdmcmahan.pointofincidence.model.Pattern;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.LinkedList;
import java.util.List;

public class PatternsParser {

    public List<Pattern> parse(InputStream input, int smudgeFactor) throws IOException {
        try (LineNumberReader reader = new LineNumberReader(new InputStreamReader(input))) {
            String line;

            List<Pattern> patterns = new LinkedList<>();
            Pattern.PatternBuilder builder = Pattern.builder()
                    .smudgeFactor(smudgeFactor);

            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    patterns.add(builder.build());
                    builder = Pattern.builder()
                            .smudgeFactor(smudgeFactor);
                } else {
                    builder.row(line);
                }
            }

            patterns.add(builder.build());
            return patterns;
        }
    }
}
