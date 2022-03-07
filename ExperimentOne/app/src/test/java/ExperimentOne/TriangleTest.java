import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/***
 * 
 * @author Krisino
 * @date 2022/3/7-16:42:13
 * @description 软件测试实验一：三角形测试用例
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
    @DisplayName("不等边三角形")
    void scalene_test() {
        Triangle triangle = new Triangle();
        String type = triangle.classify(3, 4, 6);
        assertEquals("不等边三角形", type);
    }
}
