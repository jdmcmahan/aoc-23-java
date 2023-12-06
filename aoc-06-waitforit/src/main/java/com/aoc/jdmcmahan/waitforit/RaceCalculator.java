package com.aoc.jdmcmahan.waitforit;

import com.aoc.jdmcmahan.waitforit.model.Race;

public class RaceCalculator {

    protected int winningOptions(Race race) {
        // quadratic formula (thanks kwalker314!)
        long time = race.time();
        long winningDistance = race.recordDistance() + 1;

        double sqrt = Math.sqrt(Math.pow(time, 2) - (4 * winningDistance));
        double x1 = (-time + sqrt) / -2.0;
        double x2 = (-time - sqrt) / -2.0;

        int min = (int) Math.ceil(Math.min(x1, x2));
        int max = (int) Math.floor(Math.max(x1, x2));

        return max - min + 1;
    }
}
