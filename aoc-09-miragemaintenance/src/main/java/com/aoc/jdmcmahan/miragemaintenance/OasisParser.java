package com.aoc.jdmcmahan.miragemaintenance;

import com.aoc.jdmcmahan.miragemaintenance.model.History;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.Arrays;
import java.util.List;

public class OasisParser {

    public List<History> parse(InputStream input) {
        LineNumberReader reader = new LineNumberReader(new InputStreamReader(input));
        return reader.lines()
                .map(row -> {
                    History.HistoryBuilder builder = History.builder();
                    Arrays.stream(row.split("\\s+"))
                            .mapToLong(Long::parseLong)
                            .forEachOrdered(builder::value);

                    return builder.build();
                })
                .toList();
    }
}
