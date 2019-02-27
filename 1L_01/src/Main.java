import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static double getArea(ArrayList<Point> points) {
        double a = calcLength(points.get(0), points.get(1));
        double b = calcLength(points.get(1), points.get(2));
        double c = calcLength(points.get(2), points.get(0));
        double p = (a + b + c) / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    public static double calcLength(Point one, Point two) {
        return Math.sqrt(Math.pow((two.x - one.x), 2) + Math.pow((two.y - one.y), 2));
    }

    public static void main(String[] args) {
        ArrayList<Point> points = new ArrayList<Point>();
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            points.add(new Point(scanner.nextDouble(), scanner.nextDouble()));
        }
        for (Point s : points) {
            System.out.println(s.x + " " + s.y);
        }
        System.out.println(getArea(points));
    }
}
