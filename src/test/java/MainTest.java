import com.td.calc.Calculator;
import com.td.calc.ParseLine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class MainTest {

    @ParameterizedTest
    @CsvSource({
            "5-5, 0.0",
            " 5 - 5 , 0.0",
            "5, 5.0",
            "3.2+3=, 6.2",
            "12*4, 48.0",
            "2.4-4.2, -1.8",
            "34/5, 6.8",
            "4^2, 16.0",
            "4.6*7*(4^2+(66-50))-0.4-1100+140/2, 0.0"
    })
    public void resultShouldBeCorrect(String input, String expected) {
        ParseLine parseLine = new ParseLine(input);
        Calculator calculator = new Calculator(parseLine.getSymbols());
        Assertions.assertEquals(expected, String.valueOf(calculator.printResult()));
    }
}
