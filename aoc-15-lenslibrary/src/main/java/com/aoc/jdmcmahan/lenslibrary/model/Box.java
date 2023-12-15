package com.aoc.jdmcmahan.lenslibrary.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Box {

    private final Map<String, Lens> lenses = new LinkedHashMap<>();

    public void addLens(Lens lens) {
        lenses.put(lens.getLabel(), lens);
    }

    public void removeLens(String label) {
        lenses.remove(label);
    }

    public List<Lens> getLenses() {
        return lenses.values().stream()
                .toList();
    }
}
