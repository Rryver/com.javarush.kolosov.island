package ru.javarush.kolosov.island.entities.organisms;

import lombok.Getter;

@Getter
public enum OrganismIcon {
    PLANT("\uD83C\uDF31"),

    WOLF("\uD83D\uDC3A"),
    RABBIT("\uD83D\uDC07");

    final String value;

    OrganismIcon(String icon) {
        this.value = icon;
    }
}
