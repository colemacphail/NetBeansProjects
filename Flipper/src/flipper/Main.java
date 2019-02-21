import java.util.Scanner;

/**
 *
 * @author Cole
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static int[][] horFlip(int[][] grid) {
        int t0 = grid[0][0];
        int t1 = grid[1][0];
        grid[0][0] = grid[0][1];
        grid[1][0] = grid[1][1];
        grid[0][1] = t0;
        grid[1][1] = t1;

        return grid;
    }
    public static int[][] vertFlip(int[][] grid) {
        int t0 = grid[0][0];
        int t1 = grid[0][1];
        grid[0][0] = grid[1][0];
        grid[0][1] = grid[1][1];
        grid[1][0] = t0;
        grid[1][1] = t1;

        return grid;
    }
    

    public static void main(String[] args) {
        int[][] grid = new int[2][2];
        grid[0][0] = 1;
        grid[1][0] = 2;
        grid[0][1] = 3;
        grid[1][1] = 4;

        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == 'H') {
                grid = horFlip(grid);
            } else if (input.charAt(i) == 'V') {
                grid = vertFlip(grid);
            }
        }
        
        System.out.println(grid[0][0] + " " + grid[1][0]);
        System.out.println(grid[0][1] + " " + grid[1][1]);
    }

}
