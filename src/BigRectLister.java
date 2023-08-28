import java.awt.*;
import java.util.ArrayList;

public class BigRectLister {

    public static void main(String[] args) {

        // calls collectAll on an anonymous filter and an anonymous list of Rectangles
        ArrayList<Object> rects = Collector.collectAll(new BigRectangleFilter(), initRectangles());

        // prints the filtered list
        printRects(rects);
    }

    /**
     *
     * @return ArrayList of Objects that is filled with Rectangles.
     */
    private static ArrayList<Object> initRectangles() {
        ArrayList<Object> rectangles = new ArrayList<>();
        rectangles.add(new Rectangle(2, 2));
        rectangles.add(new Rectangle(1, 4));
        rectangles.add(new Rectangle(2, 7));
        rectangles.add(new Rectangle(1, 6));
        rectangles.add(new Rectangle(1, 1));
        rectangles.add(new Rectangle(3, 5));
        rectangles.add(new Rectangle(5, 3));
        rectangles.add(new Rectangle(2, 4));
        rectangles.add(new Rectangle(5, 2));
        rectangles.add(new Rectangle(2, 1));
        return rectangles;
    }

    /**
     * Iterates through an ArrayList of Rectangles, printing their respective lengths and perimeters.
     *
     * @param list List of Rectangles
     */
    private static void printRects(ArrayList<Object> list) {
        // header
        System.out.printf("%5s   %6s   %9s%n", "WIDTH", "HEIGHT", "PERIMETER");

        int width, height; // store widths and heights of rectangles

        for (Object rect : list) {
            width = ((Rectangle) rect).width;
            height = ((Rectangle) rect).height;
            System.out.printf("%5d   %6d   %9d%n", width, height, 2 * width + 2 * height);
        }
    }
}
