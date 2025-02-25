package ru.javarush.kolosov.island.services.lifeCycle;

import ru.javarush.kolosov.island.entities.organisms.Animal;

public class AnimalTask implements Runnable {

    private final Animal animal;

    public AnimalTask(Animal animal) {
        this.animal = animal;
    }

    @Override
    public void run() {
        animal.startDay();

        if (animal.isAlive()) {
            animal.move();
            animal.eat();
            animal.reproduce();
        }

        animal.endDay();
    }

}
