package ru.javarush.kolosov.island.entities.island;

import lombok.Getter;
import lombok.Setter;
import ru.javarush.kolosov.island.Application;
import ru.javarush.kolosov.island.config.SimulationSettings;
import ru.javarush.kolosov.island.entities.organisms.Animal;
import ru.javarush.kolosov.island.entities.organisms.Organism;
import ru.javarush.kolosov.island.entities.organisms.plants.Plant;
import ru.javarush.kolosov.island.repository.AnimalCreator;
import ru.javarush.kolosov.island.repository.PlantCreator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
public class Island {
    private final int width;
    private final int height;

    private final Cell[][] cells;

    public Island(SimulationSettings settings) {
        this.width = settings.getIslandWidth();
        this.height = settings.getIslandHeight();
        this.cells = new Cell[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Cell cell = new Cell(x, y, this);
                cells[y][x] = cell;

                for (Class<? extends Organism> organismClazz : settings.getAvailableOrganismClazz()) {
                    int count = Application.simulation.getSettings().getOrganismParam(organismClazz).getMaxCountOnCell() / 2 + 1;

                    if (organismClazz == Plant.class) {
                        PlantCreator.create(cell, count);
                    } else {
                        AnimalCreator.create(organismClazz.asSubclass(Animal.class), cell, ThreadLocalRandom.current().nextInt(0, count));
                    }
                }
            }
        }
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
