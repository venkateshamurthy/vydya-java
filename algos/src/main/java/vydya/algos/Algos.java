/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package vydya.algos;

import java.util.Arrays;

/**
 *
 * @author venkatm
 */
public class Algos {

    public static void main(String[] args) {
        System.out.format("Running some algorithm problems..%s\n",
                args.length > 0 && args[0].toLowerCase().startsWith("auto")
                        ? "automatically" 
                        : "requiring to  manually enter size of the array");
        
        
        SelectionSort.main(args);
        MergeSort.main(args);
        QuickSort.main(args);
        LinearSearch.main(args);
        BinarySearch.main(args);
        MinMax.main(args);
        Fibonacci.main(args);
        NCR.main(args);
        Dijkstra.main(args);
    }
}
