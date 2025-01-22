package ru.javarush.kolosov.island.services.lifeCycle;

import ru.javarush.kolosov.island.entities.organisms.plants.Plant;

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
