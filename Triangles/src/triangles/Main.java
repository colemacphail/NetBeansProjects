/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package triangles;

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
    static int getMaxVal(ArrayList<Integer> vals) {
        int max = 0;
        for (int i = 0; i < vals.size(); i++) {
            if (vals.get(i) > max) {
                max = vals.get(i);
            }
        }
        return max;
    }

    static int getTriangleSize(int size) {
        if (size == 1) {
            return 1;
        } else {
            return size + getTriangleSize(size - 1);
        }
    }

    int getTriangleVal(int[][] triangle, int index) {

        int currIndex = 0;

        for (int i = 0; i < triangle.length; i++) {
            for (int j = 0; j < triangle[0].length; j++) {
                if (triangle[i][j] >= 0) {
                    currIndex++;
                    if (currIndex == index) {
                        return triangle[i][j];
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int size = sc.nextInt() + 1;
        int subSize = sc.nextInt();
        int[][] triangle = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < i; j++) {
                triangle[i][j] = -1;
            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < i; j++) {
                triangle[i][j] = sc.nextInt();
            }
        }

        int totalMax = 0;

        for (int i = 0; i < size - (subSize - 1); i++) {//each subtriangle
            ArrayList<Integer> subTriangle = new ArrayList<>();
            int[] triangleVals = new int[getTriangleSize(subSize)];
            for (int j = 0; j < getTriangleSize(subSize); j++) {//each space within subtriangle
                triangleVals[j] = 
            }
        }

    }

}
