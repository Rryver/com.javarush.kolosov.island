package ru.javarush.kolosov.island.services.lifeCycle;

import ru.javarush.kolosov.island.entities.island.Island;
import ru.javarush.kolosov.island.entities.organisms.Animal;

import java.util.ArrayList;
import java.util.List;

public class AnimalsLifeCycle extends LifeCycle {
    public AnimalsLifeCycle(Island island, int lifeCycleIntervalMillis) {
        super(island, lifeCycleIntervalMillis);
    }

    @Override
    protected Runnable getTaskCreatingAlgorithm() {
        return () -> {
            List<? extends Animal> allAnimals = island.getAllAnimals();
            List<AnimalTask> animalTasks = new ArrayList<>();
            for (Animal animal : allAnimals) {
                animalTasks.add(new AnimalTask(animal));
            }
            animalTasks.forEach(lifeCycleExecutorService::submit);
        };
    }
}
