package com.aoc.jdmcmahan.hotsprings;

import com.aoc.jdmcmahan.hotsprings.model.ConditionRecord;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.Arrays;
import java.util.List;

public class ConditionRecordsParser {

    public List<ConditionRecord> parse(InputStream input) {
        LineNumberReader reader = new LineNumberReader(new InputStreamReader(input));

        return reader.lines()
                .map(line -> {
                    String[] tokens = line.split("\\s+");
                    if (tokens.length != 2) {
                        throw new IllegalArgumentException("Invalid record: " + line);
                    }

                    ConditionRecord.ConditionRecordBuilder builder = ConditionRecord.builder();
                    builder.condition(tokens[0]);

                    Arrays.stream(tokens[1].split(","))
                            .map(Integer::valueOf)
                            .forEach(builder::group);

                    return builder.build();
                })
                .toList();
    }
}
