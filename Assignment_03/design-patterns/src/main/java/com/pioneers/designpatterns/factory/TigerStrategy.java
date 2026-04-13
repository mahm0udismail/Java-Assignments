package com.pioneers.designpatterns.factory;

import lombok.extern.slf4j.Slf4j;

/**
 * Strategy class to implements all services of the tiger.
 * @see AnimalService
 *
 * @author abdelaziz
 */
@Slf4j
public class TigerStrategy implements AnimalService {
    @Override
    public void makeSound() {
        log.info("ne33aaaaaawwww");
    }

    @Override
    public void feed() {
        log.info("Feeding Katkot to the Lion");
    }
}

