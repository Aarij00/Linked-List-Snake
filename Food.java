import java.util.Random;
import java.lang.Math;
import java.awt.*;

public class Food {
    
    private int x;
    private int y;
    private Random gen;
    private Color color;

    public Food(Color color) {
        gen = new Random();
        this.color = color;
        int tempX = gen.nextInt((int)(Grid.GRID_WIDTH / Grid.CELL_SIZE)) * Grid.CELL_SIZE;
        int tempY = gen.nextInt((int)(Grid.GRID_HEIGHT / Grid.CELL_SIZE)) * Grid.CELL_SIZE;
        setX(520);
        setY(0);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int newX) {
        this.x = newX;
    }

    public void setY(int newY) {
        this.y = newY;
    }    

    public Color getColor() {
        return this.color;
    }


}
