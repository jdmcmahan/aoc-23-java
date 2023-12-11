package com.aoc.jdmcmahan.pipemaze.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class AbstractPipe implements Pipe {

    @EqualsAndHashCode.Include
    protected final Position position;
}
