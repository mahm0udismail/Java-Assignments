package com.pioneers.designpatterns.delegation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * A delegator class to implements various missions for the animals.
 *
 * @author abdelaziz
 * @see AnimalService
 */
@Slf4j
@Component
public class Zookeeper2 {

    private final AnimalService animalService;

    public Zookeeper2(@Qualifier("cat") AnimalService animalService) {
        this.animalService = animalService;
    }

    /**
     * Feed the animal.
     */
    public void feedAnimal() {
        animalService.feed();
    }
}
