import java.lang.Math;
import java.util.ArrayList;

class Tuple<X, Y> {
    private X x;
    private Y y;
    public Tuple(X x, Y y) { 
        this.x = x; 
        this.y = y; 
    }

    public X getFirst() {
        return this.x;
    }

    public Y getSecond() {
        return this.y;
    }

    public void setFirst(X x) {
        this.x = x;
    }
    public void setSecond(Y y) {
        this.y = y;
    }
}

class Node {
    Tuple<Integer, Integer> data;
    Node next;

    public Node(Tuple<Integer, Integer> newData) {
        this.data = newData;
        this.next = null;
    }

    public boolean equals(Node other) {
        return this.data.getFirst() == other.data.getFirst() && this.data.getSecond() == other.data.getSecond();
    }
}

public class Snake {

    private Node head;
    private char direction;
    private int numOfParts;
    

    public Snake() {
        // (Grid.GRID_WIDTH / Grid.CELL_SIZE) / 2, (Grid.GRID_HEIGHT / Grid.CELL_SIZE) / 2)
        Tuple<Integer, Integer> startingPoint = new Tuple<>(0, 0);
        head = new Node(startingPoint);

        direction = 0;
        numOfParts = 1;
    }

    public void insertAtStart(Tuple<Integer, Integer> data) {
        Node node = new Node(data);
        Node temp = this.head;
        node.next = temp;
        this.head = node;

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

    public void insertAtEnd(Tuple<Integer, Integer> d) {
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

    public Node getTail() {
        Node n = head;
        while (n.next != null) {
            n = n.next;
        }
        return n;
    }

    public ArrayList<Node> getBody() {
        ArrayList<Node> arr = new ArrayList<>();
        Node h = this.head;
        while (h.next != null) {
            arr.add(h);
            h = h.next;
        }
        // adding last element
        arr.add(h);
        // removing head from array
        arr.remove(0);
        return arr;
    }

    public ArrayList<Node> getBodyWithHead() {
        ArrayList<Node> arr = new ArrayList<>();
        Node h = this.head;
        while (h.next != null) {
            arr.add(h);
            h = h.next;
        }
        // adding last element
        arr.add(h);
        return arr;
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

    public void printSnake() {
        Node h = this.head;
        while (h.next != null) {
            Tuple<Integer, Integer> c = h.data;
            int x = c.getFirst();
            int y = c.getSecond();
            System.out.print(x + "," + y + " -> ");
            h = h.next;
        }
        Tuple<Integer, Integer> c = h.data;
        int x = c.getFirst();
        int y = c.getSecond();
        System.out.println(x + "," + y);
    }

}
