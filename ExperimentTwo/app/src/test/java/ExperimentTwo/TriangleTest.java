package ExperimentTwo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

public class TriangleTest {

    // Done with CsvSource Annotation
    @ParameterizedTest
    @CsvSource({
        "1, 50, 50, 等腰三角形",
        "2, 50, 50, 等腰三角形",
        "50, 50, 50, 等边三角形",
        "98, 50, 50, 等腰三角形",
        "99, 50, 50, 等腰三角形",
        "50, 1, 50, 等腰三角形",
        "50, 2, 50, 等腰三角形",
        "50, 98, 50, 等腰三角形",
        "50, 99, 50, 等腰三角形",
        "50, 50, 1, 等腰三角形",
        "50, 50, 2, 等腰三角形",
        "50, 50, 98, 等腰三角形",
        "50, 50, 99, 等腰三角形"
    })
    void generalBoundaryValueAnalysis(int a, int b, int c, String expected) {
        Triangle triangle = new Triangle();
        assertEquals(expected, triangle.classify(a, b, c));
    }

    // Done with CsvFileSource Annotation
    @ParameterizedTest
    @CsvFileSource(resources = "/robustness.csv")
    void robustnessBoundaryValueAnalysis(int a, int b, int c, String expected) {
        Triangle triangle = new Triangle();
        assertEquals(expected, triangle.classify(a, b, c));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/worstCase.csv")
    void worstCaseBoundaryValueAnalysis(int a, int b, int c, String expected) {
        Triangle triangle = new Triangle();
        assertEquals(expected, triangle.classify(a, b, c));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/robustnessWorstCase.csv")
    void robustnessWorstCaseBoundaryValueAnalysis(int a, int b, int c, String expected) {
        Triangle triangle = new Triangle();
        assertEquals(expected, triangle.classify(a, b, c));
    }
}