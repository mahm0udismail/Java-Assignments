package com.pioneers.designpatterns.delegation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * A delegator class to implements various missions for the animals.
 *
 * @author abdelaziz
 * @see AnimalService
 */
@Slf4j
@Component
public class Zookeeper4 {

    private final Map<String, AnimalService> animalServiceMap;

    @Autowired
    public Zookeeper4(ApplicationContext appContext) {
//        ObjectProvider<AnimalService> animalServiceProvider = appContext.getBeanProvider(AnimalService.class);
        /*this.animalServiceList = appContext.getBeansOfType(AnimalService.class)
                .values()
                .stream()
                .toList();*/

        this.animalServiceMap = appContext.getBeansOfType(AnimalService.class);
    }

    /**
     * Feed the animal by the bean name.
     */
    public void feedAnimal(final String beanName) {
        AnimalService animalService = animalServiceMap.get(beanName);
        Optional.ofNullable(animalService)
                .ifPresentOrElse(AnimalService::feed, () -> logIncorrectAnimalInput(beanName));
    }

    private void logIncorrectAnimalInput(String animalName) {
        log.error("{} Animal in not listed in our system.", animalName);
    }
}
