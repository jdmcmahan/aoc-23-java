package com.aoc.jdmcmahan.hauntedwasteland;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NetworkParser {

    private static final Pattern NODE_PATTERN = Pattern.compile("^(\\w{3})\\s*=\\s*\\((\\w{3}),\\s*(\\w{3})\\)$");

    public Network parse(InputStream input) throws IOException {
        try (LineNumberReader reader = new LineNumberReader(new InputStreamReader(input))) {
            String line = reader.readLine();
            if (line == null) {
                throw new IllegalArgumentException("Input is empty");
            }

            List<Network.Instruction> instructions = line.chars()
                    .mapToObj(instruction -> {
                        if (instruction == 'L') {
                            return Network.Instruction.MOVE_LEFT;
                        } else if (instruction == 'R') {
                            return Network.Instruction.MOVE_RIGHT;
                        } else {
                            throw new IllegalArgumentException("Unrecognized instruction: " + instruction);
                        }
                    })
                    .toList();

            Network network = new Network(instructions);
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    continue;
                }

                Matcher nodeMatcher = NODE_PATTERN.matcher(line);
                if (!nodeMatcher.find()) {
                    throw new IllegalArgumentException("Invalid node definition: " + line);
                }

                String id = nodeMatcher.group(1);
                String leftId = nodeMatcher.group(2);
                String rightId = nodeMatcher.group(3);

                network.createNode(id, leftId, rightId);
            }

            return network;
        }
    }
}
