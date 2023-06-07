import com.td.calc.Checker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CheckerTest {
    @ParameterizedTest
    @ValueSource(strings = { // only incorrect
            "",
            "1",
            "12",
            "123456789",
            "-",
            "1-",
            "-1",
            "1++1",
            "+1-1",
            "1+1+1+",

            ".12+123",
            "12+123.",
            "12.+123",
            "12+.123",
            "1.2+.123",
            "1..2+123",

            "(1+2",
            "1+2)",
            "(1+2(",
            ")1+2(",
            "((()))",
            "-(-1--2)--(-(-3+-4)",
            "(1+2)*3-4*(d5+6)",

            "^2",
            "2^",
            "2^^2",
            "2+3^^"
    })
    public void lineShouldHaveErrors(String input) {
        Assertions.assertTrue(Checker.hasError(input));
    }

    @ParameterizedTest
    @ValueSource(strings = { // only correct
            "1+1",
            "-1-1",
            "1/-1",
            "-1-1-1-1-1-1-1-1",
            "123456789+876-2/764*875",

            "12.345-0.345--12",

            "(1+1)",
            "(12+34)*56",
            "12*(34+56)",
            "(12+34-56)",
            "((12+34)*56-78)/9",
            "1*(23+4*(56-7))+8",
            "(1+2)*3-4*(5+6)",
            "-(-1--2)--(-(-3+-4))",
            "1.1*(2.3+4.4*(5.6-7.7))+8.8",

            "2^2",
            "(2^2)^2",
            "-(2)^2",
            "2^-2",
            "2^((-2)^2)"
    })
    public void lineShouldNotHaveErrors(String input) {
        Assertions.assertFalse(Checker.hasError(input));
    }

    @Test
    public void checkNegativeNumbersShouldWorkCorrect() {
        Assertions.assertEquals("~(~1-~2)-~(~(~3+~4))", Checker.checkNegativeNumbers("-(-1--2)--(-(-3+-4))"));
    }
}
