package com.pioneers.designpatterns.delegation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Strategy class to implements all services of the tiger.
 * @see AnimalService
 *
 * @author abdelaziz
 */
@Slf4j
//@Component
public record Tiger() implements AnimalService {

    public Tiger {
        System.out.println("");
    }

    @Override
    public boolean isBeanNameAligned(final String name) {
        return this.getClass().getSimpleName().equalsIgnoreCase(name);
    }

    @Override
    public void makeSound() {
        log.info("ne33aaaaaawwww");
    }

    @Override
    public void feed() {
        log.info("Feeding 🐱🐱🐱🐱to the Tiger");
    }
}

