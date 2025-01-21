package ru.javarush.kolosov.island.services;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import ru.javarush.kolosov.island.config.SimulationSettings;
import ru.javarush.kolosov.island.entities.island.Island;
import ru.javarush.kolosov.island.entities.organisms.Animal;
import ru.javarush.kolosov.island.entities.organisms.Plant;
import ru.javarush.kolosov.island.services.printInfo.methods.PrintToConsoleEveryCellMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Getter
public class Simulation {
    @Setter
    @NonNull
    private SimulationSettings settings;
    private Island island;

    //    private PrintInfoService printInfoService;
    private ScheduledExecutorService printInfoService;
    private ExecutorService animalExecutorService;
    private ExecutorService plantExecutorService;

    private boolean inProgress = false;

    public Simulation() {
        this.settings = new SimulationSettings();
    }

    public Simulation(SimulationSettings simulationSettings) {
        this.settings = simulationSettings;
    }

    public void start() {
        createInland();
        inProgress = true;
        startPrintInfoService();

        animalExecutorService = Executors.newFixedThreadPool(2);
        plantExecutorService = Executors.newFixedThreadPool(2);

        while (true) {
            if (island.getAllAnimals().isEmpty()) {
                stop();
                return;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            List<Plant> allPlants = island.getAllPlants();
            List<PlantTask> plantTasks = new ArrayList<>();
            for (Plant plant : allPlants) {
                plantTasks.add(new PlantTask(plant));
            }
            plantTasks.forEach(plantExecutorService::submit);
//
            List<? extends Animal> allAnimals = island.getAllAnimals();
            List<AnimalTask> animalTasks = new ArrayList<>();
            for (Animal animal : allAnimals) {
                animalTasks.add(new AnimalTask(animal));
            }
            animalTasks.forEach(animalExecutorService::submit);
        }
    }

    public void stop() {
        printInfoService.shutdown();
        animalExecutorService.shutdownNow();
        plantExecutorService.shutdownNow();
        inProgress = false;
    }

    private void startPrintInfoService() {
        printInfoService = Executors.newSingleThreadScheduledExecutor();
        printInfoService.scheduleAtFixedRate(new PrintToConsoleEveryCellMethod(), 0, 1000, TimeUnit.MILLISECONDS);
    }

    private void createInland() {
        island = new Island();
    }
}
