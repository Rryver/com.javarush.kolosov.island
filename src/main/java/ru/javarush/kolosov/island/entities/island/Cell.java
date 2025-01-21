package ru.javarush.kolosov.island.entities.island;

import lombok.Getter;
import lombok.Setter;
import ru.javarush.kolosov.island.entities.organisms.Organism;
import ru.javarush.kolosov.island.entities.organisms.Plant;
import ru.javarush.kolosov.island.entities.organisms.herbivores.Rabbit;
import ru.javarush.kolosov.island.entities.organisms.predators.Wolf;
import ru.javarush.kolosov.island.utils.Utils;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
public class Cell {
    private Island island;
    private final int x;
    private final int y;

    private final List<Organism> organisms = Collections.synchronizedList(new ArrayList<>());

    private Map<Class<? extends Organism>, Integer> maxOrganismsCount = new HashMap<>();

    public Cell(int x, int y, Island island) {
        this.x = x;
        this.y = y;
        this.island = island;

        maxOrganismsCount.put(Plant.class, 50);
        maxOrganismsCount.put(Wolf.class, 30);
        maxOrganismsCount.put(Rabbit.class, 200);
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

        synchronized (organisms) {
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
        }

        return result;
    }

    public long getOrganismCount(Class<? extends Organism> neededOrganism) {
        synchronized (organisms) {
            return organisms.stream()
                    .filter((organism) -> organism.getClass() == neededOrganism)
                    .count();
        }
    }

    public Cell getCellLeft() {
        return island.getCell(x - 1, y);
    }

    public Cell getCellRight() {
        return island.getCell(x + 1, y);
    }

    public Cell getCellUp() {
        return island.getCell(x, y - 1);
    }

    public Cell getCellDown() {
        return island.getCell(x, y + 1);
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
        organisms.add(organism);
    }

    public void removeOrganism(Organism organism) {
        organisms.remove(organism);
    }

    public Integer getMaxOrganismCount(Class<? extends Organism> clazz) {
        return maxOrganismsCount.get(clazz);
    }
}
