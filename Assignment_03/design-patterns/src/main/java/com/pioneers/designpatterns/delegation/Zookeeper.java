package com.pioneers.designpatterns.delegation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * A delegator class to implements various missions for the animals.
 *
 * @author abdelaziz
 * @see AnimalService
 */
@Slf4j
@RequiredArgsConstructor
public class Zookeeper {

    private final List<AnimalService> animalServiceList;

    /**
     * Feed all animals in the system.
     */
    public void feedAllAnimals() {
        animalServiceList.forEach(AnimalService::feed);
    }

    /**
     * Feed animal by its name.
     *
     * @param animalName Name of the animal.
     */
    public void feedAnimal(final String animalName) {
        animalServiceList.stream()
                .filter(animalService -> animalService.isBeanNameAligned(animalName))
                .findFirst()
                .ifPresentOrElse(AnimalService::feed, () -> logIncorrectAnimalInput(animalName));
    }

    /**
     * Force all animals to step out their sounds.
     */
    public void makeAllAnimalsSound() {
        animalServiceList.forEach(AnimalService::makeSound);
    }

    private void logIncorrectAnimalInput(String animalName) {
        log.error("{} Animal in not listed in our system.", animalName);
    }
}
