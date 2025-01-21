package ru.javarush.kolosov.island.entities.organisms.predators;

import ru.javarush.kolosov.island.entities.island.Cell;
import ru.javarush.kolosov.island.entities.organisms.plants.Plant;

public class Fox extends Predator {
    public Fox(Cell currentCell) {
        super(currentCell);

        weight = 8;
        speed = 2;
        needEatToBeFull = 2;

        this.eatChances.put(Plant.class, 100);
    }
}
