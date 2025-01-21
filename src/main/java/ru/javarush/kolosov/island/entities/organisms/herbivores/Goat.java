package ru.javarush.kolosov.island.entities.organisms.herbivores;

import ru.javarush.kolosov.island.entities.island.Cell;
import ru.javarush.kolosov.island.entities.organisms.plants.Plant;

public class Goat extends Herbivore{
    public Goat(Cell currentCell) {
        super(currentCell);

        weight = 60;
        speed = 3;
        needEatToBeFull = 10;

        this.eatChances.put(Plant.class, 100);
    }
}
