package com.pioneers.designpatterns.factory;

import lombok.extern.slf4j.Slf4j;

/**
 * Strategy class to implements all services of the dog.
 * @see AnimalService
 *
 * @author abdelaziz
 */
@Slf4j
public class DogStrategy implements AnimalService {

    @Override
    public void makeSound() {
        log.info("Haw Haw Haw Haw!!");
    }

    @Override
    public void feed() {
        log.info("Feeding Dry Food to the Dog");
    }
}
