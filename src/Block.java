import javafx.geometry.Point2D;

import java.awt.*;


public class Block {
    private Point2D position;

    Block(Point2D position) {
        this.position = position;
    }

    public void draw(Graphics g2) {
        g2.setColor(Color.GREEN);
        g2.fillRect((int) position.getX() * 20 + 1, (int) position.getY() * 20 + 1, 19, 19);
    }

    public int getX() {
        return (int) position.getX();
    }

    public int getY() {
        return (int) position.getY();
    }

    public Point2D getPosition() {
        return position;
    }
}

