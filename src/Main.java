import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите выражение:");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        checkBrackets(line);
        checkPoints(line);

        ParseLine parseLine = new ParseLine(line);
        Calculator calculator = new Calculator(parseLine.getSymbols());

        System.out.println("Выражение в форме ОПЗ: " + parseLine.getSymbols());
        System.out.println("Результат: " + calculator.printResult());

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
            System.out.println("Скобки не согласованы.");
            System.exit(0);
        }
    }

    private static void checkPoints(String line) {
        for (int i = 0; i < line.length(); i++) {
            if (String.valueOf(line.charAt(i)).equals(".")) {
                if (i == 0 || i == line.length() - 1) {
                    System.out.println("Неверная запись дробных чисел.");
                    System.exit(0);
                } else {
                    if (!Utils.isNumber(String.valueOf(line.charAt(i - 1))) || !Utils.isNumber(String.valueOf(line.charAt(i + 1)))) {
                        System.out.println("Неверная запись дробных чисел.");
                        System.exit(0);
                    }
                }
            }
        }
    }
}
