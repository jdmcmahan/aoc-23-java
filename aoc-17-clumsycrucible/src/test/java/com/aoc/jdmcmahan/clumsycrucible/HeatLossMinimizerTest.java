package com.aoc.jdmcmahan.clumsycrucible;

import com.aoc.jdmcmahan.clumsycrucible.model.BlockMap;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class HeatLossMinimizerTest {

    @Test
    void testMinimumHeatLoss_withExample1Input() throws IOException {
        try (InputStream input = HeatLossMinimizerTest.class.getClassLoader().getResourceAsStream("example1.txt")) {
            BlockMapParser parser = new BlockMapParser();
            BlockMap blockMap = parser.parse(input);

            int result = blockMap.minimumHeatLoss(blockMap.northwestPosition(), blockMap.southeastPosition(), 0, 3);

            assertEquals(102, result);
        }
    }

    @Test
    void testMinimumHeatLoss_withChallengeInput() throws IOException {
        try (InputStream input = HeatLossMinimizerTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            BlockMapParser parser = new BlockMapParser();
            BlockMap blockMap = parser.parse(input);

            int result = blockMap.minimumHeatLoss(blockMap.northwestPosition(), blockMap.southeastPosition(), 0, 3);

            assertEquals(847, result);
        }
    }

    @Test
    void testUltraCrucibleHeatLoss_withExample1Input() throws IOException {
        try (InputStream input = HeatLossMinimizerTest.class.getClassLoader().getResourceAsStream("example1.txt")) {
            BlockMapParser parser = new BlockMapParser();
            BlockMap blockMap = parser.parse(input);

            int result = blockMap.minimumHeatLoss(blockMap.northwestPosition(), blockMap.southeastPosition(), 4, 10);

            assertEquals(94, result);
        }
    }

    @Test
    void testUltraCrucibleHeatLoss_withExample2Input() throws IOException {
        try (InputStream input = HeatLossMinimizerTest.class.getClassLoader().getResourceAsStream("example2.txt")) {
            BlockMapParser parser = new BlockMapParser();
            BlockMap blockMap = parser.parse(input);

            int result = blockMap.minimumHeatLoss(blockMap.northwestPosition(), blockMap.southeastPosition(), 4, 10);

            assertEquals(71, result);
        }
    }

    @Test
    void testUltraCrucibleHeatLoss_withChallengeInput() throws IOException {
        try (InputStream input = HeatLossMinimizerTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            BlockMapParser parser = new BlockMapParser();
            BlockMap blockMap = parser.parse(input);

            int result = blockMap.minimumHeatLoss(blockMap.northwestPosition(), blockMap.southeastPosition(), 4, 10);

            assertEquals(997, result);
        }
    }
}