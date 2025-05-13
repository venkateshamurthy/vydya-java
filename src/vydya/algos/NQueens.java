package vydya.algos;

import java.util.Scanner;

import static java.lang.Math.abs;

public class NQueens {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("This is NQueens problem. So enter number of queens:");
        int n = scanner.nextInt();
        int[] queens = new int[n];
        if (placeQueens(queens, 0)) {
            printBoard(queens);
        } else {
            System.out.println("No solution found.");
        }
        scanner.close();
    }

    private static boolean placeQueens(int[] queens, int queenNum) {
        int n = queens.length;
        if (queenNum == n) {
            return true;                                // All queens are placed successfully
        }
        for (int col = 0; col < n; col++) {
            if (isSafe(queens, queenNum, col)) {
                queens[queenNum] = col;                       // Good, queen is at (row, col)
                if (placeQueens(queens, queenNum + 1)) { // Now try to place next queen
                    return true;
                }
                queens[queenNum] = -1;                        // Oh! Backtrack: Remove queen from this column
            }
        }
        return false;                                  // No safe position found in this row
    }

    private static boolean isSafe(int[] board, int row, int col) {
        for (int r = 0; r < row; r++) {
            int c = board[r];
            if ( c == col || abs(c - col) == abs(r - row)) {
                return false; // Attacked by another queen
            }
        }
        return true;          // Safe position as no one is attacking
    }

    private static void printBoard(int[] board) {
        int n = board.length;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (board[row] == col) {
                    System.out.print("Q  ");
                } else {
                    System.out.print(".  ");
                }
            }
            System.out.println();
        }
    }
}