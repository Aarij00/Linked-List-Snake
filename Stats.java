import javax.swing.*;
import javax.swing.plaf.metal.MetalButtonUI;

import java.awt.*;
import java.awt.event.*;


/**
 * Stats
 */
public class Stats extends JPanel implements ActionListener{

    private Timer timer;
    private JButton resetBtn;
    private JButton toggleGridBtn;

    public Stats() {
        super();
        this.setLayout(null);
        this.setLocation(0, 0);
        this.setPreferredSize(new Dimension(600, 100));
        this.setBackground(new Color(10, 25, 46));
        this.setBorder(BorderFactory.createLineBorder(new Color(101, 255, 220), 2));
        initialize();
    }

    public void initialize() {
        resetBtn = new JButton("Reset");
        resetBtn.setLocation(10, 35);
        resetBtn.setSize(new Dimension(150, 30));
        resetBtn.setUI(new MetalButtonUI());
        resetBtn.setOpaque(false);
        resetBtn.setForeground(new Color(101, 255, 220));
        resetBtn.setBorder(BorderFactory.createLineBorder(new Color(101, 255, 220)));
        resetBtn.setFocusable(false);
        resetBtn.addActionListener(this);
        this.add(resetBtn);
        
        toggleGridBtn = new JButton("Toggle Grid Lines");
        toggleGridBtn.setLocation(440, 35);
        toggleGridBtn.setSize(new Dimension(150, 30));
        toggleGridBtn.setUI(new MetalButtonUI());
        toggleGridBtn.setOpaque(false);
        toggleGridBtn.setForeground(new Color(101, 255, 220));
        toggleGridBtn.setBorder(BorderFactory.createLineBorder(new Color(101, 255, 220)));
        toggleGridBtn.setFocusable(false);
        toggleGridBtn.addActionListener(this);
        this.add(toggleGridBtn);

        timer = new Timer(Grid.DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.setFont(new Font("Helvetica", Font.PLAIN, 20)); 
        g.setColor(new Color(101, 255, 220));
        g.drawString(String.format("Score: %d", Grid.score), 240, 40);
        g.drawString(String.format("High Score: %d", Grid.highScore), 240, 80);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == toggleGridBtn) {
            Grid.toggleGridLines = !Grid.toggleGridLines;
        }
        if (e.getSource() == resetBtn) {
            if (!Grid.running) {
                Grid.repaintCanvas = true;
                Grid.score = 0;
                Grid.highScore = Grid.score > Grid.highScore ? Grid.score : Grid.highScore;
                Grid.clickedReset = true;

            }

        }
        repaint();
    }

}