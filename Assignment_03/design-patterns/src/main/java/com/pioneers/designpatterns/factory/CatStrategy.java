package com.pioneers.designpatterns.factory;

import lombok.extern.slf4j.Slf4j;

/**
 * Strategy class to implements all services of the cat.
 * @see AnimalService
 *
 * @author abdelaziz
 */
@Slf4j
public class CatStrategy implements AnimalService {

    public CatStrategy() {
        super();
    }

    @Override
    public void makeSound() {
        log.info("Mew Mew Mew Mew!!");
    }

    @Override
    public void feed() {
        log.info("Feeding Fish to the Cat");
    }
}
