package ru.javarush.kolosov.island.config;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import ru.javarush.kolosov.island.config.deserializers.EatChancesDeserializer;
import ru.javarush.kolosov.island.entities.organisms.Organism;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganismParams {
    private int weight;
    private int maxCountOnCell;
    private double needEatToBeFull;
    private int speed;
    private String icon;

    @JsonDeserialize(using = EatChancesDeserializer.class)
    private Map<Class<? extends Organism>, Integer> eatChances;
}
