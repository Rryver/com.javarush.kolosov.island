package ru.javarush.kolosov.island.config;

import lombok.Getter;

@Getter
@Deprecated
public enum OrganismIcon {
    WOLF("\uD83D\uDC3A"),
    BOA("\uD83D\uDC0D"),
    FOX("\uD83E\uDD8A"),
    BEAR("\uD83D\uDC3B"),
    EAGLE("\uD83E\uDD85"),

    HORSE("\uD83D\uDC0E"),
    DEER("\uD83E\uDD8C"),
    RABBIT("\uD83D\uDC07"),
    MOUSE("\uD83D\uDC01"),
    GOAT("\uD83D\uDC10"),
    SHEEP("\uD83D\uDC11"),
    BOAR("\uD83D\uDC17"),
    BUFFALO("\uD83D\uDC03"),
    DUCK("\uD83E\uDD86"),
    CATERPILLAR("\uD83D\uDC1B"),

    PLANT("\uD83C\uDF31");

    final String value;

    OrganismIcon(String icon) {
        this.value = icon;
    }
    }
