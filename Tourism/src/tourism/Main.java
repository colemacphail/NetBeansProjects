package tourism;



import java.util.ArrayList;
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

        double numOfAttractions = sc.nextInt();
        double attractionsPerDay = sc.nextInt();

        ArrayList<Integer> attractions = new ArrayList<>();

        for (int i = 0; i < numOfAttractions; i++) {
            attractions.add(sc.nextInt());
        }

        int[][] schedule = new int[(int) Math.ceil(numOfAttractions / attractionsPerDay)][(int) attractionsPerDay]; // [how many days][which attractions that day]
        attractions.sort(null);

        int attractionsLeft = attractions.size() - 1;
        for (int i = 0; i < schedule.length; i++) {
            for (int j = 0; j < schedule[0].length; j++) {
                try{
                schedule[j][i] = attractions.get(attractionsLeft);
                } catch (Exception e){
                }
                if (attractionsLeft > 0) {
                    attractionsLeft--;
                }
            }
        }
        int total = 0;
        for (int i = 0; i < schedule.length; i++) {
            total += schedule[i][0];
        }

        System.out.println(total);

    }

}
