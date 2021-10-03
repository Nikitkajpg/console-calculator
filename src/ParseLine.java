import java.util.ArrayList;
import java.util.Stack;

public class ParseLine {
    Stack<String> stack = new Stack<>();
    ArrayList<String> symbols = new ArrayList<>();

    public ParseLine(String line) {
        for (int i = 0; i < line.length(); i++) {
            String symbol = String.valueOf(line.charAt(i));

            if (Utils.isNumber(symbol) || Utils.isOperator(symbol) || Utils.isDelimiter(symbol) || symbol.equals(" ") || symbol.equals(".")) {
                // проверка на отрицательное число
                if (symbol.equals("-")) {
                    String next = String.valueOf(line.charAt(i + 1));
                    if (i == 0 && (Utils.isNumber(next) || next.equals("("))) {
                        symbol = "~";
                    } else if (i != 0) {
                        String prev = String.valueOf(line.charAt(i - 1));
                        if (Utils.isNumber(next) && prev.equals("-") || ((Utils.isOperator(prev) || prev.equals("(")) && !prev.equals("%") && !prev.equals("-") && Utils.isNumber(next))) {
                            symbol = "~";
                        }
                    }
                }

                // проверка на дробные числа
                if (!symbol.equals(" ") && Utils.isNumber(symbol)) {
                    if (i + 1 < line.length()) {
                        String nextSymbol = String.valueOf(line.charAt(i + 1));
                        while (Utils.isNumber(nextSymbol) || nextSymbol.equals(".")) {
                            symbol = symbol.concat(nextSymbol);
                            i++;
                            if (i + 1 < line.length()) {
                                nextSymbol = String.valueOf(line.charAt(i + 1));
                            } else {
                                break;
                            }
                        }
                    }

                }

                // проверка "оператор или операнд"
                if (Utils.isNumber(symbol)) {
                    symbols.add(symbol);
                } else {
                    checkOperators(symbol);
                }
            } else {
                System.out.println("Некорректный символ. Ошибка: " + symbol);
                System.exit(0);
            }
        }

        // вытаскивание всех значений из стека
        int stackSize = stack.size();
        for (int i = 0; i < stackSize; i++) {
            String symbol = stack.pop();
            symbols.add(symbol);
        }
    }

    private void checkOperators(String symbol) {
        if (symbol.equals("(")) {
            stack.push(symbol);
        } else if (symbol.equals(")")) {
            while (!stack.peek().equals("(")) {
                symbols.add(stack.pop());
            }
            stack.pop(); // удаляет "("
        } else if (!symbol.equals(" ")) {
            while (!stack.empty() && Utils.checkPriority(symbol) >= Utils.checkPriority(stack.peek()) && !stack.peek().equals("(")) {
                        symbols.add(stack.pop());
            }
            if (symbols.size() > 1 && !stack.empty()) {
                if (String.valueOf(symbols.get(symbols.size() - 1)).equals("~") && Utils.checkPriority(symbol) == Utils.checkPriority(stack.peek())) {
                    symbols.add(stack.pop());
                }
            }
            stack.push(symbol);
        }
    }

    public ArrayList<String> getSymbols() {
        return symbols;
    }
}
