package com.td.calc;

public class Utils {
    private static final String[] operators = new String[]{"%", "^", "*", "/", "+", "-"};
    private  static final String[] delimiters = new String[]{"(", ")"};

    static boolean isNumber(String symbol) {
        try {
            if (symbol.contains(".")) {
                Double.parseDouble(symbol);
            } else {
                Integer.parseInt(symbol);
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    static boolean isOperator(String symbol) {
        for (String operator : operators) {
            if (symbol.equals(operator)) {
                return true;
            }
        }
        return false;
    }

    static boolean isDelimiter(String symbol) {
        for (String delimiter : delimiters) {
            if (symbol.equals(delimiter)) {
                return true;
            }
        }
        return false;
    }

    static int checkPriority(String symbol) {
        if (symbol.equals("(") || symbol.equals(")")) {
            return 1;
        } else if (symbol.equals("%") || symbol.equals("~")) {
            return 2;
        } else if (symbol.equals("^")) {
            return 3;
        } else if (symbol.equals("*") || symbol.equals("/")) {
            return 4;
        } else {
            return 5;
        }
    }
}
