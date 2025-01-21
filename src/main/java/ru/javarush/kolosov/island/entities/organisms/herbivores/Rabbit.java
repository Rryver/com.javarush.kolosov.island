package ru.javarush.kolosov.island.entities.organisms.herbivores;

import lombok.Getter;
import lombok.Setter;
import ru.javarush.kolosov.island.entities.island.Cell;
import ru.javarush.kolosov.island.entities.organisms.Plant;

@Getter
@Setter
public class Rabbit extends Herbivore {
    public Rabbit(Cell currentCell) {
        super(currentCell);

        weight = 2;
        speed = 2;
        needEatToBeFull = 0.45;

        this.eatChances.put(Plant.class, 60);
    }
}
