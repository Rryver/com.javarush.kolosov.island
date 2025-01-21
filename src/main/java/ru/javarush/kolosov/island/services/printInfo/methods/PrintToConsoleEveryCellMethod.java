package ru.javarush.kolosov.island.services.printInfo.methods;

import ru.javarush.kolosov.island.Application;
import ru.javarush.kolosov.island.entities.island.Cell;
import ru.javarush.kolosov.island.entities.island.Island;
import ru.javarush.kolosov.island.entities.organisms.Organism;
import ru.javarush.kolosov.island.services.Simulation;

import java.util.Map;

public class PrintToConsoleEveryCellMethod implements PrintInfoMethod {
    @Override
    public void run() {
        Simulation simulation = Application.simulation;
        Island island = simulation.getIsland();
        Cell[][] cells = island.getCells();

        for (int y = 0; y < island.getHeight(); y++) {
            System.out.print("| ");
            for (int x = 0; x < island.getWidth(); x++) {
                Cell cell = cells[y][x];
                Map<Organism, Integer> organisms = cell.getOrganismsCount();
                for (Organism organism : organisms.keySet()) {
                    System.out.print(simulation.getSettings().getOrganismIcon(organism.getClass()) + ":" + organisms.get(organism));
                }

                int diffAvailableAndExistsOrganisms = simulation.getSettings().getAvailableOrganismClazz().size() - organisms.size();
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
