import javax.swing.JFrame;

public class GameWindow extends JFrame {

    public GameWindow() {
        this.setTitle("Java Pong");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        GamePanel panel = new GamePanel();
        this.add(panel);
        this.pack();

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
