package ru.javarush.kolosov.island.repository;

import ru.javarush.kolosov.island.Application;
import ru.javarush.kolosov.island.config.OrganismParams;
import ru.javarush.kolosov.island.entities.island.Cell;
import ru.javarush.kolosov.island.entities.organisms.Organism;
import ru.javarush.kolosov.island.entities.organisms.plants.Plant;

public class PlantCreator {

    public static <Plant> void create(Cell cell) {
        create(cell, 1);
    }

    public static <T extends Organism> void create(Cell cell, int count) {
        OrganismParams plantParams = Application.simulation.getSettings().getOrganismParam(Plant.class);

        for (int i = 0; i < count; i++) {
            Plant plant = new Plant(cell, plantParams.getWeight());
            cell.addOrganism(plant);
        }
    }
}
