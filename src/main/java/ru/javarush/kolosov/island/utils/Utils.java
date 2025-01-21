package ru.javarush.kolosov.island.utils;

import java.util.List;

public class Utils {
    public static <T> void addIfNotNull(List<T> list, T element) {
        if (element != null) {
            list.add(element);
        }
    }
}
