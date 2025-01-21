package ru.javarush.kolosov.island.entities.organisms.herbivores;

import ru.javarush.kolosov.island.entities.island.Cell;
import ru.javarush.kolosov.island.entities.organisms.plants.Plant;

public class Horse extends Herbivore{
    public Horse(Cell currentCell) {
        super(currentCell);

        weight = 400;
        speed = 4;
        needEatToBeFull = 60;

        this.eatChances.put(Plant.class, 100);
    }
}
