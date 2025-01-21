package ru.javarush.kolosov.island.repository;

import ru.javarush.kolosov.island.entities.island.Cell;
import ru.javarush.kolosov.island.entities.organisms.Organism;

import java.lang.reflect.Constructor;

public class OrganismCreator {

    public static <T extends Organism> void create(Class<T> clazz, Cell cell) {
        create(clazz, cell, 1);
    }

    public static <T extends Organism> void create(Class<T> clazz, Cell cell, int count) {
        try {
            Constructor<T> constructor = clazz.getDeclaredConstructor(Cell.class);

            for (int i = 0; i < count; i++) {
                T animal = constructor.newInstance(cell);
                cell.addOrganism(animal);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
