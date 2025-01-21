package ru.javarush.kolosov.island;


import com.fasterxml.jackson.databind.ObjectMapper;
import ru.javarush.kolosov.island.config.SimulationSettings;
import ru.javarush.kolosov.island.services.Simulation;

import java.io.File;
import java.io.IOException;

public class Application {
    public static Simulation simulation;

    public static void main(String[] args) throws IOException {
//        simulation = new Simulation();
//        simulation.start();

        File settingsFile = new File("src/main/resources/settings.json");
        ObjectMapper objectMapper = new ObjectMapper();
        SimulationSettings simulationSettings = objectMapper.readValue(settingsFile, SimulationSettings.class);
        return;
    }
}