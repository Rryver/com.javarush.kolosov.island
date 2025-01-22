package ru.javarush.kolosov.island.entities.organisms;


import lombok.Getter;
import lombok.Setter;
import ru.javarush.kolosov.island.Application;
import ru.javarush.kolosov.island.entities.island.Cell;
import ru.javarush.kolosov.island.repository.AnimalCreator;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
abstract public class Animal extends Organism {
    /**
     * Кого может съесть и шансы поймать
     */
    protected Map<Class<? extends Organism>, Integer> eatChances = new HashMap<>();

    /**
     * Скорость перемещения, не более чем, клеток за ход
     */
    protected int speed;

    /**
     * Сколько пищи нужно животному для полного насыщения, килограммы
     */
    protected double needEatToBeFull;

    /**
     * Сегодня съедено, килограммы
     */
    protected double todayEaten;

    /**
     * Дней без еды прожито
     */
    protected int daysWithoutEat;

    public Animal(Cell currentCell, double weight, int speed, double needEatToBeFull, Map<Class<? extends Organism>, Integer> eatChances) {
        super(currentCell, weight);

        this.speed = speed;
        this.needEatToBeFull = needEatToBeFull;
        this.eatChances = eatChances;
    }

    public void move() {
        if (speed <= 0) {
            return;
        }

        Cell cellToMoveIn = getCurrentCell();
        for (int i = 0; i < speed; i++) {
            Cell randomLinkedCell = cellToMoveIn.randomLinkedCell();

            long currentCountOnCell = randomLinkedCell.getOrganismCount(this.getClass());
            int maxCountOnCell = Application.simulation.getSettings().getOrganismParam(this.getClass()).getMaxCountOnCell();

            if (currentCountOnCell < maxCountOnCell) {
                cellToMoveIn = randomLinkedCell;
            }
        }

        if (cellToMoveIn == getCurrentCell()) {
            return;
        }

        getCurrentCell().removeOrganism(this);
        cellToMoveIn.addOrganism(this);
        setCurrentCell(cellToMoveIn);
    }

    public void eat() {
        if (todayEaten >= needEatToBeFull) {
            return;
        }

        synchronized (getCurrentCell().getOrganisms()) {
            List<Organism> organismsToHunt = findOrganismsToHunt();

            for (Organism organismToHunt : organismsToHunt) {
                if (huntedSuccessfully(organismToHunt)) {
                    todayEaten += organismToHunt.getWeight();
                    organismToHunt.die();
                }

                if (todayEaten >= needEatToBeFull) {
                    return;
                }
            }
        }
    }

    private List<Organism> findOrganismsToHunt() {
        return getCurrentCell().getOrganisms().stream()
                .filter(e -> this.eatChances.containsKey(e.getClass()))
                .sorted(Comparator.comparingDouble(Organism::getWeight))
                .toList();
    }

    private boolean huntedSuccessfully(Organism organism) {
        return ThreadLocalRandom.current().nextInt(0, 100) <= eatChances.get(organism.getClass());
    }

    @Override
    public void reproduce() {
        if (todayEaten < needEatToBeFull) {
            return;
        }

        synchronized (getCurrentCell().getOrganisms()) {
            long sameTypeCountOnCell = getCurrentCell().getOrganismCount(this.getClass());
            int maxCountOnCell = Application.simulation.getSettings().getOrganismParam(this.getClass()).getMaxCountOnCell();

            if (sameTypeCountOnCell < 2 || sameTypeCountOnCell >= maxCountOnCell) {
                return;
            }

            AnimalCreator.create(this.getClass(), getCurrentCell());
        }
    }

    public void startDay() {
        if (getDaysWithoutEat() >= Application.simulation.getSettings().getDaysWithoutEatToDie()) {
            die();
            return;
        }

        todayEaten = 0;
    }

    public void endDay() {
        if (todayEaten == 0) {
            daysWithoutEat++;
        } else {
            daysWithoutEat = 0;
        }
    }
}
