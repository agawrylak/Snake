import javafx.geometry.Point2D;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SnakeGameTest {

    @Test
    public void snakeMovementTest() {

        Snake testSnake = new Snake(10, null);

        for (Direction d : Direction.values()) {
            Point2D oldPosition = testSnake.getHead().getPosition();
            testSnake.setVelocity(d.getVector());
            testSnake.move();
            Assertions.assertEquals(testSnake.getHead().getPosition(), (oldPosition.add(d.getVector())));

        }

    }

    @Test
    public void snakeGrowsTest() {

        Snake testSnake = new Snake(10, new Point2D(0, 0));
        Food food = new Food(10, testSnake, new Point2D(1, 0));
        int oldSize = testSnake.getBlocks().size();

        testSnake.setVelocity(new Point2D(1, 0));
        testSnake.checkCollision(food);

        Assertions.assertEquals(oldSize + 1, testSnake.getBlocks().size());

    }

    @Test
    public void snakeDiesTest() {

        Snake testSnake = new Snake(10, new Point2D(0, 0));
        Food food = new Food(10, testSnake, new Point2D(1, 0));

        testSnake.setVelocity(new Point2D(1, 0));
        testSnake.checkCollision(food);

        food = new Food(10, testSnake, new Point2D(2, 0));
        testSnake.checkCollision(food);

        food = new Food(10, testSnake, new Point2D(3, 0));
        testSnake.checkCollision(food);

        testSnake.setVelocity(Direction.DOWN.getVector());
        testSnake.move();

        testSnake.setVelocity(Direction.LEFT.getVector());
        testSnake.move();

        testSnake.setVelocity(Direction.UP.getVector());
        testSnake.checkCollision(food);

        Assertions.assertEquals(false, testSnake.isAlive());

    }


}
