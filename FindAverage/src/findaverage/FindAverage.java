package findaverage;

import java.util.*;

public class FindAverage {

    public static void main(String[] args) {

        Scanner userIn = new Scanner(System.in);

        double userNum = 0;
        long numOfNums = 0;
        boolean findAverage = false;

        System.out.println("Hi there!");
        System.out.println("Welcome to the average machine!");

        while (true) {

            System.out.println("Put in whatever numbers you want, then say 'okay'");
            System.out.println("when you want the average!");

            do {
                String reply = userIn.nextLine();
                if (reply.equalsIgnoreCase("okay")) {
                    findAverage = true;
                } else {
                    double number = Double.parseDouble(reply);
                    userNum += number;
                    numOfNums++;
                }
            } while (findAverage == false);
            System.out.println(userNum / numOfNums + " is your average!");
            System.out.println("Would you like me to find another average?");
            System.out.println("[YES]  [NO]");
            while (true) {
                String reply = userIn.nextLine();
                if (reply.equalsIgnoreCase("no")) {
                    System.out.println("Okay! Have a nice day!");
                    System.exit(0);
                } else if (reply.equalsIgnoreCase("yes")) {
                    numOfNums = 0;
                    userNum = 0;
                    findAverage = false;
                    break;
                } else {
                    System.out.println("I'm sorry, I didn't get that.");
                }
            }
        }
    }
}
