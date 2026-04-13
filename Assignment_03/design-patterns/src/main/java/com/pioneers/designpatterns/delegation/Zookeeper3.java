package com.pioneers.designpatterns.delegation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
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
public class Zookeeper3 {

    private final ObjectProvider<AnimalService> animalServiceProvider;
//    private final ObjectProvider<Tiger> tigerObjectProvider;

    public Zookeeper3(@Qualifier("cat") ObjectProvider<AnimalService> animalServiceProvider) {
        this.animalServiceProvider = animalServiceProvider;
    }

    /**
     * Feed the animal by the bean name.
     */
    public void feedAnimal() {
        animalServiceProvider.ifAvailable(animalService -> {
            animalService.feed();
            animalService.makeSound();
        });
    }
}
