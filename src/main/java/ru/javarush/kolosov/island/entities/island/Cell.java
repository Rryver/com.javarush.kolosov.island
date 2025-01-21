package ru.javarush.kolosov.island.entities.island;

import lombok.Getter;
import lombok.Setter;
import ru.javarush.kolosov.island.Application;
import ru.javarush.kolosov.island.entities.organisms.Organism;
import ru.javarush.kolosov.island.utils.Utils;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
public class Cell {
    private final int x;
    private final int y;

    private final List<Organism> organisms = Collections.synchronizedList(new ArrayList<>());

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "organisms=" + organisms.toString() +
                '}';
    }

//    public Map<Class<? extends Organism>, Long> getOrganismsCountInCell() {
//        Map<Class<? extends Organism>, Long> result = SimulationSettings.availableOrganismClazz.stream().collect(Collectors.toMap(e -> e, e -> 0L));
//
//        Map<Class<? extends Organism>, Long> result = new HashMap<>();
//
//        for (Class<? extends Organism> availableOrganismClazz : SimulationSettings.availableOrganismClazz) {
//            result.put(availableOrganismClazz, 0L);
//        }
//
//        Map<Organism, Long> collect = organisms.stream()
//                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
//
//        collect.forEach((key, value) -> result.put(key.getClass(), value));
//    }

    public Map<Organism, Integer> getOrganismsCount() {
        Map<Organism, Integer> result = new HashMap<>();

//        Map<Organism, Long> result = organisms.stream()
//                .collect(Collectors.groupingByConcurrent(Function.identity(), Collectors.counting()));

//        for (Organism organism : organisms) {
//            if (!result.containsKey(organism)) {
//                result.put(organism, 1);
//            } else {
//                result.put(organism, result.get(organism) + 1);
//            }
//        }

        for (Organism organism : organisms) {
            boolean found = false;
            for (Organism resultOrganism : result.keySet()) {
                if (organism.getClass() == resultOrganism.getClass()) {
                    result.put(resultOrganism, result.get(resultOrganism) + 1);
                    found = true;
                    break;
                }
            }
            if (!found) {
                result.put(organism, 1);
            }
        }

        return result;
    }

    public long getOrganismCount(Class<? extends Organism> neededOrganism) {
        return organisms.stream()
                .filter((organism) -> organism.getClass() == neededOrganism)
                .count();
    }

    public Cell getCellLeft() {
        return Application.simulation.getIsland().getCell(x - 1, y);
    }

    public Cell getCellRight() {
        return Application.simulation.getIsland().getCell(x + 1, y);
    }

    public Cell getCellUp() {
        return Application.simulation.getIsland().getCell(x, y - 1);
    }

    public Cell getCellDown() {
        return Application.simulation.getIsland().getCell(x, y + 1);
    }

    public Cell randomLinkedCell() {
        List<Cell> availableCells = new ArrayList<>();

        Utils.addIfNotNull(availableCells, getCellLeft());
        Utils.addIfNotNull(availableCells, getCellRight());
        Utils.addIfNotNull(availableCells, getCellUp());
        Utils.addIfNotNull(availableCells, getCellDown());

        int randomCellIndex = ThreadLocalRandom.current().nextInt(0, availableCells.size());
        return availableCells.get(randomCellIndex);
    }

    public void addOrganism(Organism organism) {
//        if (organisms.contains(organism)) {
//            return;
//        }

        organisms.add(organism);
    }

    public void removeOrganism(Organism organism) {
//        if (!organisms.contains(organism)) {
//            return;
//        }

        organisms.remove(organism);
    }
}
