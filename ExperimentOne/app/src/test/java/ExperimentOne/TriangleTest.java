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
    @DisplayName("输入错误")
    void parameters_error_test() {
        Triangle triangle = new Triangle();
        String type = triangle.classify(0, 4, 5);
        assertEquals("输入错误", type);
    }

    @Test
    @DisplayName("非三角形")
    void not_triangle() {
        Triangle triangle = new Triangle();
        String type = triangle.classify(1, 1, 3);
        assertEquals("非三角形", type);
    }

    @Test
    @DisplayName("一般三角形")
    void scalene_test() {
        Triangle triangle = new Triangle();
        String type = triangle.classify(3, 4, 6);
        assertEquals("一般三角形", type);
    }

    @Test
    @DisplayName("等边三角形")
    void equilateral_triangle() {
        Triangle triangle = new Triangle();
        String type = triangle.classify(1, 1, 1);
        assertEquals("等边三角形", type);
    }

    @Test
    @DisplayName("等腰三角形")
    void isosceles_triangle() {
        Triangle triangle = new Triangle();
        String type = triangle.classify(2, 2, 1);
        assertEquals("等腰三角形", type);
    }

}
