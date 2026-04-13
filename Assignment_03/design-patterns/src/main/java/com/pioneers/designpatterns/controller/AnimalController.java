package com.pioneers.designpatterns.controller;

import com.pioneers.designpatterns.delegation.*;
import com.pioneers.designpatterns.factory.AnimalFactory;
import com.pioneers.designpatterns.factory.AnimalService;
import com.pioneers.designpatterns.factory.Animals;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("animal-service")
public class AnimalController {

    private final AnimalFactory animalFactory;
    private final Zookeeper zookeeper;
    private final Zookeeper2 zookeeper2;
    private final Zookeeper3 zookeeper3;
    private final Zookeeper4 zookeeper4;
    private final SubTortoise tortoise;

    @GetMapping("factory/{animal}")
    public void animalServiceWithFactoryApi(@PathVariable(value = "animal") String animalName) {
        try {
            final Animals animalEnum = Animals.fromName(animalName);

            final AnimalService animalService = animalFactory.createAnimal(animalEnum);

            animalService.makeSound();
            animalService.feed();
        } catch (IllegalArgumentException e) {
            log.error("Unknown Animal Name");
        }
    }

    @GetMapping("delegation/{animal}")
    public void animalServiceWithDelegatorApi(@PathVariable(name = "animal") String animalName) {
        zookeeper.feedAnimal(animalName);
    }

    @GetMapping("feedAllAnimals")
    public void feedAllAnimalsApi() {
        zookeeper.feedAllAnimals();
    }

    @GetMapping("makeAllAnimalsSound")
    public void makeAllAnimalsSoundApi() {
        zookeeper.makeAllAnimalsSound();
    }

    @GetMapping("feedSingleAnimal")
    public void feedSingleAnimalApi() {
        zookeeper2.feedAnimal();
    }

    @GetMapping("feedSingleAnimalByAppContext/{beanName}")
    public void feedSingleAnimalByAppContextApi(@PathVariable String beanName) {
        System.out.println("tortoise.isBeanNameAligned(beanName) = " + tortoise.isBeanNameAligned(beanName));
    }
}
