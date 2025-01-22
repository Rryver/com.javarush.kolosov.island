package ru.javarush.kolosov.island.services;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import ru.javarush.kolosov.island.config.SimulationSettings;
import ru.javarush.kolosov.island.entities.island.Island;
import ru.javarush.kolosov.island.services.lifeCycle.AnimalsLifeCycle;
import ru.javarush.kolosov.island.services.lifeCycle.LifeCycle;
import ru.javarush.kolosov.island.services.lifeCycle.PlantsLifeCycle;
import ru.javarush.kolosov.island.services.printer.PrintInfo;
import ru.javarush.kolosov.island.services.printer.methods.PrintToConsoleEveryCell;

import java.io.IOException;

@Getter
public class Simulation {
    @Setter
    @NonNull
    private SimulationSettings settings;
    private Island island;
    private int daysPastUntilStopSimulation;

    private PrintInfo printInfoService;
    private LifeCycle plantsLifeCycleService;
    private LifeCycle animalsLifeCycleService;

    private boolean inProgress = false;

    public Simulation() throws IOException {
        this.settings = SimulationSettings.create();
    }

    public void start() {
        createInland();
        inProgress = true;
        daysPastUntilStopSimulation = 0;
        startPrintInfoService();
        startLifeCycleServices();

        do {
            try {
                Thread.sleep(settings.getLifeCycleIntervalMillis());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            daysPastUntilStopSimulation++;
        } while (!isStopConditionReached());

        stop();
    }

    public void stop() {
        printInfoService.stop();
        plantsLifeCycleService.stop();
        animalsLifeCycleService.stop();
        inProgress = false;
    }

    private void startLifeCycleServices() {
        plantsLifeCycleService = new PlantsLifeCycle(island, settings.getPlantsGrowsIntervalMillis());
        plantsLifeCycleService.start();

        animalsLifeCycleService = new AnimalsLifeCycle(island, settings.getLifeCycleIntervalMillis());
        animalsLifeCycleService.start();
    }

    private void startPrintInfoService() {
        printInfoService = new PrintInfo(new PrintToConsoleEveryCell(), 1000);
        printInfoService.start();
    }

    private void createInland() {
        island = new Island(settings);
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
