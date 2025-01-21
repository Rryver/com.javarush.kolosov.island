package ru.javarush.kolosov.island.entities.organisms.predators;

import ru.javarush.kolosov.island.entities.island.Cell;
import ru.javarush.kolosov.island.entities.organisms.plants.Plant;

public class Boa extends Predator{
    public Boa(Cell currentCell) {
        super(currentCell);

        weight = 15;
        speed = 1;
        needEatToBeFull = 3;

        this.eatChances.put(Plant.class, 100);
    }
}
