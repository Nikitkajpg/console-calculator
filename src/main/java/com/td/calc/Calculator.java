package com.td.calc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;

public class Calculator {
    Stack<Double> stack = new Stack<>();

    public Calculator(ArrayList<String> symbols) {
        for (String symbol : symbols) {
            BigDecimal bd1, bd2;
            double n1, n2;
            try {
                switch (symbol) {
                    case "~" -> stack.push(stack.pop() * -1);
                    case "%" -> stack.push(stack.pop() / 100);
                    case "^" -> {
                        n2 = stack.pop();
                        n1 = stack.pop();
                        stack.push(Math.pow(n1, n2));
                    }
                    case "*" -> stack.push(stack.pop() * stack.pop());
                    case "/" -> {
                        n2 = stack.pop();
                        n1 = stack.pop();
                        if (n2 != 0) {
                            stack.push(n1 / n2);
                        } else {
                            System.out.println("Division by 0");
                            System.exit(0);
                        }
                    }
                    case "+" -> stack.push(stack.pop() + stack.pop());
                    case "-" -> {
                        bd2 = BigDecimal.valueOf(stack.pop());
                        bd1 = BigDecimal.valueOf(stack.pop());
                        stack.push(bd1.subtract(bd2).doubleValue());
                    }
                    default -> stack.push(Double.parseDouble(symbol));
                }
            } catch (EmptyStackException e) {
                System.out.println("Incorrect expression. Mistake: " + symbol);
                System.exit(0);
            }
        }
    }

    public double printResult() {
        return stack.pop();
    }
}
