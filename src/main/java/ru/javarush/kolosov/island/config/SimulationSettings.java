package ru.javarush.kolosov.island.config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import ru.javarush.kolosov.island.entities.organisms.Organism;
import ru.javarush.kolosov.island.entities.organisms.plants.Plant;
import ru.javarush.kolosov.island.entities.organisms.herbivores.*;
import ru.javarush.kolosov.island.entities.organisms.predators.Bear;
import ru.javarush.kolosov.island.entities.organisms.predators.Boa;
import ru.javarush.kolosov.island.entities.organisms.predators.Fox;
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

    @JsonIgnore
    private Set<Class<? extends Organism>> availableOrganismClazz = new HashSet<>();
    @JsonIgnore
    private Map<Class<? extends Organism>, OrganismIcon> organismsIcon = new HashMap<>();
    @JsonIgnore
    private Map<Class<? extends Organism>, Integer> maxOrganismsCountOnCell = new HashMap<>();

    @JsonDeserialize(using = SettingsDeserializer.class)
    private Map<Class<? extends Organism>, OrganismParams> organismParams = new HashMap<>();


    public SimulationSettings() {
        initAvailableAnimalsClazz();
//        initTotalCountAtStart();
        initOrganismIcons();
        initMaxOrganismsCountOnCell();
    }

    private void initAvailableAnimalsClazz() {
        availableOrganismClazz.add(Wolf.class);
        availableOrganismClazz.add(Boa.class);
        availableOrganismClazz.add(Fox.class);
        availableOrganismClazz.add(Bear.class);

        availableOrganismClazz.add(Horse.class);
        availableOrganismClazz.add(Deer.class);
        availableOrganismClazz.add(Rabbit.class);
        availableOrganismClazz.add(Mouse.class);
        availableOrganismClazz.add(Goat.class);
        availableOrganismClazz.add(Sheep.class);
        availableOrganismClazz.add(Boar.class);
        availableOrganismClazz.add(Buffalo.class);
        availableOrganismClazz.add(Duck.class);
        availableOrganismClazz.add(Caterpillar.class);

        availableOrganismClazz.add(Plant.class);
    }

    private void initOrganismIcons() {
        organismsIcon.put(Wolf.class, OrganismIcon.WOLF);
        organismsIcon.put(Boa.class, OrganismIcon.BOA);
        organismsIcon.put(Fox.class, OrganismIcon.FOX);
        organismsIcon.put(Bear.class, OrganismIcon.BEAR);

        organismsIcon.put(Horse.class, OrganismIcon.HORSE);
        organismsIcon.put(Deer.class, OrganismIcon.DEER);
        organismsIcon.put(Rabbit.class, OrganismIcon.RABBIT);
        organismsIcon.put(Mouse.class, OrganismIcon.MOUSE);
        organismsIcon.put(Goat.class, OrganismIcon.GOAT);
        organismsIcon.put(Sheep.class, OrganismIcon.SHEEP);
        organismsIcon.put(Boar.class, OrganismIcon.BOAR);
        organismsIcon.put(Buffalo.class, OrganismIcon.BUFFALO);
        organismsIcon.put(Duck.class, OrganismIcon.DUCK);
        organismsIcon.put(Caterpillar.class, OrganismIcon.CATERPILLAR);

        organismsIcon.put(Plant.class, OrganismIcon.PLANT);
    }

    private void initMaxOrganismsCountOnCell() {
        maxOrganismsCountOnCell.put(Wolf.class, 30);
        maxOrganismsCountOnCell.put(Boa.class, 30);
        maxOrganismsCountOnCell.put(Fox.class, 30);
        maxOrganismsCountOnCell.put(Bear.class, 5);

        maxOrganismsCountOnCell.put(Horse.class, 20);
        maxOrganismsCountOnCell.put(Deer.class, 20);
        maxOrganismsCountOnCell.put(Rabbit.class, 150);
        maxOrganismsCountOnCell.put(Mouse.class, 500);
        maxOrganismsCountOnCell.put(Goat.class, 140);
        maxOrganismsCountOnCell.put(Sheep.class, 140);
        maxOrganismsCountOnCell.put(Boar.class, 50);
        maxOrganismsCountOnCell.put(Buffalo.class, 10);
        maxOrganismsCountOnCell.put(Duck.class, 200);
        maxOrganismsCountOnCell.put(Caterpillar.class, 1000);

        maxOrganismsCountOnCell.put(Plant.class, 200);
    }

    public OrganismIcon getOrganismIcon(Class<? extends Organism> clazz) {
        return organismsIcon.get(clazz);
    }

    public Integer getOrganismMaxCountOnCell(Class<? extends Organism> clazz) {
        return maxOrganismsCountOnCell.get(clazz);
    }
}
