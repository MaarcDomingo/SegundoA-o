import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    public static final int PANEL_WIDTH = 800;
    public static final int PANEL_HEIGHT = 600;

    private Timer timer;

    public GamePanel() {
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(this);

        iniciarJuego();

        // Game loop a uno 60 FPS (16ms)
        timer = new Timer(16, this);
        timer.start();
    }

    private void iniciarJuego() {

    }
}
