package com.td.calc;

public class Checker {
    public static boolean hasError(String line) {
        return !line.matches(
                "(\\(*-?[()]*-?\\d+[()]*-?(\\.\\d+[()]*-?)?([-+*/^])?)+[()]*-?[()]*-?\\d+(\\.\\d+)?\\)*")
                || line.matches("\\d*") || checkBracketsNumber(line);
    }

    private static boolean checkBracketsNumber(String line) {
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
            return true;
        } else {
            return false;
        }
    }

    public static String checkNegativeNumbers(String line) {
        StringBuilder sb = new StringBuilder(line);
        for (int i = 0; i < sb.length(); i++) {
            String symbol = String.valueOf(sb.charAt(i));

            if (Utils.isMinus(symbol)) {
                String nextSymbol = String.valueOf(sb.charAt(i + 1));
                if (i == 0 && (Utils.isDigit(nextSymbol) || Utils.isDelimiter(nextSymbol, false))) {
                    sb.setCharAt(i, '~');
                } else if (i != 0) {
                    String prevSymbol = String.valueOf(sb.charAt(i - 1));
                    if ((Utils.isDigit(nextSymbol) && (Utils.isOperator(prevSymbol) || Utils.isOpenBracket(prevSymbol))) ||
                            (Utils.isDelimiter(nextSymbol, false) && Utils.isOperatorOrDelimiter(prevSymbol, false))) {
                        sb.setCharAt(i, '~');
                    }
                }
            }
        }
        return sb.toString();
    }
}
