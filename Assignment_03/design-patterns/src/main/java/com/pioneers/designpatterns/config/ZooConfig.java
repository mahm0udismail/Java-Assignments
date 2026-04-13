package com.pioneers.designpatterns.config;

import com.pioneers.designpatterns.delegation.AnimalService;
import com.pioneers.designpatterns.delegation.Zookeeper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * A Configuration class to define the beans related to Zoo.
 *
 * @author abdelaziz
 */
@Configuration
public class ZooConfig {

    /**
     * Define the zookeeper bean.
     *
     * @param animalServiceList AnimalSerive strategies injected from the application context.
     * @return Zookeeper bean.
     */
    @Bean
    public Zookeeper zookeeper(List<AnimalService> animalServiceList) {
        return new Zookeeper(animalServiceList);
    }
}
