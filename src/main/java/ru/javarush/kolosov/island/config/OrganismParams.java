package ru.javarush.kolosov.island.config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.javarush.kolosov.island.entities.organisms.Organism;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrganismParams {
    private int weight;
    private int maxCountOnCell;
    private double needEatToBeFull;
    private int speed;
    private OrganismIcon icon;

    @JsonIgnore
    private Map<Class<? extends Organism>, Integer> eatChances;
}
