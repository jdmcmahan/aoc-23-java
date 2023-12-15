package com.aoc.jdmcmahan.lenslibrary.model;

import lombok.Builder;
import lombok.Singular;

import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

@Builder
public record InitializationSequence(@Singular List<String> steps) {

    private static final Pattern DASH_STEP_PATTERN = Pattern.compile("(\\w+)-");

    private static final Pattern EQUALS_STEP_PATTERN = Pattern.compile("(\\w+)=(\\d+)");

    @Override
    public List<String> steps() {
        return Collections.unmodifiableList(steps);
    }

    public List<Box> generateBoxes() {
        List<Box> boxes = IntStream.range(0, 256)
                .mapToObj(i -> new Box())
                .toList();

        for (String step : steps) {
            Matcher matcher;
            if ((matcher = DASH_STEP_PATTERN.matcher(step)).find()) {
                String label = matcher.group(1);

                int hash = hash(label);
                Box box = boxes.get(hash);

                box.removeLens(label);
            } else if ((matcher = EQUALS_STEP_PATTERN.matcher(step)).find()) {
                String label = matcher.group(1);
                int focalLength = Integer.parseInt(matcher.group(2));

                int hash = hash(label);
                Box box = boxes.get(hash);

                box.addLens(new Lens(label, focalLength));
            } else {
                throw new IllegalArgumentException("Unrecognized step string: " + step);
            }
        }

        return boxes;
    }

    public static int hash(String s) {
        return s.chars()
                .reduce(0, (result, element) -> ((result + element) * 17) % 256);
    }
}
