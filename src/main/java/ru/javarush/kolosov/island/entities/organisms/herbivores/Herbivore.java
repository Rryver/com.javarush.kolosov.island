package ru.javarush.kolosov.island.entities.organisms.herbivores;

import lombok.Getter;
import lombok.Setter;
import ru.javarush.kolosov.island.entities.island.Cell;
import ru.javarush.kolosov.island.entities.organisms.Animal;

@Getter
@Setter
public class Herbivore extends Animal {
    public Herbivore(Cell currentCell) {
        super(currentCell);
    }
}
