package com.aoc.jdmcmahan.thefloorwillbelava;

import com.aoc.jdmcmahan.thefloorwillbelava.model.Beam;
import com.aoc.jdmcmahan.thefloorwillbelava.model.Contraption;
import com.aoc.jdmcmahan.thefloorwillbelava.model.Direction;
import com.aoc.jdmcmahan.thefloorwillbelava.model.Position;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EnergizedTilesTest {

    @Test
    void testEnergizedTiles_withExampleInput() throws IOException {
        try (InputStream input = EnergizedTilesTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            ContraptionParser parser = new ContraptionParser();
            Contraption contraption = parser.parse(input);

            Beam beam = contraption.beam(Position.valueOf(-1, 0), Direction.RIGHT);
            long result = beam.getEnergizedTiles().size();

            assertEquals(46, result);
        }
    }

    @Test
    void testEnergizedTiles_withChallengeInput() throws IOException {
        try (InputStream input = EnergizedTilesTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            ContraptionParser parser = new ContraptionParser();
            Contraption contraption = parser.parse(input);

            Beam beam = contraption.beam(Position.valueOf(-1, 0), Direction.RIGHT);
            long result = beam.getEnergizedTiles().size();

            assertEquals(8551, result);
        }
    }

    @Test
    void testOptimalEnergy_withExampleInput() throws IOException {
        try (InputStream input = EnergizedTilesTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            ContraptionParser parser = new ContraptionParser();
            Contraption contraption = parser.parse(input);

            int result = contraption.beams().stream()
                    .map(Beam::getEnergizedTiles)
                    .mapToInt(Set::size)
                    .max()
                    .orElse(0);

            assertEquals(51, result);
        }
    }

    @Test
    void testOptimalEnergy_withChallengeInput() throws IOException {
        try (InputStream input = EnergizedTilesTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            ContraptionParser parser = new ContraptionParser();
            Contraption contraption = parser.parse(input);

            int result = contraption.beams().stream()
                    .map(Beam::getEnergizedTiles)
                    .mapToInt(Set::size)
                    .max()
                    .orElse(0);

            assertEquals(8754, result);
        }
    }
}