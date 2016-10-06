/*
 * Program to randomly fill in 0s and 1s into an n-by-n matrix, print the matrix, 
 * and find the rows and columns with the most 1s. 
 */
package largestrowcolumn;

/**
 *
 * @author Denis Stepanov, N01077691
 * @date   October 4th, 2016
 */
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class LargestRowColumn {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the array size n: ");
        int n = scan.nextInt();
        ArrayList<Integer> rowList = new ArrayList<Integer>();
        ArrayList<Integer> colList = new ArrayList<Integer>();

        int a[][] = new int[n][n];
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = r.nextInt(2);
            }
        }
        System.out.println("Print the array row by row: ");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
        int maxRow = 0;
        int maxCol = 0;
        int maxRowOnes = 0;
        int maxColOnes = 0;
        for (int i = 0; i < n; i++) {
            maxRowOnes = 0;
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 1) {
                    maxRowOnes++;
                }
            }
            if (maxRow < maxRowOnes) {
                maxRow = maxRowOnes;
            }
        }
        for (int i = 0; i < n; i++) {
            maxRowOnes = 0;
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 1) {
                    maxRowOnes++;
                }
            }
            if (maxRow == maxRowOnes) {
                rowList.add(i);
            }
        }
        for (int i = 0; i < n; i++) {
            maxColOnes = 0;
            for (int j = 0; j < n; j++) {
                if (a[j][i] == 1) {
                    maxColOnes++;
                }
            }
            if (maxCol < maxColOnes) {
                maxCol = maxColOnes;
            }

        }
        for (int i = 0; i < n; i++) {
            maxColOnes = 0;
            for (int j = 0; j < n; j++) {
                if (a[j][i] == 1) {
                    maxColOnes++;
                }
            }
            if (maxCol == maxColOnes) {
                colList.add(i);
            }

        }
        System.out.println("Row(s) with the most 1's: " + rowList);
        System.out.println("Column(s) with most 1's: " + colList);
    }
}
