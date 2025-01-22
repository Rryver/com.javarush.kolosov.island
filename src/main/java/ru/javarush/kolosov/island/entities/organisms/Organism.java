package ru.javarush.kolosov.island.entities.organisms;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.javarush.kolosov.island.entities.island.Cell;

@Getter
@Setter
abstract public class Organism {
    protected Cell currentCell;
    protected double weight;
    protected boolean isAlive = true;

    public Organism(Cell currentCell, double weight) {
        this.currentCell = currentCell;
        this.weight = weight;
    }

    abstract public void reproduce();

    public void die() {
        isAlive = false;
        getCurrentCell().removeOrganism(this);
    }
}
