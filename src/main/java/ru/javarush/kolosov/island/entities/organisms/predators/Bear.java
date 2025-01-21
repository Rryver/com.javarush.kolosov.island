package ru.javarush.kolosov.island.entities.organisms.predators;

import ru.javarush.kolosov.island.entities.island.Cell;
import ru.javarush.kolosov.island.entities.organisms.plants.Plant;

public class Bear extends Predator {
    public Bear(Cell currentCell) {
        super(currentCell);

        weight = 500;
        speed = 2;
        needEatToBeFull = 80;

        this.eatChances.put(Plant.class, 100);
    }
}
