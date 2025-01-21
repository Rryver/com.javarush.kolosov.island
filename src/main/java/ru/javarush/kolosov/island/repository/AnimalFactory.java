package ru.javarush.kolosov.island.repository;

import ru.javarush.kolosov.island.entities.island.Cell;
import ru.javarush.kolosov.island.entities.organisms.Animal;
import ru.javarush.kolosov.island.entities.organisms.Organism;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class AnimalFactory {

    public static <T extends Animal> void create(Class<T> clazz, Cell cell) {
        create(clazz, cell, 1);
    }

    public static <T extends Animal> void create(Class<T> clazz, Cell cell, int count) {
        try {
            Constructor<T> constructor = clazz.getDeclaredConstructor(Cell.class);

            for (int i = 0; i < count; i++) {
                T animal = constructor.newInstance(cell);
                cell.addOrganism(animal);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
            //TODO Подумать что делать. Возможно, просто логировать и продолжать работу. Не страшно если одно животное не сгенерируется
        }
    }

}
