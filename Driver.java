/**
 * Driver
 */

 import java.util.ArrayList;

public class Driver {

    public static void main(String[] args) {
        new GameWindow();

        // int[][] grid = {    {1, 2, 3}, 
        //                     {4, 5, 6}, 
        //                     {7, 8, 9}   };
        
        // int rows = grid.length;
        // int cols = grid[0].length;

        // int r = 0;
        // int c = 0;

        // ArrayList<Integer> x = new ArrayList<>();

        // int count = 0;
        // while (count < 4) {
        //     System.out.println("iteration " + count);
        //     if (r == 0 && c == 0) {
        //         System.out.println("reached 0, 0");
        //         while (c < cols) {
        //             x.add(grid[r][c]);
        //             System.out.println("Added " + grid[r][c] + " to x");
        //             c++;
        //         }
        //         c--;
        //     }
        //     else if (r == rows-1 && c == 0) {
        //         System.out.println("reached rows-1, 0");
        //         while (r >= 0) {
        //             x.add(grid[r][c]);
        //             System.out.println("Added " + grid[r][c] + " to x");
        //             r--;
        //         }
        //         r++;
        //     }
        //     else if (r == 0 && c == cols-1) {
        //         System.out.println("reached 0, cols-1");
        //         while (r < rows) {
        //             x.add(grid[r][c]);
        //             System.out.println("Added " + grid[r][c] + " to x");
        //             r++;
        //         }
        //         r--;
        //     }
        //     else if (r == rows-1 && c == cols-1) {
        //         System.out.println("reached rows-1, cols-1");
        //         while (c >= 0) {
        //             x.add(grid[r][c]);
        //             System.out.println("Added " + grid[r][c] + " to x");
        //             c--;
        //         }
        //         c++;
        //     }
        //     count++;
        // }

        // ArrayList<Integer> newX = new ArrayList<>();
        // for (Integer i : x) {
        //     if (!newX.contains(i)) {
        //         newX.add(i);
        //     }
        // }
        // System.out.println(newX);








        // TEST
        // for (int i = 0; i < grid.length; i++) {
        //     for (int j = 0; j < grid[0].length; j++) {
        //         System.out.print(grid[i][j] + " ");
        //     }
        //     System.out.println();
        // }

        // System.out.println("rows: " + rows);
        // System.out.println("cols: " + cols);

    }
}