import java.util.Scanner;

/**
 *
 * @author Cole
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int days = sc.nextInt();
        sc.nextLine();
        int[] t1Scores = new int[days];
        int[] t2Scores = new int[days];
        int maxEqualScores = 0;

        for (int i = 0; i < days; i++) {
            t1Scores[i] = sc.nextInt();
        }

        sc.nextLine();

        for (int i = 0; i < days; i++) {
            t2Scores[i] = sc.nextInt();
        }

        int t1Sum = 0;
        int t2Sum = 0;

        for (int i = 0; i < days; i++) {
            t1Sum += t1Scores[i];
            t2Sum += t2Scores[i];
            if (t1Sum == t2Sum) {
                maxEqualScores = i + 1;
            }
        }
            System.out.println(maxEqualScores);
        

    }

}
