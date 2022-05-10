import java.util.Random;
import java.lang.Math;

public class Food {
    
    private int x;
    private int y;
    private Random gen;

    public Food() {
        gen = new Random();
        int tempX = gen.nextInt((int)(Grid.GRID_WIDTH / Grid.CELL_SIZE)) * Grid.CELL_SIZE;
        int tempY = gen.nextInt((int)(Grid.GRID_HEIGHT / Grid.CELL_SIZE)) * Grid.CELL_SIZE;
        setX(tempX);
        setY(tempY);
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


}
