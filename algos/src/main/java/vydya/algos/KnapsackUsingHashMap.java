package vydya.algos;
import static java.lang.Math.max;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Supplier;

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
public class KnapsackUsingHashMap {
    private static class Key {
        private final int i, c;
        private Key (int i, int c)    { this.i = i; this.c = c; }
        public static Key key(int i, int c){return new Key(i, c);}
        public int hashCode() { return Objects.hash(i, c);}
        public boolean equals(Object o){Key k = (Key)o; return k.i==i && k.c==c;}
        public String toString() { return String.format("[%d, %d]", i, c);}
    }
    
    static final  Random rand = new Random(10L);
    
    final int[] v;// =  {6, 4, 5, 3, 9, 7};
    final int[] w;//  = {4, 2, 3, 1, 6, 4};
    final int   W;
    
    final Map<Key, Integer> K;// = new int[n + 1][W + 1];
    final int n;
    
    public KnapsackUsingHashMap(int[] v, int[] w, int W) {
        if (v.length != w.length) {
            System.err.println(" Values and weights dont match in sizes");
            System.exit(-1);
        }
        
        this.v = v;
        this.w = w;
        this.W = W;
        this.n = v.length;
        this.K = new LinkedHashMap<>();
        put(0, 0, 0);
        
        System.out.format("\nvalues :%s\nweights: %s\ncapacity:%d",
                Arrays.toString(v), Arrays.toString(w), W);
        
    }
            
    int  get(int i, int c)          { return K.getOrDefault(Key.key(i,c), 0);}
    void put(int i, int c, int val) { K.put(Key.key(i,c), val);   }
    
    public int compute() {
        
        Deque<Integer> take = new ArrayDeque<>();

        for (int i = 1; i < n; i++) {
            
            for (int c = 1; c <= W; c++) {
                int val = get(i - 1, c);
                
                if (w[i] <= c)
                    val = max(v[i] + get(i - 1, c - w[i]), val);
                
                put(i , c, val);
            }
        }
        
        for (int i = n - 1, c = W; i > 0 && c > 0; i--) {
            
            if (get(i, c) != get(i - 1, c)) {
                
                take.addFirst(w[i]); 
                
                c -= w[i];
            }
        }
        
        System.out.println("\n Matrix:"+printKnapsack());
        System.out.println(" Items:"+take);
        
        return get(n - 1, W);

    }

    private  String printKnapsack() {
        //print only such entries where the value changes
        Set<Integer> set  = new HashSet<>();
        return  K.entrySet().stream()
                .filter(e -> set.add(e.getValue()))
                .map(e -> String.format("%12s,", e))
                .reduce("", String::concat);
    }
     
    static KnapsackUsingHashMap build(Supplier<Integer> size,
            Supplier<Integer> items, Supplier<Integer> capacity) {
        final int noOfItems, W;
        final int[] v, w;
        
        System.out.println("Enter number of items: ");
        noOfItems = size.get();
        v = new int[noOfItems + 1];
        w = new int[noOfItems + 1];

        System.out.println("Enter values and weights of items: ");
        for (int i = 1; i <= noOfItems; i++) {
            v[i] = items.get();
            w[i] = items.get();
        }
        
        System.out.print("Enter knapsack capacity: ");
        W = capacity.get();

        return new KnapsackUsingHashMap(v, w, W);
    }

    public static void main(String[] args) {
        System.out.println("\nRunning Knapsack using Hash Map...");
        KnapsackUsingHashMap knapsack;
        
        if (args.length > 0 && args[0].startsWith("auto")) {
            System.out.println("Choosing Random number of items, their values and weights");
            knapsack = build(
                    () -> rand.nextInt(10, 20), 
                    () -> rand.nextInt(10, 100),
                    () -> rand.nextInt(100, 200)
            );
        } else {
            /**
             * This option requires user input. An example to use
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
            knapsack = build(scanner::nextInt, scanner::nextInt, scanner::nextInt);
        }
        System.out.format(" For a knapsack that can hold upto %d weight;"
                + " max profit = %s\n", knapsack.W, knapsack.compute());
    }
}