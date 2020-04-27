import javafx.geometry.Point2D;

public enum Direction {

    UP(new Point2D(0, -1)),
    DOWN(new Point2D(0, 1)),
    RIGHT(new Point2D(1, 0)),
    LEFT(new Point2D(-1, 0));

    final private Point2D vector;


    Direction(Point2D vector) {
        this.vector = vector;
    }

    public Point2D getVector() {
        return vector;
    }
}
