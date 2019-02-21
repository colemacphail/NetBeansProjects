package absolutelyacidic;

import java.util.ArrayList;
import java.util.List;
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

        int numSensors = sc.nextInt();
        int[] numOfReadings = new int[1000];

        for (int i = 0; i < numSensors; i++) {
            numOfReadings[sc.nextInt()]++;
        }

        ArrayList<Integer> mostFrequentVal = new ArrayList<>();
        ArrayList<Integer> mostFrequentValCount = new ArrayList<>();
    }
}
