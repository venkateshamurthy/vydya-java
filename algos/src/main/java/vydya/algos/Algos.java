/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package vydya.algos;

import java.util.Arrays;
import java.util.Deque;
import java.util.Random;

/**
 *
 * @author venkatm
 */
public class Algos {

    public static void main(String[] args) {
        Random rand = new Random(10L);
        
        boolean isAuto=args.length > 0 && args[0].toLowerCase().startsWith("auto");
        
        System.out.format("Running some algorithm problems..%s\n",
                           isAuto
                        ? "automatically" 
                        : "requiring to  manually enter size of the array");
        
        if (isAuto) {
            int[] input = null, result = null;
            // 1. NCR
            System.out.println("\nRunning NCR..");
            int n = rand.nextInt(8, 12);
            int r = rand.nextInt(5, 8);
            NCR ncr = new NCR();
            System.out.format("n=%d, r=%d nCr=%d%n", n, r, ncr.ncr(n, r));
            
            // 2. MinMax
            System.out.println("\nRuning MinMax...");
            input = MinMax.createRandomArray(rand.nextInt(10, 20));
            result = new MinMax(input).findMinMax();
            System.out.println("Min:"+result[0]+"  Max:"+result[1]);
            
            // 3. Fibonacci
            System.out.println("\nRunning Fibonacci..");
            int f = rand.nextInt(10,20);
            Fibonacci fib = new Fibonacci();
            System.out.println("n:"+n+"   Result: "+fib.fibonacci(f));
            
            // 4. Selection Sort
            System.out.println("\nRuning Selection Sort...");
            input = SelectionSort.createRandomArray(rand.nextInt(10, 20));
            result = new SelectionSort(input).sort();
            System.out.format("Selection sorted Output :%s\n", Arrays.toString(result));
            
            // 5. Quick Sort
            System.out.println("\nRuning Quick Sort...");
            input = QuickSort.createRandomArray(rand.nextInt(10, 20));
            result = new QuickSort(input).sort();
            System.out.format("Quick sorted Output :%s\n", Arrays.toString(result));
            
            // 6. Merge Sort
            System.out.println("\nRuning Merge Sort...");
            input = MergeSort.createRandomArray(rand.nextInt(10, 20));
            result = new MergeSort(input).sort();
            System.out.format("Merge sorted Output :%s\n", Arrays.toString(result));
            
            // 7. Linear Search
            System.out.println("\n\nRunning Linear Search without sorting...");
            input = LinearSearch.createRandomArray(rand.nextInt(10, 20));
            LinearSearch linearSearcher = new LinearSearch(input);
            linearSearcher.search(788);  //present
            linearSearcher.search(-100); //absent
            
            // 8. Binary Search
            System.out.println("\n\nRunning Binary Search after sorting...");
            input = BinarySearch.createRandomArray(rand.nextInt(10, 20));
            result = new MergeSort(input).sort();
            System.out.format("Sorted Output for Binary Search :%s\n",Arrays.toString(result));
            BinarySearch binarySearcher = new BinarySearch(result);
            binarySearcher.search(788);  //present
            binarySearcher.search(-100); //absent
            
            // 9. KnapSack using 2D array
            System.out.println("\nRunning Knapsack...");
            final int   noOfItems;
            final int[] v;
            final int[] w;
            final int   W;

            noOfItems = rand.nextInt(10, 20);                
            v = new int[noOfItems + 1];
            w = new int[noOfItems + 1];

            System.out.println("Enter values and weights:");
            for (int i = 1; i <= noOfItems; i++) {        // 6 4 4 2 5 3 3 1 9 6 7 4
                v[i] = rand.nextInt(10, 200);
                w[i] = rand.nextInt(10, 100);
            }
            System.out.print("\nEnter capacity of the knapsack:");
            W = rand.nextInt(10, 750);                    // 10

            Knapsack knapsack = new Knapsack(v, w, W);
            System.out.format(" For a knapsack that can hold upto %d weight;"
                + " max profit = %s\n", W, knapsack.compute());
            
            // 10. KnapSack using HashMap
            System.out.println("\nRunning Knapsack...using Hash Map");
            System.out.println("Choosing Random number of items, their values and weights");
            KnapsackUsingHashMap knapsackWithMap = KnapsackUsingHashMap
                    .build(
                        () -> rand.nextInt(10, 20), 
                        () -> rand.nextInt(10, 100),
                        () -> rand.nextInt(100, 200)
                    );
            System.out.format(" For a knapsack that can hold upto %d weight;"
                + " max profit = %s\n", knapsackWithMap.W, knapsackWithMap.compute());
            
            //11. Dijkstra
            System.out.println("\n\nComputing Dijkstra....\n");
            int size = rand.nextInt(10, 20);
            System.out.println("Assuming a random size(10, 20) of 2D array to build the graph :"+size);
            Dijkstra dijk = new Dijkstra(size);
            int end = rand.nextInt(0, size);
            Deque<V> path = dijk.computePaths(0, end); // run Dijkstra
            System.out.println("\nDistance from "+0+" to "+end+" = "+ dijk.vertex(end).d);
            System.out.format("%s\n", path);
            
        } else {
            SelectionSort.main(args);
            MergeSort.main(args);
            QuickSort.main(args);
            LinearSearch.main(args);
            BinarySearch.main(args);
            MinMax.main(args);
            Fibonacci.main(args);
            NCR.main(args);
            Dijkstra.main(args);
            Knapsack.main(args);
            KnapsackUsingHashMap.main(args);
        }
    }
}
