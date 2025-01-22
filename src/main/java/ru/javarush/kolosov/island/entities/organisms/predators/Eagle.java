package ru.javarush.kolosov.island.entities.organisms.predators;

import ru.javarush.kolosov.island.entities.island.Cell;
import ru.javarush.kolosov.island.entities.organisms.Organism;

import java.util.Map;

public class Eagle extends Predator {
    public Eagle(Cell currentCell, double weight, int speed, double needEatToBeFull, Map<Class<? extends Organism>, Integer> eatChances) {
        super(currentCell, weight, speed, needEatToBeFull, eatChances);
    }
}
