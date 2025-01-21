package ru.javarush.kolosov.island.entities.organisms.herbivores;

import ru.javarush.kolosov.island.entities.island.Cell;
import ru.javarush.kolosov.island.entities.organisms.plants.Plant;

public class Mouse extends Herbivore {
    public Mouse(Cell currentCell) {
        super(currentCell);

        weight = 0.05;
        speed = 1;
        needEatToBeFull = 0.01;

        this.eatChances.put(Plant.class, 100);
        this.eatChances.put(Caterpillar.class, 100);
    }
}
