package com.pioneers.designpatterns.factory;

import lombok.extern.slf4j.Slf4j;

/**
 * Strategy class to implements all services of the lion.
 * @see AnimalService
 *
 * @author abdelaziz
 */
@Slf4j
public class LionStrategy implements AnimalService {
    @Override
    public void makeSound() {
        log.info("3aaaaaawwww");
    }

    @Override
    public void feed() {
        log.info("Feeding Pig to the Lion");
    }
}
