package com.pioneers.designpatterns.factory;

@Deprecated
public final class AnimalsDefines {

    public static final String CAT = "cat";
    public static final String DOG = "dog";
    public static final String LION = "lion";

    private AnimalsDefines() throws IllegalAccessException {
        throw new IllegalAccessException("Utility class");
    }
}
