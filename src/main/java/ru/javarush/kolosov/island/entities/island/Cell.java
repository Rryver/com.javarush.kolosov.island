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
    private Island island;
    private final int x;
    private final int y;

    private final List<Organism> organisms = Collections.synchronizedList(new ArrayList<>());

    public Cell(int x, int y, Island island) {
        this.x = x;
        this.y = y;
        this.island = island;
    }

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
        return Application.simulation.getSettings().getOrganismParam(clazz).getMaxCountOnCell();
    }
}
