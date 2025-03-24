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
        final String[] EMPTY=args;
        System.out.format("Running some algorithm problems..%s\n",
                args.length > 0 && args[0].toLowerCase().startsWith("auto")
                        ? "automatically" 
                        : "requiring to  manually enter size of the array");
        
        
        SelectionSort.main(EMPTY);
        MergeSort.main(EMPTY);
        QuickSort.main(EMPTY);
        LinearSearch.main(EMPTY);
        BinarySearch.main(EMPTY);
        MinMax.main(EMPTY);
        Fibonacci.main(EMPTY);
        NCR.main(EMPTY);
    }
}
