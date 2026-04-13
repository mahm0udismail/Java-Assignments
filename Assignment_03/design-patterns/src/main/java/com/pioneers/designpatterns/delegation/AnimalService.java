package com.pioneers.designpatterns.delegation;

/**
 * Animal Service for all kind of Animals
 *
 * @author abdelaziz
 */
public interface AnimalService {

    /**
     * Check if bean name equalsIgnorCase to the name of each strategy.
     *
     * @param name Name of the animal.
     * @return true if name is aligned, false otherwise.
     */
    boolean isBeanNameAligned(String name);

    /**
     * Implement the functionality of making sound of the animal strategy.
     */
    void makeSound();

    /**
     * Implement the functionality of feeding of the animal strategy
     */
    void feed();
}
