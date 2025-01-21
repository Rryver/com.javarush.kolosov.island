package ru.javarush.kolosov.island.entities.organisms.herbivores;

import ru.javarush.kolosov.island.entities.island.Cell;
import ru.javarush.kolosov.island.entities.organisms.plants.Plant;

public class Deer extends Herbivore {
    public Deer(Cell currentCell) {
        super(currentCell);

        weight = 300;
        speed = 4;
        needEatToBeFull = 50;

        this.eatChances.put(Plant.class, 100);
    }
}
