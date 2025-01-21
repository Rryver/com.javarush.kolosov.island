package ru.javarush.kolosov.island.services.printInfo;


import lombok.NoArgsConstructor;
import ru.javarush.kolosov.island.services.printInfo.methods.PrintInfoMethod;


@NoArgsConstructor
public class PrintInfoService {
    PrintInfoMethod printInfoMethod;

    public PrintInfoService(PrintInfoMethod printInfoMethod) {
        this.printInfoMethod = printInfoMethod;
    }


}
