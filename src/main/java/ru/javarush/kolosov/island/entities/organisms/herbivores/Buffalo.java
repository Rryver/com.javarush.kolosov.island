package ru.javarush.kolosov.island.entities.organisms.herbivores;

import ru.javarush.kolosov.island.entities.island.Cell;
import ru.javarush.kolosov.island.entities.organisms.plants.Plant;

public class Buffalo extends Herbivore {
    public Buffalo(Cell currentCell) {
        super(currentCell);

        weight = 700;
        speed = 3;
        needEatToBeFull = 100;

        this.eatChances.put(Plant.class, 100);
    }
}
