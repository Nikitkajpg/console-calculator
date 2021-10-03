import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;

public class Calculator {
    Stack<Double> stack = new Stack<>();

    public Calculator(ArrayList<String> symbols) {
        for (String symbol : symbols) {
            double n1, n2;
            try {
                switch (symbol) {
                    case "~":
                        stack.push(stack.pop() * -1);
                        break;
                    case "%":
                        stack.push(stack.pop() / 100);
                        break;
                    case "^":
                        n2 = stack.pop();
                        n1 = stack.pop();
                        stack.push(Math.pow(n1, n2));
                        break;
                    case "*":
                        stack.push(stack.pop() * stack.pop());
                        break;
                    case "/":
                        n2 = stack.pop();
                        n1 = stack.pop();
                        if (n2 != 0) {
                            stack.push(n1 / n2);
                        } else {
                            System.out.println("Деление на 0.");
                            System.exit(0);
                        }
                        break;
                    case "+":
                        stack.push(stack.pop() + stack.pop());
                        break;
                    case "-":
                        n2 = stack.pop();
                        n1 = stack.pop();
                        stack.push(n1 - n2);
                        break;
                    default:
                        stack.push(Double.parseDouble(symbol));
                }
            } catch (EmptyStackException e) {
                System.out.println("Некорректная запись выражения. Ошибка: " + symbol);
                System.exit(0);
            }
        }
    }

    public double printResult() {
        return stack.pop();
    }
}
