import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/**
 * Stats
 */
public class Stats extends JPanel implements ActionListener{

    private Timer timer;
    private JButton reset;
    private JButton toggleGrid;

    public Stats() {
        super();
        this.setLocation(0, 0);
        this.setPreferredSize(new Dimension(600, 100));
        this.setBackground(new Color(10, 25, 46));
        this.setBorder(BorderFactory.createLineBorder(new Color(101, 255, 220), 2));
        reset = new JButton("Reset");
        toggleGrid = new JButton();
        reset.setFocusable(false);
        reset.addActionListener(this);
        toggleGrid.setFocusable(false);
        toggleGrid.addActionListener(this);
        this.add(reset);
        this.add(toggleGrid);
        timer = new Timer(Grid.DELAY, this);
        timer.start();
        
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        toggleGrid.setText("Toggle Grid");

        g.setFont(new Font("Helvetica", Font.PLAIN, 20)); 
        g.setColor(new Color(101, 255, 220));
        g.drawString(String.format("Score: %d", Grid.score), 460, 40);
        g.drawString(String.format("High Score: %d", Grid.score), 460, 80);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == toggleGrid) {
            Grid.toggleGridLines = !Grid.toggleGridLines;
        }
        if (e.getSource() == reset) {
            Grid.score = 0;

        }
        repaint();
    }

    


}