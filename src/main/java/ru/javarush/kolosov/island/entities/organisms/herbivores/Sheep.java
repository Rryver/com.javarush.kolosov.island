package ru.javarush.kolosov.island.entities.organisms.herbivores;

import ru.javarush.kolosov.island.entities.island.Cell;
import ru.javarush.kolosov.island.entities.organisms.plants.Plant;

public class Sheep extends Herbivore {
    public Sheep(Cell currentCell) {
        super(currentCell);

        weight = 70;
        speed = 3;
        needEatToBeFull = 15;

        this.eatChances.put(Plant.class, 100);
    }
}
