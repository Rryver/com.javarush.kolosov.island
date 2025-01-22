package ru.javarush.kolosov.island.entities.organisms.plants;

import lombok.Getter;
import lombok.Setter;
import ru.javarush.kolosov.island.Application;
import ru.javarush.kolosov.island.entities.island.Cell;
import ru.javarush.kolosov.island.entities.organisms.Organism;
import ru.javarush.kolosov.island.repository.PlantCreator;

@Getter
@Setter
public class Plant extends Organism {
    public Plant(Cell currentCell, double weight) {
        super(currentCell, weight);
    }

    @Override
    public void reproduce() {
        synchronized (getCurrentCell().getOrganisms()) {
            long currentCountOnCell = getCurrentCell().getOrganismCount(this.getClass());
            int maxCountOnCell = Application.simulation.getSettings().getOrganismParam(this.getClass()).getMaxCountOnCell();

            if (currentCountOnCell >= maxCountOnCell) {
                return;
            }

            PlantCreator.create(getCurrentCell());
        }
    }
}
