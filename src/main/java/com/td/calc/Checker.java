package com.td.calc;

public class Checker {
    public static void checkLine(String line) {
        checkEmptyLine(line);
        checkBrackets(line);
        checkPoints(line);
    }

    private static void checkEmptyLine(String line) {
        if (line.equals("")) {
            System.out.println("Line is empty");
            System.exit(0);
        }
    }

    private static void checkBrackets(String line) {
        int open = 0, close = 0;
        for (int i = 0; i < line.length(); i++) {
            if (String.valueOf(line.charAt(i)).equals("(")) {
                open++;
            } else if (String.valueOf(line.charAt(i)).equals(")")) {
                close++;
            }
        }
        if (open != close) {
            System.out.println("The brackets are not consistent");
            System.exit(0);
        }
    }

    private static void checkPoints(String line) {
        for (int i = 0; i < line.length(); i++) {
            if (String.valueOf(line.charAt(i)).equals(".")) {
                if (i == 0 || i == line.length() - 1) {
                    System.out.println("Incorrect input of fractional numbers");
                    System.exit(0);
                } else {
                    if (!Utils.isNumber(String.valueOf(line.charAt(i - 1))) || !Utils.isNumber(String.valueOf(line.charAt(i + 1)))) {
                        System.out.println("Incorrect input of fractional numbers");
                        System.exit(0);
                    }
                }
            }
        }
    }
}
