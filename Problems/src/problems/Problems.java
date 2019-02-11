package problems;

import java.io.*;

public class Problems {

    public static void main(String[] args) {

        try (PrintStream save = new PrintStream(new File("C:\\Users\\Cole\\Documents\\NetBeansProjects\\TopDownShooter\\save.txt"))) {

            save.println("Hi");
            save.close();

        } catch (FileNotFoundException e) {
            System.out.println("Can't find that, sorry.");
        }
    }
}
