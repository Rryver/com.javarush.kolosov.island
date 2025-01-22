package ru.javarush.kolosov.island.repository;

import ru.javarush.kolosov.island.Application;
import ru.javarush.kolosov.island.config.OrganismParams;
import ru.javarush.kolosov.island.entities.island.Cell;
import ru.javarush.kolosov.island.entities.organisms.Animal;

import java.lang.reflect.Constructor;
import java.util.Map;

public class AnimalCreator {

    public static <T extends Animal> void create(Class<T> clazz, Cell cell) {
        create(clazz, cell, 1);
    }

    public static <T extends Animal> void create(Class<T> clazz, Cell cell, int count) {
        try {
            Constructor<T> constructor = clazz.getDeclaredConstructor(Cell.class, double.class, int.class, double.class, Map.class);
            OrganismParams animalParams = Application.simulation.getSettings().getOrganismParam(clazz);

            for (int i = 0; i < count; i++) {
                T animal = constructor.newInstance(cell,
                        animalParams.getWeight(),
                        animalParams.getSpeed(),
                        animalParams.getNeedEatToBeFull(),
                        animalParams.getEatChances());

                cell.addOrganism(animal);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
