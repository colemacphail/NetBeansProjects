package searchingasmt;

import java.util.Scanner;

/**
 *
 * @author diramim
 */
public class Searches {

    public enum SearchMethods {
        LINEAR, BINARY
    };

    public void run() {
        Scanner sc = new Scanner(System.in);
        SearchMethods method = SearchMethods.LINEAR;

        System.out.println("Options: [L]inear [B]inary [N]ew Array [O]ptions [Q]uit");

        int[] vals = new int[(int) (Math.random() * 10) + 5];

        vals[0] = (int) (Math.random() * 10) + 10;
        for (int i = 1; i < vals.length; i++) {
            vals[i] = vals[i - 1] + (int) (Math.random() * 5) + 1;
        }

        while (true) {
            System.out.println();
            for (int i = 0; i < vals.length; i++) {
                System.out.print("[" + vals[i] + "]");
            }
            System.out.println();

            System.out.print("Number to search for (or option): ");
            String input = sc.nextLine();

            try {
                int num = Integer.parseInt(input);

                int index;
                if (method == SearchMethods.LINEAR) {
                    index = linearSearch(vals, num);
                } else {
                    index = binarySearch(vals, num);
                }
                if (index >= 0) {
                    System.out.println("Found: Slot #" + index);
                } else if (index == -1) {
                    System.out.println("NOT FOUND");
                }

            } catch (NumberFormatException e) {
                input = input.toUpperCase();
                if (input.startsWith("L")) {
                    System.out.println("Switched to linear search");
                    method = SearchMethods.LINEAR;
                } else if (input.startsWith("B")) {
                    System.out.println("Switched to binary search");
                    method = SearchMethods.BINARY;
                } else if (input.startsWith("O")) {
                    System.out.println("Options: [L]inear [B]inary [N]ew Array [O]ptions [Q]uit");
                } else if (input.startsWith("N")) {
                    System.out.println("New array");
                    // new array
                    vals = new int[(int) (Math.random() * 10) + 5];

                    vals[0] = (int) (Math.random() * 10) + 10;
                    for (int i = 1; i < vals.length; i++) {
                        vals[i] = vals[i - 1] + (int) (Math.random() * 5) + 1;
                    }
                } else if (input.startsWith("Q")) {
                    System.exit(0);
                }
            }

        }

    }

    public int linearSearch(int[] arr, int x) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == x) {
                return i;
            }
        }
        return -1;
    }

    public int binarySearch(int[] arr, int x) {
        int low = 0;
        int high = arr.length;
        for (int i = 0; i < arr.length; i++) {
            int index = (high + low) / 2;
            if (arr[index] == x) {
                return index;
            } else {
                if (arr[index] > x) {
                    high = index;
                } else {
                    low = index;
                }
            }
        }
        return -1;
    }
}
