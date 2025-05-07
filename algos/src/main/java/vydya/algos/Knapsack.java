package vydya.algos;
import static java.lang.Math.max;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Random;
import java.util.Scanner;

/**
 * Eg: For max weight of 10 (2+ 3 + 1 + 4) we get max value 19 (4 + 5 + 3 + 7)
 * <p>
 * <pre>
 * 	static int[] v = new int[] { 6, 4, 5, 3, 9, 7 };
 * 	static int[] w = new int[] { 4, 2, 3, 1, 6, 4 };
 * Item	0	1	2	3	4	5	6	7	8	9	10
 *    --------------------------------------------------------------------------------------
 *    0	0	0	0	0	6	6	6	6	6	6	6
 *    --------------------------------------------------------------------------------------
 *    1	0	0	4	4	6	6	10	10	10	10	10
 *    --------------------------------------------------------------------------------------
 *    2	0	0	4	5	6	9	10	11	11	15	15
 *    --------------------------------------------------------------------------------------
 *    3	0	3	4	7	8	9	12	13	14	15	18
 *    --------------------------------------------------------------------------------------
 *    4	0	3	4	7	8	9	12	13	14	16	18
 *    --------------------------------------------------------------------------------------
 *    5	0	3	4	7	8	10	12	14	15	16	19
 *    --------------------------------------------------------------------------------------
 * </pre>
 *
 * @author vydya
 */
public class Knapsack {
    private static final  Random rand = new Random(10L);
    
    final int[]   v;// =  {6, 4, 5, 3, 9, 7};
    final int[]   w;//  = {4, 2, 3, 1, 6, 4};
    final int     W;
    final int     n;
    final int[][] K;// = new int[n][W + 1];
    
    
    public Knapsack(int[] v, int[] w, int W) {
        if (v.length != w.length) {
            System.err.println(" Values and weights dont match in sizes");
            System.exit(-1);
        }
        
        this.v = v;
        this.w = w;
        this.W = W;
        this.n = v.length;
        this.K = new int[n][W + 1];
        
        System.out.println("v:"+Arrays.toString(v));
        System.out.println("w:"+Arrays.toString(w));
    }
            
    int  get(int i, int c)          { return K[i][c]; }
    void put(int i, int c, int val) { K[i][c] = val;  }
    
    public int compute() {
        
        Deque<Integer> take = new ArrayDeque<>();

        for (int i = 1; i < n; i++) {
            
            for (int c = 1; c <= W; c++) {
                int val;
                
                if (w[i] <= c)
                    val = max(v[i] + get(i - 1, c - w[i]), get(i - 1, c));
                else
                    val = get(i - 1, c);
                
                put(i , c, val);
            }
        }
        
        for (int i = n - 1, c = W; i > 0 && c > 0; i--) {
            
            if (get(i, c) != get(i - 1, c)) {
                
                take.addFirst(w[i]); 
                
                c -= w[i];
            }
        }
        
        System.out.println("\nMatrix:"+printMatrix());
        System.out.println("Items:"+take);
        
        return get(n - 1, W);

    }

    /**
     * Print the Knapsack
     * @return 
     */
    private  String printMatrix() {
        StringBuilder sb = new StringBuilder("\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= W; j++) {
                sb.append(String.format(" %03d ", get(i, j)));
            }
            sb.append("\n\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("\nRunning Knapsack...");
        final int   noOfItems;
        final int[] v;
        final int[] w;
        final int   W;
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter the no of items:"); // 6
        noOfItems = scanner.nextInt();                
        v = new int[noOfItems + 1];
        w = new int[noOfItems + 1];
        
        System.out.println("Enter values and weights:");
        for (int i = 1; i <= noOfItems; i++) {        // 6 4 4 2 5 3 3 1 9 6 7 4
            v[i] = scanner.nextInt();
            w[i] = scanner.nextInt();
        }
        System.out.print("\nEnter capacity of the knapsack:");
        W = scanner.nextInt();                        // 10
        
        Knapsack knapsack = new Knapsack(v, w, W);
        System.out.format(" For a knapsack that can hold upto %d weight;"
                + " max profit = %s\n", W, knapsack.compute());
    }
}