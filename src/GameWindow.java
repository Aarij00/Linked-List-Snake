import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * GameWindow
 */
public class GameWindow extends JFrame{

    public static final int WIDTH = 600;
    public static final int HEIGHT = 700;

    private JPanel container;
    private JPanel statsPanel;
    private JPanel gridPanel;
    public GameWindow() {
        super();

        statsPanel = new Stats();
        gridPanel = new Grid();
        
        
        
        this.setTitle("Linked List Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(statsPanel, BorderLayout.NORTH);
        this.add(gridPanel, BorderLayout.CENTER);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}