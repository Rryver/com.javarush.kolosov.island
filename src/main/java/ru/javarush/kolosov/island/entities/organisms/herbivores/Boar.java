package ru.javarush.kolosov.island.entities.organisms.herbivores;

import ru.javarush.kolosov.island.entities.island.Cell;
import ru.javarush.kolosov.island.entities.organisms.plants.Plant;

public class Boar extends Herbivore {
    public Boar(Cell currentCell) {
        super(currentCell);

        weight = 400;
        speed = 2;
        needEatToBeFull = 50;

        this.eatChances.put(Plant.class, 100);
        this.eatChances.put(Mouse.class, 50);
    }
}
