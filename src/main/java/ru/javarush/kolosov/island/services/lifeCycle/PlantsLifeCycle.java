package ru.javarush.kolosov.island.services.lifeCycle;

import ru.javarush.kolosov.island.entities.island.Island;
import ru.javarush.kolosov.island.entities.organisms.plants.Plant;

import java.util.ArrayList;
import java.util.List;

public class PlantsLifeCycle extends LifeCycle {
    public PlantsLifeCycle(Island island, int lifeCycleIntervalMillis) {
        super(island, lifeCycleIntervalMillis);
    }

    @Override
    protected Runnable getTaskCreatingAlgorithm() {
        return () -> {
            List<Plant> allPlants = island.getAllPlants();
            List<PlantTask> plantTasks = new ArrayList<>();
            for (Plant plant : allPlants) {
                plantTasks.add(new PlantTask(plant));
            }
            plantTasks.forEach(lifeCycleExecutorService::submit);
        };
    }
}
