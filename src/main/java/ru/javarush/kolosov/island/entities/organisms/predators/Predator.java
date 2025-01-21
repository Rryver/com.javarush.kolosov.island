package ru.javarush.kolosov.island.entities.organisms.predators;

import lombok.Getter;
import lombok.Setter;
import ru.javarush.kolosov.island.entities.island.Cell;
import ru.javarush.kolosov.island.entities.organisms.Animal;

@Getter
@Setter
abstract public class Predator extends Animal {

    public Predator(Cell currentCell) {
        super(currentCell);
    }
}
