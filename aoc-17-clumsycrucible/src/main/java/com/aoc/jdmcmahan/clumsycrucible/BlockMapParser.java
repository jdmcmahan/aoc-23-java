package com.aoc.jdmcmahan.clumsycrucible;

import com.aoc.jdmcmahan.clumsycrucible.model.Block;
import com.aoc.jdmcmahan.clumsycrucible.model.BlockMap;
import com.aoc.jdmcmahan.clumsycrucible.model.Position;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class BlockMapParser {

    public BlockMap parse(InputStream input) {
        LineNumberReader reader = new LineNumberReader(new InputStreamReader(input));
        BlockMap.BlockMapBuilder builder = BlockMap.builder();

        reader.lines()
                .forEach(row -> {
                    int y = reader.getLineNumber() - 1;

                    for (int x = 0; x < row.length(); x++) {
                        Position position = Position.valueOf(x, y);
                        Block block = new Block(position, Character.getNumericValue(row.charAt(x)));

                        builder.block(block);
                    }
                });

        return builder.build();
    }
}
