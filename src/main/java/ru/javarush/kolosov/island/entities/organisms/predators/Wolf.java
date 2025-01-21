package ru.javarush.kolosov.island.entities.organisms.predators;


import lombok.Getter;
import lombok.Setter;
import ru.javarush.kolosov.island.entities.island.Cell;
import ru.javarush.kolosov.island.entities.organisms.herbivores.Rabbit;

@Getter
@Setter
public class Wolf extends Predator {
    public Wolf(Cell currentCell) {
        super(currentCell);

        weight = 50;
        speed = 3;
        needEatToBeFull = 8;

        this.eatChances.put(Rabbit.class, 60);
    }


}
