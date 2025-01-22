package ru.javarush.kolosov.island.services.printer.methods;

import ru.javarush.kolosov.island.Application;
import ru.javarush.kolosov.island.entities.island.Cell;
import ru.javarush.kolosov.island.entities.island.Island;
import ru.javarush.kolosov.island.entities.organisms.Organism;
import ru.javarush.kolosov.island.services.Simulation;

import java.util.Map;

public class PrintToConsoleEveryCell implements PrintInfoMethod {
    @Override
    public void run() {
        Simulation simulation = Application.simulation;
        Island island = simulation.getIsland();
        Cell[][] cells = island.getCells();

        for (int y = 0; y < island.getHeight(); y++) {
            System.out.print("| ");
            int rowLength = 0;
            for (int x = 0; x < island.getWidth(); x++) {
                Cell cell = cells[y][x];
                Map<Organism, Integer> organisms = cell.getOrganismsCount();
                for (Organism organism : organisms.keySet()) {
                    String s = simulation.getSettings().getOrganismParam(organism.getClass()).getIcon() + ":" + organisms.get(organism);
                    System.out.print(s);
                    rowLength += s.length();
                }

                int diffAvailableAndExistsOrganisms = simulation.getSettings().getAvailableOrganismClazz().size() - organisms.size();
                if (diffAvailableAndExistsOrganisms > 0) {
                    String repeat = "    ".repeat(diffAvailableAndExistsOrganisms);
                    System.out.print(repeat);

                    rowLength += repeat.length();
                }

                System.out.print(" | ");
            }
            System.out.println("\n" + "-".repeat(rowLength + (rowLength / 100 * 10)));
        }

        System.out.println("\n");
    }
}
