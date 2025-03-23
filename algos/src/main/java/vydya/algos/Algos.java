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
        final String[] EMPTY=args;//new String[]{"auto"};
        System.out.println("Running some algorithm problems.."
                +Arrays.toString(EMPTY));
        
        
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
