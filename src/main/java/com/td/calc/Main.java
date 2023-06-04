package com.td.calc;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Enter expression:");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        Checker.checkLine(line);

        ParseLine parseLine = new ParseLine(line);
        Calculator calculator = new Calculator(parseLine.getSymbols());

        System.out.println("Выражение в форме ОПЗ: " + parseLine.getSymbols());
        System.out.println("Результат: " + calculator.printResult());

    }
}
