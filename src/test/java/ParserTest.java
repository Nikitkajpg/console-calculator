import com.td.calc.Parser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ParserTest {
    @Test
    public void fillRpnLineArrayShouldWorkCorrect() {
        List<String> originList = List.of("1.2", "*", "10", "-", "(", "~", "10", "+", "17", ")", "-", "10", "/", "2");
        List<String> expected = List.of("1.2", "10", "*", "10", "~", "17", "+", "-", "10", "2", "/", "-");
        ArrayList<String> origin = new ArrayList<>(originList);
        Assertions.assertEquals(expected, Parser.fillRpnLineArray(origin));
    }
}
