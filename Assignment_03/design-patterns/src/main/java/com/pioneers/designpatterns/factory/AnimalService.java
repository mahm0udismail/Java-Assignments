package com.pioneers.designpatterns.factory;

/**
 * Animal Service for all kind of Animals
 *
 * @author abdelaziz
 */
public interface AnimalService {

    /**
     * Implement the functionality of making sound of the animal strategy.
     */
    void makeSound();

    /**
     * Implement the functionality of feeding of the animal strategy
     */
    void feed();
}
