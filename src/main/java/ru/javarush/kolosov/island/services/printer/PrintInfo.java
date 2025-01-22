package ru.javarush.kolosov.island.services.printer;


import lombok.NoArgsConstructor;
import ru.javarush.kolosov.island.services.printer.methods.PrintInfoMethod;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


@NoArgsConstructor
public class PrintInfo {
    private PrintInfoMethod printInfoMethod;
    private int printIntervalMillis;

    private ScheduledExecutorService executor;

    public PrintInfo(PrintInfoMethod printInfoMethod, int printIntervalMillis) {
        this.printInfoMethod = printInfoMethod;
        this.printIntervalMillis = printIntervalMillis;
    }

    public void start() {
        executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(printInfoMethod, 0, printIntervalMillis, TimeUnit.MILLISECONDS);
    }

    public void stop() {
        executor.shutdown();
    }
}
