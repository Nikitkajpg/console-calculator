package com.td.calc;

import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    private String line;

    public Controller() {
        Scanner scanner = new Scanner(System.in);
        input(scanner);
        while (Checker.hasError(line)) {
            System.out.println("Incorrect expression");
            input(scanner);
        }

        line = Checker.checkNegativeNumbers(line);
        ArrayList<String> originLineArray = Parser.fillOriginLineArray(line);

        ArrayList<String> rpnLineArray = Parser.fillRpnLineArray(originLineArray);
        System.out.println("RPN: " + rpnLineArray);

        double result = Calculator.calculate(rpnLineArray);
        System.out.println(result);
    }

    private void input(Scanner scanner) {
        System.out.println("Enter expression:");
        line = scanner.nextLine();
        line = prepareLine(line);
    }

    private String prepareLine(String line) {
        return line.replaceAll("\\s+", "").replace("=", "");
    }
}
