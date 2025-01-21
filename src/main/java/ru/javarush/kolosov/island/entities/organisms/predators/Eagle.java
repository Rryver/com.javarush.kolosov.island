package ru.javarush.kolosov.island.entities.organisms.predators;

import ru.javarush.kolosov.island.entities.island.Cell;
import ru.javarush.kolosov.island.entities.organisms.plants.Plant;

public class Eagle extends Predator {
    public Eagle(Cell currentCell) {
        super(currentCell);

        weight = 6;
        speed = 3;
        needEatToBeFull = 1;

        this.eatChances.put(Plant.class, 100);
    }
}
