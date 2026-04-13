package com.pioneers.designpatterns.factory;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum Animals {
    CAT("cat"),
    DOG("dog"),
    LION("lion"),
    TIGER("tiger");

    private final String name;

    public static Animals fromName(@NonNull final String name) {
        return Arrays.stream(values())
                .filter(animal -> animal.hasName(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown Animal Name"));
    }

    public boolean hasName(String name) {
        return this.name.equalsIgnoreCase(name);
    }
}
