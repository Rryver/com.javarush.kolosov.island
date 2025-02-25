package ru.javarush.kolosov.island.services.printer;


import lombok.NoArgsConstructor;
import ru.javarush.kolosov.island.services.printer.methods.PrintInfoMethod;
import ru.javarush.kolosov.island.services.printer.methods.PrintInfoMethods;
import ru.javarush.kolosov.island.services.printer.methods.PrintToConsoleEveryCell;
import ru.javarush.kolosov.island.services.printer.methods.PrintToConsoleOnlyCountOrganisms;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


@NoArgsConstructor
public class PrintInfo {
    private PrintInfoMethod printInfoMethod;
    private int printIntervalMillis;

    private ScheduledExecutorService executor;

    public PrintInfo(PrintInfoMethods printInfoMethod, int printIntervalMillis) {
        this.printInfoMethod = getMethodByType(printInfoMethod);
        this.printIntervalMillis = printIntervalMillis;
    }

    public void start() {
        executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(printInfoMethod, 0, printIntervalMillis, TimeUnit.MILLISECONDS);
    }

    public void stop() {
        executor.shutdown();
    }

    private PrintInfoMethod getMethodByType(PrintInfoMethods printInfoMethod) {
        return switch (printInfoMethod) {
            case ONLY_COUNT_ORGANISMS -> new PrintToConsoleOnlyCountOrganisms();
            case EVERY_CELL -> new PrintToConsoleEveryCell();
        };
    }
}
