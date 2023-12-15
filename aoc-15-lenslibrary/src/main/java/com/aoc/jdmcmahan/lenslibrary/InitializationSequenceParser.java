package com.aoc.jdmcmahan.lenslibrary;

import com.aoc.jdmcmahan.lenslibrary.model.InitializationSequence;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class InitializationSequenceParser {

    public InitializationSequence parse(InputStream input) throws IOException {
        String raw = IOUtils.toString(input, StandardCharsets.UTF_8);
        String[] tokens = raw.split(",");

        InitializationSequence.InitializationSequenceBuilder builder = InitializationSequence.builder();
        Arrays.stream(tokens)
                .forEach(builder::step);

        return builder.build();
    }
}
