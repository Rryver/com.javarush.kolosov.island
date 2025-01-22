package ru.javarush.kolosov.island.services.lifeCycle;

import ru.javarush.kolosov.island.entities.island.Island;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class LifeCycle {
    protected final Island island;

    /**
     * Генерирует задачи, которые будет выполнять executorService
     */
    protected ScheduledExecutorService lifeCycleTaskCreator;

    /**
     * Выполняет задачи, полученные для выполнения по расписанию
     */
    protected ExecutorService lifeCycleExecutorService;

    protected final int lifeCycleIntervalMillis;

    public LifeCycle(Island island, int lifeCycleIntervalMillis) {
        this.island = island;
        this.lifeCycleIntervalMillis = lifeCycleIntervalMillis;
    }

    public void start() {
        lifeCycleTaskCreator = Executors.newSingleThreadScheduledExecutor();
        lifeCycleExecutorService = Executors.newFixedThreadPool(4);

        lifeCycleTaskCreator.scheduleAtFixedRate(getTaskCreatingAlgorithm(), 0, lifeCycleIntervalMillis, TimeUnit.MILLISECONDS);
    }

    public void stop() {
        lifeCycleExecutorService.shutdown();
        lifeCycleTaskCreator.shutdown();
    }

    abstract protected Runnable getTaskCreatingAlgorithm();
}
