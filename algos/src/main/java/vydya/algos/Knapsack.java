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
    static final  Random rand = new Random(10L);
    
    final int[] v;// =  {6, 4, 5, 3, 9, 7};
    final int[] w;//  = {4, 2, 3, 1, 6, 4};
    final int   W;
    
    final int[][] K;// = new int[n + 1][W + 1];
    final int n;
    
    public Knapsack(int[] v, int[] w, int W) {
        if (v.length != w.length) {
            System.err.println(" Values and weights dont match in sizes");
            System.exit(-1);
        }
        
        this.v = v;
        this.w = w;
        this.W = W;
        
        n = v.length;
        K = new int[n + 1][W + 1];
        
        System.out.println("v:"+Arrays.toString(v));
        System.out.println("w:"+Arrays.toString(w));
    }
            
    
    
    public int compute() {
        
        Deque<Integer> take = new ArrayDeque<>();

        for (int i = 1; i <= n; i++) {
            
            for (int wi = 1; wi <= W; wi++) {
                
                K[i][wi] = K[i - 1][wi];
                if (w[i - 1] <= wi)
                    K[i][wi] = max(v[i - 1] + K[i - 1][wi - w[i - 1]],
                                   K[i][wi]);
            }
        }
        
        for (int i = n, wi = W; i > 0 && wi > 0; i--) {
            
            if (K[i][wi] != K[i - 1][wi]) {
                
                take.addFirst(w[i - 1]); // add that wt[i-1]
                
                wi -= w[i - 1];          // next reduce the weight
            }
        }
        System.out.println("\n Matrix:"+printMatrix());
        System.out.println(" Items:"+take);
        return K[w.length][W];

    }

    /**
     * Print the Knapsack
     * @return 
     */
    private  String printMatrix() {
        StringBuilder sb = new StringBuilder("\n");
        for (int i = 0; i < K.length; i++) {
            for (int j = 0; j < K[0].length; j++) {
                sb.append(String.format(" %03d ", K[i][j]));
            }
            sb.append("\n\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("\nRunning Knapsack...");
        final int noOfItems;
        final int[] v;
        final int[] w;
        final int   W;
        
        // If auto is passed to program all elements are chosen in random
        if (args.length > 0 && args[0].startsWith("auto")) {
            
            System.out.println("Choosing Random number of items, their values and weights");
            noOfItems = rand.nextInt(6, 10);
            v = new int[noOfItems];
            w = new int[noOfItems];
            
            for (int i = 0; i< noOfItems; i++) {
                v[i] = rand.nextInt(10, 100);
                w[i] = rand.nextInt(1, 10); // this makes matrix bigger so be cautious
            }
            
            W = rand.nextInt(15, 20);
            
        } else {
/**
 * This option requires user input
 * An example to use
Enter the no of items:
6
Enter values and weights:
 6 4
 4 2
 5 3
 3 1
 9 6
 7 4
Enter capacity of the knapsack:
10
* Result value should be 19
 */
            Scanner scanner = new Scanner(System.in);
            
            System.out.print("\nEnter the no of items:");
            noOfItems = scanner.nextInt();
            v = new int[noOfItems];
            w = new int[noOfItems];
            
            System.out.println("Enter values and weights:");
            for (int i = 0; i< noOfItems; i++) {
                v[i] = scanner.nextInt();
                w[i] = scanner.nextInt();
            }
            
            System.out.print("\nEnter capacity of the knapsack:");
            W = scanner.nextInt();
        }
        Knapsack knapsack = new Knapsack(v, w, W);
        System.out.format(" For a knapsack that can hold upto %d weight;"
                + " max profit = %s\n", W, knapsack.compute());
    }
}