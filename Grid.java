import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class Grid extends JPanel implements ActionListener{
    
    public static final int GRID_WIDTH = 600;
    public static final int GRID_HEIGHT = 600;
    public static final int CELL_SIZE = 40;
    public static final int DELAY = 1000;
    public static int score;
    
    private int[][] grid = new int[(GRID_WIDTH/CELL_SIZE)][(GRID_HEIGHT/CELL_SIZE)];
    private Random gen;
    private Food apple;
    private Snake snake;
    private boolean running;
    public static boolean toggleGridLines;
    private Timer timer;
    
    public static boolean DEBUG = true;

    public Grid() {
        super();

        if (DEBUG) {
            int c = 0;
            for (int i = 0; i < (GRID_HEIGHT / CELL_SIZE); i++) {
                for (int j = 0; j < (GRID_WIDTH / CELL_SIZE); j++) {
                    grid[i][j] = c;
                    c++;
                }
            }
        }

        this.setLocation(0, 100);
        this.setPreferredSize(new Dimension(600, 600));
        this.setBackground(new Color(10, 25, 46));
        this.setFocusable(true);
        this.setBorder(BorderFactory.createLineBorder(new Color(101, 255, 220), 2, true));
        this.addKeyListener(new MyKeyAdapter());
        start();
    }

    public void start() {
        score = 0;
        gen = new Random();
        createApple();
        createSnake();
        running = true;
        toggleGridLines = true;
        timer = new Timer(DELAY,this);
		timer.start();
    }



    public void createSnake() {
        snake = new Snake();
    }

    public void createApple() {
        apple = new Food();
        int tempX = gen.nextInt((int)(GRID_WIDTH / CELL_SIZE)) * CELL_SIZE;
        int tempY = gen.nextInt((int)(GRID_HEIGHT / CELL_SIZE)) * CELL_SIZE;
        apple.setX(tempX);
        apple.setY(tempY);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if (toggleGridLines) {
            g.setColor(new Color(101, 255, 220));
            for (int i = 0; i < GRID_HEIGHT / CELL_SIZE; i++) {
                g.drawLine(i*CELL_SIZE, 0, i*CELL_SIZE, GRID_HEIGHT);
                g.drawLine(0, i*CELL_SIZE, GRID_WIDTH, i*CELL_SIZE);
            }
        }

        // drawing food
        g.setColor(new Color(100, 25, 46));
        g.fillOval(apple.getX(), apple.getY(), CELL_SIZE, CELL_SIZE);
        

        // drawing snake
        Node n = snake.getHead();
        boolean bodyRemains = true;
        while (bodyRemains) {
            int[] coors = snake.getCoorFromCell(n.data);
            if (n.next == null) {
                bodyRemains = false;
            }
            if (n == snake.getHead()) {
                g.setColor(new Color(136, 146, 175));
                g.fillRoundRect(coors[0], coors[1], Grid.CELL_SIZE, Grid.CELL_SIZE, 15, 15);
            } 
            else {
                g.setColor(new Color(168, 178, 208));
                g.draw3DRect(coors[0], coors[1], Grid.CELL_SIZE, Grid.CELL_SIZE, true);
            }
            n = n.next;
        }
    }
    

    public void move() {
        Node h = snake.getHead();
        switch (snake.getDirection()) {
            case 'R':
                snake.insertAtStart(h.data + 1);
                snake.removeAtEnd();
                break;
            case 'L':
                snake.insertAtStart(h.data - 1);
                snake.removeAtEnd();
                break;
            case 'U':
                snake.insertAtStart(h.data - (GRID_HEIGHT / CELL_SIZE));
                snake.removeAtEnd();
                break;
            case 'D':
                snake.insertAtStart(h.data + (GRID_HEIGHT / CELL_SIZE));
                snake.removeAtEnd();
                break;
            default:
                break;
        }
    }

    public void updateBody() {
        Node head = snake.getHead();
        char direction = snake.getDirection();

        if (snake.getNumOfParts() == 1) {
            switch (direction) {
                case 'R':
                    snake.insertAtEnd(head.data - 1);
                    break;
                case 'L':
                    snake.insertAtEnd(head.data + 1);
                    break;
                case 'U':
                    snake.insertAtEnd(head.data + (GRID_HEIGHT / CELL_SIZE));
                    break;
                case 'D':
                    snake.insertAtEnd(head.data - (GRID_HEIGHT / CELL_SIZE));
                    break;
                default:
                    break;
            }
        } 
        else {
            // check the directions of the last and second-last nodes
            // snake will always have at least 2 nodes (handled by if-stmt)
            Node current = snake.getHead();
            Node nextNode = current.next;
            while (nextNode.next != null) {
                current = nextNode;
                nextNode = current.next;
            }
            int diff = current.data - nextNode.data;
            switch (diff) {
                case 1:
                    snake.insertAtEnd(nextNode.data - 1);
                    break;
                case -1:
                    snake.insertAtEnd(nextNode.data + 1);
                    break;
                case (GRID_HEIGHT / CELL_SIZE):
                    snake.insertAtEnd(nextNode.data - (GRID_HEIGHT / CELL_SIZE));
                    break;
                case -(GRID_HEIGHT / CELL_SIZE):
                    snake.insertAtEnd(nextNode.data + (GRID_HEIGHT / CELL_SIZE));
                    break;
                default:
                    break;
            }
        }
    }

    public void checkFoodCollision() {
        Node head = snake.getHead();
        int[] coords = snake.getCoorFromCell(head.data);
        if (coords[0] == apple.getX() && coords[1] == apple.getY()) {
            score += 10;
            updateBody();
            createApple();
        }
    }

    public void checkCollision() {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            if (DEBUG) {
                Node h = snake.getHead();
                int[] cor = snake.getCoorFromCell(h.data);

                System.out.println(h.data + " OR " + cor[0] + ", " + cor[1]);

                for (int i = 0; i < grid.length; i++) {
                    for (int j = 0; j < grid[0].length; j++) {
                        System.out.print(grid[i][j] + new String(new char[4 - Integer.toString(grid[i][j]).length()]).replace("\0", " "));
                    }
                    System.out.println();
                }
            }

            checkFoodCollision();
            // checkCollision();
        }
        repaint();
        
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            char d = snake.getDirection();
            switch(e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if(d != 'R') {
                        snake.setDirection('L');
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(d != 'L') {
                        snake.setDirection('R');
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(d != 'D') {
                        snake.setDirection('U');
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(d != 'U') {
                        snake.setDirection('D');
                    }
                    break;
                }    
        }
    }

}