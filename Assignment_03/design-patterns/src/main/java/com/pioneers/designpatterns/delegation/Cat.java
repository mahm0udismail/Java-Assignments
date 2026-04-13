package com.pioneers.designpatterns.delegation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Strategy class to implements all services of the cat.
 *
 * @author abdelaziz
 * @see AnimalService
 */
@Slf4j
@Component
public record Cat() implements AnimalService {

    @Override
    public boolean isBeanNameAligned(final String name) {
        return this.getClass().getSimpleName().equalsIgnoreCase(name);
    }

    @Override
    public void makeSound() {
        log.info("Mew Mew Mew Mew!!");
    }

    @Override
    public void feed() {
        log.info("Feeding 🍤🍤🍤to the Cat");
    }
}
