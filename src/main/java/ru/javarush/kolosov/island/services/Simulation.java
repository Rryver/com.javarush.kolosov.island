package ru.javarush.kolosov.island.services;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import ru.javarush.kolosov.island.config.SimulationSettings;
import ru.javarush.kolosov.island.entities.island.Island;
import ru.javarush.kolosov.island.entities.organisms.Animal;
import ru.javarush.kolosov.island.entities.organisms.Plant;
import ru.javarush.kolosov.island.services.printInfo.methods.PrintToConsoleOnlyCountOrganismsMethod;

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
    private int daysPastUntilStopSimulation;

    //    private PrintInfoService printInfoService;
    private ScheduledExecutorService printInfoService;
    private ExecutorService animalExecutorService;
    private ExecutorService plantExecutorService;

    private boolean inProgress = false;
    private final int sleepBetweenDaysMillis = 1000;

    public Simulation() {
        this.settings = new SimulationSettings();
    }

    public void start() {
        createInland();
        inProgress = true;
        daysPastUntilStopSimulation = 0;
        startPrintInfoService();

        animalExecutorService = Executors.newFixedThreadPool(2);
        plantExecutorService = Executors.newFixedThreadPool(2);

        while (!isStopConditionReached()) {
            try {
                Thread.sleep(sleepBetweenDaysMillis);
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

            daysPastUntilStopSimulation++;
        }

        stop();
    }

    public void stop() {
        printInfoService.shutdown();
        animalExecutorService.shutdown();
        plantExecutorService.shutdown();
        inProgress = false;
    }

    private void startPrintInfoService() {
        printInfoService = Executors.newSingleThreadScheduledExecutor();
//        printInfoService.scheduleAtFixedRate(new PrintToConsoleEveryCellMethod(), sleepBetweenDaysMillis, sleepBetweenDaysMillis, TimeUnit.MILLISECONDS);
        printInfoService.scheduleAtFixedRate(new PrintToConsoleOnlyCountOrganismsMethod(), sleepBetweenDaysMillis, sleepBetweenDaysMillis, TimeUnit.MILLISECONDS);
    }

    private void createInland() {
        island = new Island(settings.getAvailableOrganismClazz());
    }

    private boolean isStopConditionReached() {
        if (island.getAllAnimals().isEmpty()) {
            return true;
        }

        if (settings.getStopSimulationAfterDays() > 0 && daysPastUntilStopSimulation >= settings.getStopSimulationAfterDays()) {
            return true;
        }

        return false;
    }
}
