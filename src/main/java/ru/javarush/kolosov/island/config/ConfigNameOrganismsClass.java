package ru.javarush.kolosov.island.config;

import lombok.Getter;
import ru.javarush.kolosov.island.entities.organisms.Organism;
import ru.javarush.kolosov.island.entities.organisms.herbivores.Rabbit;
import ru.javarush.kolosov.island.entities.organisms.predators.Wolf;

import java.util.Arrays;

@Getter
public enum ConfigNameOrganismsClass {
    WOLF("Wolf", Wolf.class),
    RABBIT("Rabbit", Rabbit.class);

    private final String settingsName;
    private final Class<? extends Organism> clazz;

    ConfigNameOrganismsClass(String settingsName, Class<? extends Organism> clazz) {
        this.settingsName = settingsName;
        this.clazz = clazz;
    }

    public static Class<? extends Organism> getClassByName(String name) {
        return Arrays.stream(ConfigNameOrganismsClass.values()).filter(value -> value.getSettingsName().equals(name)).findFirst().get().getClazz();
    }
}
