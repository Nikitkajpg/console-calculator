package com.td.calc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Stack;

public class Calculator {
    public static double calculate(ArrayList<String> rpnLineArray) {
        Stack<Double> stack = new Stack<>();
        for (String symbol : rpnLineArray) {
            BigDecimal bd1, bd2;
            double num1, num2;
            switch (symbol) {
                case "~" -> stack.push(stack.pop() * -1);
                case "^" -> {
                    num2 = stack.pop();
                    num1 = stack.pop();
                    stack.push(Math.pow(num1, num2));
                }
                case "*" -> stack.push(stack.pop() * stack.pop());
                case "/" -> {
                    num2 = stack.pop();
                    num1 = stack.pop();
                    if (num2 != 0) {
                        stack.push(num1 / num2);
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
        }
        return stack.pop();
    }
}
