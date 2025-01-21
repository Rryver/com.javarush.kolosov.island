package ru.javarush.kolosov.island.entities.organisms.predators;


import lombok.Getter;
import lombok.Setter;
import ru.javarush.kolosov.island.entities.organisms.OrganismIcon;
import ru.javarush.kolosov.island.entities.island.Cell;
import ru.javarush.kolosov.island.entities.organisms.herbivores.Rabbit;

//@ConfigFIle("/wolf.yaml")
@Getter
@Setter
public class Wolf extends Predator {

    public Wolf(Cell currentCell) {
        super(currentCell);

        weight = 50;
        maxCountOnCell = 30;
        speed = 3;
        needEatToBeFull = 8;
        icon = OrganismIcon.WOLF;

        this.eatChances.put(Rabbit.class, 60);
    }


}
