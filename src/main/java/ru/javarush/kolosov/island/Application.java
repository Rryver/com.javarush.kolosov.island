package ru.javarush.kolosov.island;


import ru.javarush.kolosov.island.services.Simulation;

import java.io.IOException;

public class Application {
    public static Simulation simulation;

    public static void main(String[] args) throws IOException {
        simulation = new Simulation();
        simulation.start();
    }
}