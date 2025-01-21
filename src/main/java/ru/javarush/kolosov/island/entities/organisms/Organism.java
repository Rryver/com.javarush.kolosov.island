package ru.javarush.kolosov.island.entities.organisms;

import lombok.Getter;
import lombok.Setter;
import ru.javarush.kolosov.island.entities.island.Cell;

@Getter
@Setter
abstract public class Organism {
    public static int maxCountOnCell;

    protected float weight;
    protected Cell currentCell;
    protected boolean isAlive = true;

    public Organism(Cell currentCell) {
        this.currentCell = currentCell;
    }

    abstract public void reproduce();

    public void die() {
        isAlive = false;
        getCurrentCell().removeOrganism(this);
    }
}
