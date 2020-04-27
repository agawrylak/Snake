import javafx.geometry.Point2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Food {
    private Point2D position;
    private int numberOfBlocks;
    private Snake snake;

    public Food(int numberOfBlocks, Snake snake, Point2D position) {
        this.snake = snake;
        this.numberOfBlocks = numberOfBlocks;

        if (position == null) {
            this.position = spawn();
        } else {
            this.position = position;
        }

        spawn();
    }

    public void draw(Graphics g2) {
        g2.setColor(Color.YELLOW);
        g2.fillRect((int) position.getX() * 20 + 1, (int) (position.getY() * 20 + 1), 19, 19);
    }

    public int getX() {
        return (int) position.getX();
    }

    public int getY() {
        return (int) position.getY();
    }

    public Point2D spawn() {
        ArrayList<Integer> listofX = new ArrayList<>();
        ArrayList<Integer> listofY = new ArrayList<>();

        for (Block b : snake.getBlocks()
        ) {
            listofX.add(b.getX());
            listofY.add(b.getY());

        }
        int randX = randomize(numberOfBlocks);
        int randY = randomize(numberOfBlocks);
        while (listofX.contains(randX) && listofY.contains(randY)) {
            randX = randomize(numberOfBlocks);
            randY = randomize(numberOfBlocks);
        }
        return new Point2D(randX, randY);
    }

    private int randomize(int upperLimit) {
        Random rand = new Random();
        return rand.nextInt(upperLimit);
    }
}
