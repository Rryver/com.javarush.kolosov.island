package ru.javarush.kolosov.island.services.printInfo.methods;

import ru.javarush.kolosov.island.Application;
import ru.javarush.kolosov.island.config.SimulationSettings;
import ru.javarush.kolosov.island.entities.island.Cell;
import ru.javarush.kolosov.island.entities.island.Island;
import ru.javarush.kolosov.island.entities.organisms.Organism;

import java.util.Map;

public class PrintToConsoleEveryCellMethod implements PrintInfoMethod {
    @Override
    public void run() {
        Island island = Application.simulation.getIsland();
        Cell[][] cells = island.getCells();

        for (int y = 0; y < island.getHeight(); y++) {
            System.out.print("| ");
            for (int x = 0; x < island.getWidth(); x++) {
                Cell cell = cells[y][x];
                Map<Organism, Integer> organisms = cell.getOrganismsCount();
                for (Organism organism : organisms.keySet()) {
                    System.out.print(organism.getIcon().getValue() + ":" + organisms.get(organism));
                }

                int diffAvailableAndExistsOrganisms = SimulationSettings.availableOrganismClazz.size() - organisms.size();
                if (diffAvailableAndExistsOrganisms > 0) {
                    System.out.print("    ".repeat(diffAvailableAndExistsOrganisms));
                }

                System.out.print(" | ");
            }
            System.out.println("\n-----------------------------------------");
        }

        System.out.println("\n");
    }
}
