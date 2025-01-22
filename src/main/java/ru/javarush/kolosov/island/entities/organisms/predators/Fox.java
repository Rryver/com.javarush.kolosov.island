package ru.javarush.kolosov.island.entities.organisms.predators;

import ru.javarush.kolosov.island.entities.island.Cell;
import ru.javarush.kolosov.island.entities.organisms.Organism;
import ru.javarush.kolosov.island.entities.organisms.plants.Plant;

import java.util.Map;

public class Fox extends Predator {
    public Fox(Cell currentCell, double weight, int speed, double needEatToBeFull, Map<Class<? extends Organism>, Integer> eatChances) {
        super(currentCell, weight, speed, needEatToBeFull, eatChances);
    }
}
