package ExperimentOne;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/***
 *
 * @author Krisino
 * @date 2022/3/7-16:42:13
 * @description SoftwareTesting: Experiment One: Triangle
 */
public class TriangleTest {


    @Test
    @DisplayName("Isosceles Triangle")
    void general_Triangle_1() {
        Triangle triangle = new Triangle();
        String type = triangle.classify(1, 50, 50);
        assertEquals("Isosceles Triangle", type);
    }

    @Test
    @DisplayName("Isosceles Triangle")
    void general_Triangle_2() {
        Triangle triangle = new Triangle();
        String type = triangle.classify(2, 50, 50);
        assertEquals("Isosceles Triangle", type);
    }

    @Test
    @DisplayName("Equilateral triangle")
    void equilateral_triangle_1() {
        Triangle triangle = new Triangle();
        String type = triangle.classify(50, 50, 50);
        assertEquals("Equilateral triangle", type);
    }

    @Test
    @DisplayName("Isosceles Triangle")
    void general_Triangle_3() {
        Triangle triangle = new Triangle();
        String type = triangle.classify(99, 50, 50);
        assertEquals("Isosceles Triangle", type);
    }

    @Test
    @DisplayName("Not Triangle")
    void not_triangle_1() {
        Triangle triangle = new Triangle();
        String type = triangle.classify(100, 50, 50);
        assertEquals("Not Triangle", type);
    }

    @Test
    @DisplayName("Isosceles Triangle")
    void general_Triangle_4() {
        Triangle triangle = new Triangle();
        String type = triangle.classify(50, 1, 50);
        assertEquals("Isosceles Triangle",   type);
    }

    @Test
    @DisplayName("Isosceles Triangle")
    void general_Triangle_5() {
        Triangle triangle = new Triangle();
        String type = triangle.classify(50, 2, 50);
        assertEquals("Isosceles Triangle", type);
    }

    @Test
    @DisplayName("Isosceles Triangle")
    void general_Triangle_6() {
        Triangle triangle = new Triangle();
        String type = triangle.classify(50, 99, 50);
        assertEquals("Isosceles Triangle", type);
    }

    @Test
    @DisplayName("Not Triangle")
    void not_triangle_2() {
        Triangle triangle = new Triangle();
        String type = triangle.classify(50, 100, 50);
        assertEquals("Not Triangle", type);
    }

    @Test
    @DisplayName("Isosceles Triangle")
    void general_Triangle_7() {
        Triangle triangle = new Triangle();
        String type = triangle.classify(50, 50, 1);
        assertEquals("Isosceles Triangle", type);
    }

    @Test
    @DisplayName("Isosceles Triangle")
    void general_Triangle_8() {
        Triangle triangle = new Triangle();
        String type = triangle.classify(50, 50, 2);
        assertEquals("Isosceles Triangle", type);
    }

    @Test
    @DisplayName("Isosceles Triangle")
    void general_Triangle_9() {
        Triangle triangle = new Triangle();
        String type = triangle.classify(50, 50, 99);
        assertEquals("Isosceles Triangle", type);
    }

    @Test
    @DisplayName("Not Triangle")
    void not_triangle_3() {
        Triangle triangle = new Triangle();
        String type = triangle.classify(50, 50, 100);
        assertEquals("Not Triangle", type);
    }

    @Test
    @DisplayName("Illegal Input")
    void illegal_input_1() {
        Triangle triangle = new Triangle();
        String type = triangle.classify(0, 50, 50);
        assertEquals("Illegal Input", type);
    }

    @Test
    @DisplayName("Illegal Input")
    void illegal_input_2() {
        Triangle triangle = new Triangle();
        String type = triangle.classify(101, 50, 50);
        assertEquals("Illegal Input", type);
    }

    @Test
    @DisplayName("Illegal Input")
    void illegal_input_3() {
        Triangle triangle = new Triangle();
        String type = triangle.classify(50, 0, 50);
        assertEquals("Illegal Input", type);
    }

    @Test
    @DisplayName("Illegal Input")
    void illegal_input_4() {
        Triangle triangle = new Triangle();
        String type = triangle.classify(50, 101, 50);
        assertEquals("Illegal Input", type);
    }

    @Test
    @DisplayName("Illegal Input")
    void illegal_input_5() {
        Triangle triangle = new Triangle();
        String type = triangle.classify(50, 50, 0);
        assertEquals("Illegal Input", type);
    }

    @Test
    @DisplayName("Illegal Input")
    void illegal_input_6() {
        Triangle triangle = new Triangle();
        String type = triangle.classify(50, 50, 101);
        assertEquals("Illegal Input", type);
    }

    @Test
    @DisplayName("General Triangle")
    void general_triangle_1() {
        Triangle triangle = new Triangle();
        String type = triangle.classify(49, 50, 51);
        assertEquals("General Triangle", type);
    }

}
