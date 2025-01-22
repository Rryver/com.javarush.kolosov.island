package ru.javarush.kolosov.island.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import ru.javarush.kolosov.island.config.deserializers.organismParamsDeserializer;
import ru.javarush.kolosov.island.entities.organisms.Organism;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
public class SimulationSettings {
    private int islandWidth = 3;
    private int islandHeight = 3;
    private int daysWithoutEatToDie = 3;

    /**
     * Кол-во дней, через которое необходимо остановить симуляцию
     * Если 0, то симуляция бесконечна (до достижения других условий окончания)
     */
    private int stopSimulationAfterDays = 0;

    @JsonDeserialize(using = organismParamsDeserializer.class)
    private Map<Class<? extends Organism>, OrganismParams> organismParams = new HashMap<>();

    private SimulationSettings() {}

    public static SimulationSettings create() throws IOException {
        File settingsFile = new File("src/main/resources/settings.json");
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(settingsFile, SimulationSettings.class);
    }

    public OrganismParams getOrganismParam(Class<? extends Organism> clazz) {
        return organismParams.get(clazz);
    }

    public Set<Class<? extends Organism>> getAvailableOrganismClazz() {
        return organismParams.keySet();
    }
}
