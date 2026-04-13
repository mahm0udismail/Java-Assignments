package com.pioneers.designpatterns.delegation;

public abstract class Tortoise implements AnimalService {

    @Override
    public boolean isBeanNameAligned(String name) {
        return false;
    }

    @Override
    public void feed() {

    }
}
