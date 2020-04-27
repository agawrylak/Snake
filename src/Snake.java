
import javafx.geometry.Point2D;

import java.util.LinkedList;
import java.util.Random;

public class Snake {
    private LinkedList<Block> blocks = new LinkedList<>();
    private Point2D velocity;
    private Block head;
    private boolean isAlive;
    private int numberOfBlocks;

    Snake(int numberOfBlocks, Point2D point) {

        if (point == null) {
            this.head = new Block(new Point2D(randomize(numberOfBlocks - 1), randomize(numberOfBlocks - 1)));

        } else {
            this.head = new Block(point);
        }
        blocks.add(head);
        this.velocity = new Point2D(1, 0);
        this.isAlive = true;
        this.numberOfBlocks = numberOfBlocks;

    }

    public void startOver(int numberOfBlocks) {
        blocks.clear();
        this.head = new Block(new Point2D(randomize(numberOfBlocks - 1), randomize(numberOfBlocks - 1)));
        blocks.add(head);
        this.velocity = new Point2D(1, 0);

    }


    private int randomize(int upperLimit) {
        Random rand = new Random();
        return rand.nextInt(upperLimit);
    }

    void eat(int x, int y) {
        blocks.add(new Block(new Point2D(x, y)));
    }

    void move() {

        Point2D oldPos = this.head.getPosition();
        blocks.remove(blocks.get(0));
        this.head = new Block(oldPos.add(velocity));
        blocks.add(this.head);
    }

    private void grow(int x, int y) {
        this.head = new Block(new Point2D(x, y));
        blocks.add(this.head);


    }

    public boolean checkCollision(Food food) {
        for (Block b : this.getBlocks()
        ) {
            if (this.head.getX() + this.getDx() == b.getX() && this.head.getY() + this.getDy() == b.getY()) {
                this.isAlive = false;
                return true;
            }

        }

        if (this.getDx() + this.getHead().getX() == numberOfBlocks) {

            snakeOutOfBonds(new Point2D(-numberOfBlocks, 0));
            return true;

        } else if (this.getDx() + this.getHead().getX() == -1) {

            snakeOutOfBonds(new Point2D(numberOfBlocks, 0));
            return true;

        } else if (this.getDy() + this.getHead().getY() == -1) {

            snakeOutOfBonds(new Point2D(0, numberOfBlocks));
            return true;

        } else if (this.getDy() + this.getHead().getY() == numberOfBlocks) {

            snakeOutOfBonds(new Point2D(0, -numberOfBlocks));
            return true;

        } else if (this.getDy() + this.getHead().getY() == food.getY() && this.getDx() + this.getHead().getX() == food.getX()) {
            this.grow(food.getX(), food.getY());
            food.spawn();
        }
        return false;
    }

    public int getDx() {
        return (int) velocity.getX();
    }

    public int getDy() {
        return (int) velocity.getY();
    }

    public LinkedList<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(LinkedList<Block> blocks) {
        this.blocks = blocks;
    }

    public Block getHead() {
        return head;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public Point2D getVelocity() {
        return velocity;
    }

    public void setVelocity(Point2D velocity) {
        this.velocity = velocity;
    }

    private void snakeOutOfBonds(Point2D point) {
        Point2D oldVelocity = this.getVelocity();
        this.setVelocity(point);
        this.move();
        this.setVelocity(oldVelocity);
    }
}
