package ru.javarush.kolosov.island.services;

import ru.javarush.kolosov.island.entities.organisms.Plant;

public class PlantTask implements Runnable {
    Plant plant;

    public PlantTask(Plant plant) {
        this.plant = plant;
    }


    @Override
    public void run() {
        plant.reproduce();
    }
}
