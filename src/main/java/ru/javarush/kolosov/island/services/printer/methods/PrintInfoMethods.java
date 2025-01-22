package ru.javarush.kolosov.island.services.printer.methods;

import lombok.Getter;
import ru.javarush.kolosov.island.config.ConfigNameOrganismsClass;
import ru.javarush.kolosov.island.entities.organisms.Organism;

import java.util.Arrays;

@Getter
public enum PrintInfoMethods {
    ONLY_COUNT_ORGANISMS("OnlyCountOrganisms"),
    EVERY_CELL("EveryCell");

    private final String settingsName;

    PrintInfoMethods(String settingsName) {
        this.settingsName = settingsName;
    }

    public static PrintInfoMethods getByName(String name) {
        return Arrays.stream(PrintInfoMethods.values()).filter(value -> value.getSettingsName().equals(name)).findFirst().get();
    }
}
