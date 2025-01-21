package ru.javarush.kolosov.island.repository;

import ru.javarush.kolosov.island.entities.island.Cell;
import ru.javarush.kolosov.island.entities.organisms.Plant;

public class PlantFactory {

    public static void  create(Cell cell) {
        create(cell, 1);
    }

    public static void create(Cell cell, int count) {
        for (int i = 0; i < count; i++) {
            Plant plant = new Plant(cell);
            cell.addOrganism(plant);
        }
    }
}
