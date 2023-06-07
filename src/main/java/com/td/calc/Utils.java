package com.td.calc;

public class Utils {
    private static final String[] operators = new String[]{"^", "*", "/", "+", "-"};
    private static final String[] delimiters = new String[]{"(", ")", "."};

    public static boolean isOperatorOrDelimiter(String symbol, boolean withDot) {
        return isOperator(symbol) || isDelimiter(symbol, withDot);
    }

    public static boolean isOperator(String symbol) {
        for (String operator : operators) {
            if (symbol.equals(operator)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isDelimiter(String symbol, boolean withDot) {
        if (withDot) {
            for (String delimiter : delimiters) {
                if (symbol.equals(delimiter)) {
                    return true;
                }
            }
        } else {
            for (int i = 0; i < delimiters.length - 1; i++) {
                if (symbol.equals(delimiters[i])) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int checkPriority(String symbol) {
        return switch (symbol) {
            case "(", ")" -> 1;
            case "~" -> 2;
            case "^" -> 3;
            case "*", "/" -> 4;
            default -> 5;
        };
    }

    public static boolean isNumber(String line) {
        try {
            if (line.contains(".")) {
                Double.parseDouble(line);
            } else {
                Integer.parseInt(line);
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isDigit(String symbol) {
        try {
            Integer.parseInt(symbol);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isDot(String symbol) {
        return symbol.equals(delimiters[2]);
    }

    public static boolean isMinus(String symbol) {
        return symbol.equals(operators[4]);
    }

    public static boolean isOpenBracket(String symbol) {
        return symbol.equals(delimiters[0]);
    }
}
