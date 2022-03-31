package experimentthree;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {
    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv")
    void testBilling(String start, String end, int startDST, int endDST, String expected) {
        Main main = new Main();
        assertEquals(expected, String.format("%.2f", main.billing(start, end, startDST, endDST)));
    }
}
