package ExperimentOne;

public class Triangle {
    public String classify(int a, int b, int c) {
        if (a < 1 || a > 100 || b < 1 || b > 100 || c < 1 || c > 100) {
            return "Illegal Input";
        }
        if (!((a + b > c) && (a + c > b) && (b + c > a))) {
            return "Not Triangle";
        } else if (a == b && a == c && b == c) {
            return "Equilateral triangle";
        } else if (a != b && a != c && b != c) {
            return "General Triangle";
        } else {
            return "Isosceles Triangle";
        }
    }
}