package ExperimentTwo;

// Triangle data range: [1, 100)
public class Triangle {
    public String classify(int a, int b, int c) {
        if (a < 1 || a > 99 || b < 1 || b > 99 || c < 1 || c > 99) {
            return "输入错误";
        }
        if (!((a + b > c) && (a + c > b) && (b + c > a))) {
            return "非三角形";
        } else if (a == b && a == c && b == c) {
            return "等边三角形";
        } else if (a != b && a != c && b != c) {
            return "一般三角形";
        } else {
            return "等腰三角形";
        }
    }
}