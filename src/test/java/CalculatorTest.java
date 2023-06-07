import com.td.calc.Calculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CalculatorTest {
    @Test
    public void calculateShouldWorkCorrect() {
        List<String> input1 = List.of("1", "~", "2", "~", "-", "~", "3", "~", "4", "~", "+", "~", "~", "-");
        List<String> input2 = List.of("2", "2", "~", "2", "^", "^");
        List<String> input3 = List.of("12.345", "0.345", "-", "12", "~", "-");

        Assertions.assertEquals(6.0, Calculator.calculate(new ArrayList<>(input1)));
        Assertions.assertEquals(16.0, Calculator.calculate(new ArrayList<>(input2)));
        Assertions.assertEquals(24.0, Calculator.calculate(new ArrayList<>(input3)));
    }
}
