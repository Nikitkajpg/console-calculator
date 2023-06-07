package com.td.calc;

import java.util.ArrayList;
import java.util.Stack;

public class Parser {
    public static ArrayList<String> fillRpnLineArray(ArrayList<String> originLineArray) {
        ArrayList<String> symbols = new ArrayList<>();
        Stack<String> stack = new Stack<>();

        for (String symbol : originLineArray) {
            if (Utils.isNumber(symbol)) {
                symbols.add(symbol);
            } else {
                checkOperators(symbol, stack, symbols);
            }
        }

        extractSymbolsFromStack(symbols, stack);
        return symbols;
    }

    private static void extractSymbolsFromStack(ArrayList<String> symbols, Stack<String> stack) {
        int stackSize = stack.size();
        for (int i = 0; i < stackSize; i++) {
            String symbol = stack.pop();
            symbols.add(symbol);
        }
    }

    private static void checkOperators(String symbol, Stack<String> stack, ArrayList<String> symbols) {
        if (symbol.equals("(")) {
            stack.push(symbol);
        } else if (symbol.equals(")")) {
            // executes all symbols from stack till "(", than deletes "("
            while (!stack.peek().equals("(")) {
                symbols.add(stack.pop());
            }
            stack.pop();
        } else {
            while (!stack.empty() && Utils.checkPriority(symbol) >= Utils.checkPriority(stack.peek()) && !stack.peek().equals("(")) {
                symbols.add(stack.pop());
            }
            if (symbols.size() > 1 && !stack.empty()) {
                if (symbols.get(symbols.size() - 1).equals("~") && Utils.checkPriority(symbol) == Utils.checkPriority(stack.peek())) {
                    symbols.add(stack.pop());
                }
            }
            stack.push(symbol);
        }
    }

    public static ArrayList<String> fillOriginLineArray(String line) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < line.length(); i++) {
            String symbol = String.valueOf(line.charAt(i));
            if (!Utils.isDigit(symbol)) {
                arrayList.add(symbol);
            } else {
                String number = symbol;
                if (i < line.length() - 1) {
                    String nextSymbol = String.valueOf(line.charAt(i + 1));
                    while (i < line.length() - 1 && Utils.isDigit(nextSymbol) || Utils.isDot(nextSymbol)) {
                        number = number.concat(nextSymbol);
                        i++;
                        if (i < line.length() - 1) {
                            nextSymbol = String.valueOf(line.charAt(i + 1));
                        }
                    }
                }
                arrayList.add(number);
            }
        }
        return arrayList;
    }
}
