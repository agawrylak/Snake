import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;

public class MainScreen extends JPanel {

    private static long totalElapsedTime = 0;
    private static int numberOfBlocks = 20;

    private Graphics2D g2;
    private KeyControl keyControl;
    private Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


    private boolean isRunning = false;

    private Snake snake;
    private Food food;


    MainScreen() {
        snake = new Snake(numberOfBlocks, null);
        food = new Food(numberOfBlocks, snake, null);

        this.setPreferredSize(new Dimension(numberOfBlocks * 20, numberOfBlocks * 20));
        keyControl = new KeyControl(snake);
        this.addKeyListener(keyControl);
        setFocusable(true);
        requestFocus();
        start();
    }

    private void start() {
        this.mainLoop();
    }

    private void mainLoop() {
        Thread thread = new Thread() {
            public void run() {
                isRunning = true;
                long previousTime = System.currentTimeMillis();
                long currentTime = previousTime;
                long elapsedTime;

                while (isRunning) {
                    currentTime = System.currentTimeMillis();
                    elapsedTime = (currentTime - previousTime); // elapsed time in seconds
                    totalElapsedTime += elapsedTime;

                    updateGame(currentTime - previousTime);
                    repaint();


                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException ex) {
                    }
                    previousTime = currentTime;

                }
            }
        };
        thread.start();


    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, 800, 600);
        int currentx = 0, currenty = 0;
        g2.setColor(Color.black);
        for (int i = 0; i < numberOfBlocks; i++) {
            for (int y = 0; y < numberOfBlocks; y++) {
                g2.drawRect(currentx, currenty, 20, 20);
                currentx += 20;


            }
            currentx = 0;
            currenty += 20;
        }
        for (int i = 0; i < numberOfBlocks; i++) {
            for (int y = 0; y < 40; y++) {

                g2.drawRect(currentx, currenty, 20, 20);
                currentx += 20;


            }
            currentx = 0;
            currenty += 20;
        }
        for (Block b : snake.getBlocks()
        ) {
            b.draw(g2);

        }
        food.draw(g2);


    }

    private void updateGame(float elapsedSeconds) {
        if (snake.checkCollision(food) && snake.isAlive()) {
            snake.move();
        } else if (snake.checkCollision(food) && !snake.isAlive()) {
            loseGame();
        }
    }

    private void loseGame() {
        int choice = JOptionPane.showConfirmDialog(null, "Czy chcesz zagrać ponownie?", null, JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            food.spawn();
            snake.startOver(numberOfBlocks);
        } else {
            System.exit(0);
        }

    }


}
