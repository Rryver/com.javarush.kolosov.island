package ru.javarush.kolosov.island.entities.organisms.predators;


import lombok.Getter;
import lombok.Setter;
import ru.javarush.kolosov.island.entities.island.Cell;
import ru.javarush.kolosov.island.entities.organisms.Organism;

import java.util.Map;

@Getter
@Setter
public class Wolf extends Predator {
    public Wolf(Cell currentCell, double weight, int speed, double needEatToBeFull, Map<Class<? extends Organism>, Integer> eatChances) {
        super(currentCell, weight, speed, needEatToBeFull, eatChances);
    }
}
