import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class Grid extends JPanel implements ActionListener{
    
    public static final int GRID_WIDTH = 600;
    public static final int GRID_HEIGHT = 600;
    public static final int CELL_SIZE = 40;
    public static final int DELAY = 150;
    public static boolean clickedReset;
    public static int score;
    public static int highScore;
    public static boolean repaintCanvas = true;
    
    private Random gen;
    private Food apple;
    private Snake snake;
    public static boolean running;
    public static boolean toggleGridLines;
    private Timer timer;
    
    public static boolean DEBUG = true;

    Action upAction;
    Action downAction;
    Action leftAction;
    Action rightAction;

    public Grid() {
        super();

        this.setLocation(0, 100);
        this.setPreferredSize(new Dimension(600, 600));
        this.setBackground(new Color(10, 25, 46));
        this.setFocusable(true);
        this.setBorder(BorderFactory.createLineBorder(new Color(101, 255, 220), 2, true));

        upAction = new UpAction();
        downAction = new DownAction();
        leftAction = new LeftAction();
        rightAction = new RightAction();

        this.getInputMap().put(KeyStroke.getKeyStroke("UP"), "upAction");
        this.getActionMap().put("upAction", upAction);

        this.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "downAction");
        this.getActionMap().put("downAction", downAction);

        this.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "leftAction");
        this.getActionMap().put("leftAction", leftAction);

        this.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "rightAction");
        this.getActionMap().put("rightAction", rightAction);

        start();
    }

    public void start() {
        clickedReset = false;
        score = 0;
        highScore = score;
        gen = new Random();
        createApple();
        createSnake();
        running = true;
        toggleGridLines = true;
        timer = new Timer(DELAY,this);
		timer.start();
    }



    public void createSnake() {
        if (clickedReset) {
            clickedReset = false;
            snake = null;
            snake = new Snake();
            createApple();
            timer.setDelay(150);
            running = true;
            
        }
        else if (snake == null) {
            snake = new Snake();
        }
        
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
            Tuple<Integer, Integer> coors = n.data;
            int x = coors.getFirst();
            int y = coors.getSecond();
            if (n.next == null) {
                bodyRemains = false;
            }
            
            if (n == snake.getHead()) {
                g.setColor(new Color(136, 146, 175));
                g.fillRoundRect(x, y, Grid.CELL_SIZE, Grid.CELL_SIZE, 15, 15);
            } 
            else {
                g.setColor(new Color(200, 178, 208));
                g.fill3DRect(x, y, Grid.CELL_SIZE, Grid.CELL_SIZE, false);
            }
            n = n.next;
        }
    }
    

    public void move() {
        Node h = snake.getHead();
        Tuple<Integer, Integer> c = h.data;
        int hX = c.getFirst();


        int hY = c.getSecond();
        char d = snake.getDirection();

        if (d == 'R') {
            Tuple<Integer, Integer> newT = new Tuple<>(hX+CELL_SIZE, hY);
            snake.insertAtStart(newT);
            snake.removeAtEnd();
        }else if (d == 'L') {
            Tuple<Integer, Integer> newT = new Tuple<>(hX-CELL_SIZE, hY);
            snake.insertAtStart(newT);
            snake.removeAtEnd();
        }else if (d == 'U') {
            Tuple<Integer, Integer> newT = new Tuple<>(hX, hY-CELL_SIZE);
            snake.insertAtStart(newT);
            snake.removeAtEnd();
        }else if (d == 'D') {
            Tuple<Integer, Integer> newT = new Tuple<>(hX, hY+CELL_SIZE);
            snake.insertAtStart(newT);
            snake.removeAtEnd();
        }

        checkCollision();
    }

    public void updateBody() {
        Node t = snake.getTail();
        Tuple<Integer, Integer> c = t.data;
        int tX = c.getFirst();
        int tY = c.getSecond();
        char d = snake.getDirection();
        
        if (snake.getNumOfParts() == 1) {
            if (d == 'R') {
                Tuple<Integer, Integer> newT = new Tuple<>(tX-CELL_SIZE, tY);
                snake.insertAtEnd(newT);
            }else if (d == 'L') {
                Tuple<Integer, Integer> newT = new Tuple<>(tX+CELL_SIZE, tY);
                snake.insertAtEnd(newT);
            }else if (d == 'U') {
                Tuple<Integer, Integer> newT = new Tuple<>(tX, tY+CELL_SIZE);
                snake.insertAtEnd(newT);
            }else if (d == 'D') {
                Tuple<Integer, Integer> newT = new Tuple<>(tX, tY-CELL_SIZE);
                snake.insertAtEnd(newT);
            }
        }
        else {
            // check the directions of the last and second-last nodes
            // snake will always have at least 2 nodes (handled by if-stmt)
            ArrayList<Node> body = snake.getBodyWithHead();
            Node secondLastNode = body.get(body.size() - 2);
            Node lastNode = body.get(body.size() - 1);

            int xDiff = secondLastNode.data.getFirst() - lastNode.data.getFirst();
            int yDiff = secondLastNode.data.getSecond() - lastNode.data.getSecond();

            int tailX = lastNode.data.getFirst();
            int tailY = lastNode.data.getSecond();

            if (xDiff == 40 && yDiff == 0) {
                Tuple<Integer, Integer> newT = new Tuple<>(tailX-CELL_SIZE, tailY);
                snake.insertAtEnd(newT);
            } else if (xDiff == -40 && yDiff == 0) {
                Tuple<Integer, Integer> newT = new Tuple<>(tailX+CELL_SIZE, tailY);
                snake.insertAtEnd(newT);
            } else if (xDiff == 0 && yDiff == 40) {
                Tuple<Integer, Integer> newT = new Tuple<>(tailX, tailY-CELL_SIZE);
                snake.insertAtEnd(newT);
            } else if (xDiff == 0 && yDiff == -40) {
                Tuple<Integer, Integer> newT = new Tuple<>(tailX, tailY+CELL_SIZE);
                snake.insertAtEnd(newT);
            }
        }        
    }


    public void checkFoodCollision() {
        Node head = snake.getHead();
        Tuple<Integer, Integer> coors = head.data;
        int x = coors.getFirst();
        int y = coors.getSecond();
        if (x == apple.getX() && y == apple.getY()) {
            score += 10;
            highScore = score > highScore ? score : highScore;
            updateBody();
            createApple();
            timer.setDelay(timer.getDelay() - 2);
        }
    }

    public void checkCollision() {
        Node head = snake.getHead();
        Tuple<Integer, Integer> c = head.data;
        int headX = c.getFirst();
        int headY = c.getSecond();

        // border collision
        if (headX > (GRID_WIDTH - CELL_SIZE) || headX < 0 || headY < 0 || headY > (GRID_HEIGHT - CELL_SIZE)) {
            repaintCanvas = false;
            running = false;
        }

        // body collision
        ArrayList<Node> x = snake.getBody();
        for (Node node : x) {
            if (headX == node.data.getFirst() && headY == node.data.getSecond()) {
                repaintCanvas = false;
                running = false;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkFoodCollision();
        }
        else {
            createSnake();
        }
        if (repaintCanvas) {
            repaint();
        }
        
    }




    public class UpAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {  
            char d = snake.getDirection();
            if (d != 'D') {
                snake.setDirection('U');
            }          
        }
    }

    public class DownAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {     
            char d = snake.getDirection();
            if (d != 'U') {
                snake.setDirection('D');
            }        
        }
    }

    public class LeftAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {    
            char d = snake.getDirection();
            if (d != 'R') {
                snake.setDirection('L');
            }         
        }
    }

    public class RightAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {     
            char d = snake.getDirection();
            if (d != 'L') {
                snake.setDirection('R');
            }        
        }
    }

}