
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KeyControl implements KeyListener {
    Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    Snake snake;
    private Direction direction;
    //0 - up, 1 - down, 2 - left, 3 - right

    public KeyControl(Snake snake) {
        this.snake = snake;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (direction != Direction.LEFT) {
                direction = Direction.RIGHT;
                LOGGER.log(Level.INFO, "PRESSED RIGHT");
            }


        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (direction != Direction.RIGHT) {
                direction = Direction.LEFT;
                LOGGER.log(Level.INFO, "PRESSED LEFT");
            }

        }

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (direction != Direction.DOWN) {
                direction = Direction.UP;
                LOGGER.log(Level.INFO, "PRESSED UP");
            }


        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (direction != Direction.UP) {
                direction = Direction.DOWN;
                LOGGER.log(Level.INFO, "PRESSED DOWN");
            }

        }
        snake.setVelocity(direction.getVector());

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
