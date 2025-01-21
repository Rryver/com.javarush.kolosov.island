package ru.javarush.kolosov.island;


import ru.javarush.kolosov.island.services.Simulation;

public class Application {
    public static Simulation simulation;

    public static void main(String[] args) {
        simulation = new Simulation();
        simulation.start();
    }
}