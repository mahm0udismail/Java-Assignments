package com.pioneers.designpatterns.delegation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public record Pig() implements AnimalService {

    @Override
    public boolean isBeanNameAligned(final String name) {
        return this.getClass().getSimpleName().equalsIgnoreCase(name);
    }

    @Override
    public void makeSound() {
        log.info("oink oink oink");
    }

    @Override
    public void feed() {
        log.info("Feeding 💩💩💩💩 to Pig!!");
    }
}
