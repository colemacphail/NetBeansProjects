/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arithmeticsquare;

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
        int[] row0 = new int[2];
        int[] row1 = new int[2];
        int[] row2 = new int[2];
        int[] col0;
        int[] col1;
        int[] col2;

        for (int i = 0; i < row0.length; i++) {
            if (sc.next().charAt(0) != 'X') {
                row0[i] = sc.nextInt();
            }
        }
        for (int i = 0; i < row1.length; i++) {
            if (sc.next().charAt(0) != 'X') {
                row1[i] = sc.nextInt();
            }
        }
        for (int i = 0; i < row2.length; i++) {
            if (sc.next().charAt(0) != 'X') {
                row2[i] = sc.nextInt();
            }
        }
        col0 = new int[]{row0[0], row1[0], row2[0]};
        col1 = new int[]{row0[1], row1[1], row2[1]};
        col2 = new int[]{row0[2], row1[2], row2[2]};

        
    }

}
