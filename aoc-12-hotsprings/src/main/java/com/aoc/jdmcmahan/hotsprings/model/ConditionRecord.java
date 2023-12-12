package com.aoc.jdmcmahan.hotsprings.model;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Singular;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Builder
public record ConditionRecord(String condition, @Singular List<Integer> groups) {

    public long arrangements() {
        return new ArrangementCounter(this).count();
    }

    @RequiredArgsConstructor
    protected static class ArrangementCounter {

        private static final Map<CacheKey, Long> CACHE = new HashMap<>();

        private final ConditionRecord record;

        public long count() {
            return count(record.condition(), record.groups());
        }

        protected static long count(String condition, List<Integer> groups) {
            CacheKey cacheKey = new CacheKey(condition, groups);
            if (CACHE.containsKey(cacheKey)) {
                return CACHE.get(cacheKey);
            }

            if (condition.isEmpty()) {
                return groups.isEmpty() ? 1 : 0;
            }

            char current = condition.charAt(0);
            long arrangements = 0;

            if (current == '.') {
                arrangements = count(condition.substring(1), groups);
            } else if (current == '?') {
                String remainder = condition.substring(1);
                String working = "." + remainder;
                String damaged = "#" + remainder;

                arrangements = count(working, groups) + count(damaged, groups);
            } else if (current == '#') {
                if (!groups.isEmpty()) {
                    int size = groups.getFirst();

                    if (condition.matches("^[#?]{" + size + "}.*")) {
                        groups = groups.subList(1, groups.size());

                        if (size == condition.length()) {
                            arrangements = groups.isEmpty() ? 1 : 0;
                        } else {
                            String remainder = condition.substring(size + 1);
                            arrangements = switch (condition.charAt(size)) {
                                case '.' -> count(remainder, groups);
                                case '?' -> count("." + remainder, groups);
                                default -> 0;
                            };
                        }
                    }
                }
            } else {
                throw new IllegalArgumentException("Invalid token: " + current);
            }

            CACHE.put(cacheKey, arrangements);
            return arrangements;
        }

        protected record CacheKey(String record, List<Integer> groups) {
        }
    }
}
