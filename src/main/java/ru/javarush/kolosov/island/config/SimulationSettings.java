package ru.javarush.kolosov.island.config;

import lombok.Data;
import ru.javarush.kolosov.island.entities.organisms.Organism;
import ru.javarush.kolosov.island.entities.organisms.Plant;
import ru.javarush.kolosov.island.entities.organisms.herbivores.Rabbit;
import ru.javarush.kolosov.island.entities.organisms.predators.Wolf;

import java.util.HashSet;
import java.util.Set;

@Data
public class SimulationSettings {
    private int islandWidth = 3;
    private int islandHeight = 3;
    private int daysWithoutEatToDie = 3;
    private int stopSimulationAfterDays = 20;

    public static Set<Class<? extends Organism>> availableOrganismClazz = new HashSet<>();

    static {
        initAvailableAnimalsClazz();
    }

    public SimulationSettings() {
//        initAvailableAnimalsClazz();
    }

    private static void initAvailableAnimalsClazz() {
        availableOrganismClazz.add(Plant.class);
        availableOrganismClazz.add(Wolf.class);
        availableOrganismClazz.add(Rabbit.class);
    }
}
