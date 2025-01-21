package ru.javarush.kolosov.island.entities.organisms;

import lombok.Getter;
import lombok.Setter;
import ru.javarush.kolosov.island.entities.island.Cell;
import ru.javarush.kolosov.island.repository.OrganismFactory;

@Getter
@Setter
public class Plant extends Organism {
    public Plant(Cell currentCell) {
        super(currentCell);

        this.weight = 1;
        maxCountOnCell = 200;
    }

    @Override
    public void reproduce() {
        synchronized (getCurrentCell().getOrganisms()) {
            long organismsCount = getCurrentCell().getOrganismCount(this.getClass());
            if (organismsCount >= maxCountOnCell) {
                return;
            }

            OrganismFactory.create(this.getClass(), getCurrentCell());
        }
    }
}
