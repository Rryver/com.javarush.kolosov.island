package ru.javarush.kolosov.island.entities.organisms;


import lombok.Getter;
import lombok.Setter;
import ru.javarush.kolosov.island.Application;
import ru.javarush.kolosov.island.entities.island.Cell;
import ru.javarush.kolosov.island.repository.AnimalFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
abstract public class Animal extends Organism {
    //Кого может съесть и шансы поймать
    protected Map<Class<? extends Organism>, Integer> eatChances = new HashMap<>();

    //Скорость перемещения, не более чем, клеток за ход
    protected int speed;

    //Сколько килограммов пищи нужно животному для полного насыщения
    protected double needEatToBeFull;
    protected double todayEaten;

    protected int daysWithoutEat;

    public Animal(Cell currentCell) {
        super(currentCell);
    }

    public void move() {
        if (speed <= 0) {
            return;
        }

        Cell cellToMoveIn = getCurrentCell();
        for (int i = 0; i < speed; i++) {
            Cell randomLinkedCell = cellToMoveIn.randomLinkedCell();
            long organismCount = randomLinkedCell.getOrganismCount(this.getClass());
            if (organismCount < this.getMaxCountOnCell()) {
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
                if (huntedSuccessful(organismToHunt)) {
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
                .sorted((o1, o2) -> Float.compare(o1.getWeight(), o2.getWeight()))
                .toList();
    }

    private boolean huntedSuccessful(Organism organism) {
        return ThreadLocalRandom.current().nextInt(0, 100) <= eatChances.get(organism.getClass());
    }

    @Override
    public void reproduce() {
        if (todayEaten < needEatToBeFull) {
            return;
        }

        long sameOrganismsOnCellCount = getCurrentCell().getOrganismCount(this.getClass());
        if (sameOrganismsOnCellCount < 2 || sameOrganismsOnCellCount >= this.getMaxCountOnCell()) {
            return;
        }

        AnimalFactory.create(this.getClass(), getCurrentCell());
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
        }
    }
}
