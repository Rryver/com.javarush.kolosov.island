package ru.javarush.kolosov.island.config;

import lombok.Getter;
import ru.javarush.kolosov.island.entities.organisms.Organism;
import ru.javarush.kolosov.island.entities.organisms.herbivores.*;
import ru.javarush.kolosov.island.entities.organisms.plants.Plant;
import ru.javarush.kolosov.island.entities.organisms.predators.*;

import java.util.Arrays;

@Getter
public enum ConfigNameOrganismsClass {
    WOLF("Wolf", Wolf.class),
    BOA("Boa", Boa.class),
    FOX("Fox", Fox.class),
    BEAR("Bear", Bear.class),
    EAGLE("Eagle", Eagle.class),

    HORSE("Horse", Horse.class),
    DEER("Deer", Deer.class),
    RABBIT("Rabbit", Rabbit.class),
    MOUSE("Mouse", Mouse.class),
    GOAT("Goat", Goat.class),
    SHEEP("Sheep", Sheep.class),
    BOAR("Boar", Boar.class),
    BUFFALO("Buffalo", Buffalo.class),
    DUCK("Duck", Duck.class),
    CATERPILLAR("Caterpillar", Caterpillar.class),

    PLANT("Plant", Plant.class);

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
