package ru.javarush.kolosov.island.config;

import lombok.Getter;
import lombok.Setter;
import ru.javarush.kolosov.island.entities.organisms.Organism;
import ru.javarush.kolosov.island.entities.organisms.Plant;
import ru.javarush.kolosov.island.entities.organisms.herbivores.Rabbit;
import ru.javarush.kolosov.island.entities.organisms.predators.Wolf;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
public class SimulationSettings {
    private int islandWidth = 3;
    private int islandHeight = 3;
    private int daysWithoutEatToDie = 3;

    /**
     * Кол-во дней, через которое необходимо остановить симуляцию
     * Если 0, то симуляция бесконечна (до достижения других условий окончания)
     */
    private int stopSimulationAfterDays = 0;

    private Set<Class<? extends Organism>> availableOrganismClazz = new HashSet<>();
    private Map<Class<? extends Organism>, OrganismIcon> organismsIcon = new HashMap<>();

    public SimulationSettings() {
        initAvailableAnimalsClazz();
//        initTotalCountAtStart();
        initOrganismIcons();
    }

    private void initAvailableAnimalsClazz() {
        availableOrganismClazz.add(Plant.class);
        availableOrganismClazz.add(Wolf.class);
        availableOrganismClazz.add(Rabbit.class);
    }

    private void initOrganismIcons() {
        organismsIcon.put(Plant.class, OrganismIcon.PLANT);
        organismsIcon.put(Wolf.class, OrganismIcon.WOLF);
        organismsIcon.put(Rabbit.class, OrganismIcon.RABBIT);
    }

    public OrganismIcon getOrganismIcon(Class<? extends Organism> clazz) {
        return organismsIcon.get(clazz);
    }
}
