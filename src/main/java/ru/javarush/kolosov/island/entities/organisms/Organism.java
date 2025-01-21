package ru.javarush.kolosov.island.entities.organisms;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import ru.javarush.kolosov.island.entities.island.Cell;

@Getter
@Setter
abstract public class Organism {
    protected float weight;
    protected int maxCountOnCell;
    protected Cell currentCell;

    @NonNull
    protected OrganismIcon icon;

    public Organism(Cell currentCell) {
        this.currentCell = currentCell;
    }

    abstract public void reproduce();

    public void die() {
        getCurrentCell().removeOrganism(this);
    }
}
