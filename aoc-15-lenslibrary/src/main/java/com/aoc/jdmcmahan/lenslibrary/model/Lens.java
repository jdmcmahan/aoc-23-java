package com.aoc.jdmcmahan.lenslibrary.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Lens {

    private final String label;

    private final int focalLength;
}
