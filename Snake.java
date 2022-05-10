import java.lang.Math;

class Node {
    int data;
    Node next;

    public Node(int newData) {
        data = newData;
        next = null;
    }
}

public class Snake {

    private Node head;
    private char direction;
    private int numOfParts;
    

    public Snake() {
        int startingPoint = (int) (Math.pow((Grid.GRID_WIDTH / Grid.CELL_SIZE), 2) / 2);
        head = new Node(startingPoint);

        direction = 'R';
        numOfParts = 1;
    }

    public void insertAtStart(int data) {
        Node node = new Node(data);
        node.next = head;
        head = node;

        numOfParts++;
    }

    public void removeAtEnd() {
        Node n = head;
        while (n.next.next != null) {
            n = n.next;
        }
        n.next = null;

        numOfParts--;
    }

    public void insertAtEnd(int d) {
        Node node = new Node(d);
        Node n = head;
        while (n.next != null) {
            n = n.next;
        }
        n.next = node;
        node.next = null;

        numOfParts++;
    }

    public int[] getCoorFromCell(int n) {
        int[] coordinates = new int[2];
        int x = (n % (Grid.GRID_WIDTH / Grid.CELL_SIZE)) * Grid.CELL_SIZE;
        int y = Math.floorDiv(n, (Grid.GRID_WIDTH / Grid.CELL_SIZE)) * Grid.CELL_SIZE;
        coordinates[0] = x;
        coordinates[1] = y;
        return coordinates;
    }

    public Node getHead() {
        return this.head;
    }

    public char getDirection() {
        return this.direction;
    }

    public void setDirection(char newD) {
        this.direction = newD;
    }

    public int getNumOfParts() {
        return this.numOfParts;
    }

    public void setNumOfParts(int numOfParts) {
        this.numOfParts = numOfParts;
    }

}
