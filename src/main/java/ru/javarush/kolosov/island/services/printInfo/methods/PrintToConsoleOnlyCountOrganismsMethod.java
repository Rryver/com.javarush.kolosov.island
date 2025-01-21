package ru.javarush.kolosov.island.services.printInfo.methods;

import ru.javarush.kolosov.island.Application;
import ru.javarush.kolosov.island.entities.island.Cell;
import ru.javarush.kolosov.island.entities.island.Island;
import ru.javarush.kolosov.island.entities.organisms.Organism;
import ru.javarush.kolosov.island.services.Simulation;

import java.util.Map;
import java.util.stream.Collectors;

public class PrintToConsoleOnlyCountOrganismsMethod implements PrintInfoMethod {
    @Override
    public void run() {
        Simulation simulation = Application.simulation;
        Island island = simulation.getIsland();
        Cell[][] cells = island.getCells();

        Map<Class<? extends Organism>, Integer> organismsTotal = simulation.getSettings().getAvailableOrganismClazz().stream().collect(Collectors.toMap(e -> e, e -> 0));
        for (int y = 0; y < island.getHeight(); y++) {
            for (int x = 0; x < island.getWidth(); x++) {
                Cell cell = cells[y][x];
                Map<Organism, Integer> organisms = cell.getOrganismsCount();
                for (Organism organism : organisms.keySet()) {
                    organismsTotal.put(organism.getClass(), organismsTotal.get(organism.getClass()) + organisms.get(organism));
                }
            }
        }

        for (Class<? extends Organism> clazz : organismsTotal.keySet()) {
            System.out.print(simulation.getSettings().getOrganismIcon(clazz).getValue() + ":" + organismsTotal.get(clazz) + "\t");
        }

        System.out.println("\n");
    }
}
