import javax.swing.*;

public class SnakeGame {
    public SnakeGame() {
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake - Artur Gawrylak 2020");
        frame.setDefaultCloseOperation(3);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), 0));
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        MainScreen mainScreen = new MainScreen();
        frame.getContentPane().add(mainScreen);
        frame.pack();
        frame.setVisible(true);
    }
}