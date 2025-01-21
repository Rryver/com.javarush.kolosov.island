package ru.javarush.kolosov.island.services;

import ru.javarush.kolosov.island.Application;
import ru.javarush.kolosov.island.entities.organisms.Animal;

public class AnimalTask implements Runnable {

    private final Animal animal;

    public AnimalTask(Animal animal) {
        this.animal = animal;
    }

    @Override
    public void run() {
        animal.startDay();

        animal.move();
        animal.eat();
        animal.reproduce();

        animal.endDay();
    }

}
