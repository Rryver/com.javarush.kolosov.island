package ru.javarush.kolosov.island.entities.island;

import lombok.Getter;
import lombok.Setter;
import ru.javarush.kolosov.island.entities.organisms.Animal;
import ru.javarush.kolosov.island.entities.organisms.Organism;
import ru.javarush.kolosov.island.entities.organisms.Plant;
import ru.javarush.kolosov.island.entities.organisms.herbivores.Rabbit;
import ru.javarush.kolosov.island.entities.organisms.predators.Wolf;
import ru.javarush.kolosov.island.repository.AnimalFactory;
import ru.javarush.kolosov.island.repository.PlantFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
public class Island {
    private final int width = 3;
    private final int height = 3;

    private final Cell[][] cells = new Cell[width][height];

    public Island() {
        for (int y = 0; y < cells.length; y++) {
            for (int x = 0; x < cells[y].length; x++) {
                Cell cell = new Cell(x, y);
                PlantFactory.create(cell, 10);
                cells[y][x] = cell;
            }
        }

        AnimalFactory.create(Rabbit.class, cells[2][1], 3);
        AnimalFactory.create(Rabbit.class, cells[2][0], 3);
        AnimalFactory.create(Rabbit.class, cells[2][1], 3);
        AnimalFactory.create(Rabbit.class, cells[2][2], 3);

        AnimalFactory.create(Wolf.class, cells[0][1], 2);
    }

    public Cell getCell(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) {
            return null;
        }

        return cells[y][x];
    }

    public List<Animal> getAllAnimals() {
        List<Animal> result = new ArrayList<>();

        for (Cell[] row : cells) {
            for (Cell cell : row) {
                synchronized (cell.getOrganisms()) {
                    List<Animal> list = cell.getOrganisms().stream()
                            .filter(e -> e instanceof Animal)
                            .map(e -> (Animal) e)
                            .toList();

                    result.addAll(list);
                }
            }
        }

        return result;
    }

    public List<Plant> getAllPlants() {
        List<Plant> result = new ArrayList<>();

        for (Cell[] row : cells) {
            for (Cell cell : row) {
                synchronized (cell.getOrganisms()) {
                    List<Plant> list = cell.getOrganisms().stream()
                            .filter(e -> e instanceof Plant)
                            .map(e -> (Plant) e)
                            .toList();

                    result.addAll(list);
                }
            }
        }

        return result;
    }
}
