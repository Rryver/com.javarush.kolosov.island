package ru.javarush.kolosov.island.entities.organisms;

import lombok.Getter;
import lombok.Setter;
import ru.javarush.kolosov.island.entities.island.Cell;
import ru.javarush.kolosov.island.repository.PlantFactory;

@Getter
@Setter
public class Plant extends Organism {

    public Plant(Cell currentCell) {
        super(currentCell);

        this.weight = 1;
        this.maxCountOnCell = 200;
        icon = OrganismIcon.PLANT;
    }

    @Override
    public void reproduce() {
        synchronized (getCurrentCell().getOrganisms()) {
            long organismsCount = getCurrentCell().getOrganismCount(this.getClass());
            if (organismsCount >= this.getMaxCountOnCell()) {
                return;
            }

            PlantFactory.create(getCurrentCell());
        }
    }
}
