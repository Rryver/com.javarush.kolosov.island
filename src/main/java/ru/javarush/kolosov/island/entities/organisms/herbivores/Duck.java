package ru.javarush.kolosov.island.entities.organisms.herbivores;

import ru.javarush.kolosov.island.entities.island.Cell;
import ru.javarush.kolosov.island.entities.organisms.plants.Plant;

public class Duck extends Herbivore {
    public Duck(Cell currentCell) {
        super(currentCell);

        weight = 1;
        speed = 4;
        needEatToBeFull = 0.15;

        this.eatChances.put(Plant.class, 100);
        this.eatChances.put(Caterpillar.class, 90);
    }
}
