package com.aoc.jdmcmahan.ifyougiveaseedafertilizer.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public record Range(long lowerBound, long upperBound) {

    public Range(Range other) {
        this(other.lowerBound, other.upperBound);
    }

    public long length() {
        return this.upperBound - this.lowerBound + 1;
    }

    public Range intersection(Range other) {
        long lowerBound = Math.max(this.lowerBound, other.lowerBound);
        long upperBound = Math.min(this.upperBound, other.upperBound);

        if (lowerBound > upperBound) {
            return null;
        }

        return new Range(lowerBound, upperBound);
    }

    public List<Range> difference(Range other) {
        if (this.equals(other)) {
            return Collections.emptyList();
        } else if (this.intersection(other) == null) {
            return List.of(this, other);
        }

        List<Range> differences = new LinkedList<>();

        if (this.lowerBound != other.lowerBound) {
            differences.add(new Range(Math.min(this.lowerBound, other.lowerBound), Math.max(this.lowerBound, other.lowerBound) - 1));
        }

        if (this.upperBound != other.upperBound) {
            differences.add(new Range(Math.min(this.upperBound, other.upperBound) + 1, Math.max(this.upperBound, other.upperBound)));
        }

        return differences;
    }

    @Override
    public String toString() {
        return "[" + this.lowerBound + ", " + this.upperBound + "] (length = " + this.length() + ")";
    }
}
