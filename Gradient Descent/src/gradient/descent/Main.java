
import java.util.Scanner;

public class Main {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int row = sc.nextInt();
        int col = sc.nextInt();
        int numQuestions = sc.nextInt();

        int lowestScore;

        System.out.println("? 1 0");
        System.out.flush();
        int response = sc.nextInt();

        lowestScore = response;

        for (int i = 0; i < numQuestions && i < col; i++) {

            System.out.println("? 1 0");
            System.out.flush();
            response = sc.nextInt();

            if (response < lowestScore) {
                lowestScore = response;
            }
        }

        System.out.println("! " + lowestScore);

    }

}
