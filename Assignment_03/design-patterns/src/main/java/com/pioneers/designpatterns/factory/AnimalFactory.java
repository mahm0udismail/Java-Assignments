package com.pioneers.designpatterns.factory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Factory class to build different kind of animals.
 * @see AnimalService
 *
 * @author abdelaziz
 */
@Slf4j
@Component
public class AnimalFactory {

    /**
     * Factory method to build different kind of Animals.
     *
     * @param animal is enum value for all animals.
     * @return Optional AnimalService with the new Animal Strategy.
     */
    public AnimalService createAnimal(final Animals animal) {
        return switch (animal) {
            case CAT -> new CatStrategy();
            case DOG -> new DogStrategy();
            case LION -> new LionStrategy();
            case TIGER -> new TigerStrategy();
        };
    }
}
