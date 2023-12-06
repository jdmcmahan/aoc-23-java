package com.aoc.jdmcmahan.ifyougiveaseedafertilizer.model;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RangeTest {

    @Nested
    class IntersectionTests {

        @Test
        void intersection_withFullyContainedRanges() {
            Range range = new Range(0, 5);
            Range other = new Range(2, 3);

            Range result = range.intersection(other);
            assertEquals(new Range(2, 3), result);

            result = other.intersection(range);
            assertEquals(new Range(2, 3), result);

            range = new Range(0, 4);
            other = new Range(1, 3);

            result = range.intersection(other);
            assertEquals(new Range(1, 3), result);

            result = other.intersection(range);
            assertEquals(new Range(1, 3), result);

            range = new Range(0, 4);
            other = new Range(0, 3);

            result = range.intersection(other);
            assertEquals(new Range(0, 3), result);

            result = other.intersection(range);
            assertEquals(new Range(0, 3), result);

            range = new Range(0, 4);
            other = new Range(1, 4);

            result = range.intersection(other);
            assertEquals(new Range(1, 4), result);

            result = other.intersection(range);
            assertEquals(new Range(1, 4), result);
        }

        @Test
        void intersection_withPartiallyOverlappingRanges() {
            Range range = new Range(0, 3);
            Range other = new Range(1, 4);

            Range result = range.intersection(other);
            assertEquals(new Range(1, 3), result);

            result = other.intersection(range);
            assertEquals(new Range(1, 3), result);
        }

        @Test
        void intersection_withTouchingRanges() {
            Range range = new Range(0, 2);
            Range other = new Range(2, 4);

            Range result = range.intersection(other);
            assertEquals(new Range(2, 2), result);

            result = other.intersection(range);
            assertEquals(new Range(2, 2), result);
        }

        @Test
        void intersection_withNonOverlappingRanges() {
            Range range = new Range(0, 1);
            Range other = new Range(3, 4);

            Range result = range.intersection(other);
            assertNull(result);

            result = other.intersection(range);
            assertNull(result);
        }

        @Test
        void intersection_withSameRanges() {
            Range range = new Range(0, 4);
            Range other = new Range(0, 4);

            Range result = range.intersection(other);
            assertEquals(new Range(0, 4), result);

            result = other.intersection(range);
            assertEquals(new Range(0, 4), result);
        }
    }

    @Nested
    class DifferenceTests {

        @Test
        void difference_withFullyContainedRanges() {
            Range range = new Range(0, 5);
            Range other = new Range(2, 3);

            List<Range> results = range.difference(other);
            assertThat(results).containsExactlyInAnyOrder(new Range(0, 1), new Range(4, 5));

            results = other.difference(range);
            assertThat(results).containsExactlyInAnyOrder(new Range(0, 1), new Range(4, 5));

            range = new Range(0, 4);
            other = new Range(1, 3);

            results = range.difference(other);
            assertThat(results).containsExactlyInAnyOrder(new Range(0, 0), new Range(4, 4));

            results = other.difference(range);
            assertThat(results).containsExactlyInAnyOrder(new Range(0, 0), new Range(4, 4));

            range = new Range(0, 4);
            other = new Range(0, 3);

            results = range.difference(other);
            assertThat(results).containsExactlyInAnyOrder(new Range(4, 4));

            results = other.difference(range);
            assertThat(results).containsExactlyInAnyOrder(new Range(4, 4));

            range = new Range(0, 4);
            other = new Range(1, 4);

            results = range.difference(other);
            assertThat(results).containsExactlyInAnyOrder(new Range(0, 0));

            results = other.difference(range);
            assertThat(results).containsExactlyInAnyOrder(new Range(0, 0));
        }

        @Test
        void difference_withPartiallyOverlappingRanges() {
            Range range = new Range(0, 3);
            Range other = new Range(1, 4);

            List<Range> results = range.difference(other);
            assertThat(results).containsExactlyInAnyOrder(new Range(0, 0), new Range(4, 4));

            results = other.difference(range);
            assertThat(results).containsExactlyInAnyOrder(new Range(0, 0), new Range(4, 4));
        }

        @Test
        void difference_withTouchingRanges() {
            Range range = new Range(0, 2);
            Range other = new Range(2, 4);

            List<Range> results = range.difference(other);
            assertThat(results).containsExactlyInAnyOrder(new Range(0, 1), new Range(3, 4));

            results = other.difference(range);
            assertThat(results).containsExactlyInAnyOrder(new Range(0, 1), new Range(3, 4));
        }

        @Test
        void difference_withNonOverlappingRanges() {
            Range range = new Range(0, 1);
            Range other = new Range(3, 4);

            List<Range> results = range.difference(other);
            assertThat(results).containsExactlyInAnyOrder(new Range(0, 1), new Range(3, 4));

            results = other.difference(range);
            assertThat(results).containsExactlyInAnyOrder(new Range(0, 1), new Range(3, 4));
        }

        @Test
        void difference_withSameRanges() {
            Range range = new Range(0, 4);
            Range other = new Range(0, 4);

            List<Range> results = range.difference(other);
            assertTrue(results.isEmpty());

            results = other.difference(range);
            assertTrue(results.isEmpty());
        }
    }
}