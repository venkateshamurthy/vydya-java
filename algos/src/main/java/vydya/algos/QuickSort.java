/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vydya.algos;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author vydya
 */
public class QuickSort {
    static final  Random rand = new Random(10L);
    
    private final int[] data;
    
    //Constructor is needed to populate the final field data
    public QuickSort(int[] input) {
        data = input;
    }
    
    int[] sort() {
        sort(0, data.length - 1);
        return data;
    }
    
    /**
     * Recursive sort method
     * @param start
     * @param end 
     */
    public void sort(int start, int end) {
        if (start < end) {
            int partitionIndex = partition(start, end);
            sort(start, partitionIndex - 1);
            sort(partitionIndex + 1,   end);
        }
    }
    
    /**
     * Partition method to initially go with a start pivot but however with 
     * partitioning done with a near and far pointer approach we get the partition
     * @param pivot the initial pivot (always to be at start index)
     * @param end the  last index of the array
     * @return the partition index 
     */
    public int partition (final int pivot, final int end) {
        // pivot                                     end
        // 0      1     2    3    4    5    6    7    8
        // 5      1     8    3    9    4    2    7    6
        //       near-->                          <--far
        int near = pivot + 1, far = end;
        
        // increment near and decrement far pointers 
        while (near <= far) {
            if      (data[near]<  data[pivot]) near++;//if near element < pivot
            else if (data[far] >= data[pivot]) far--; //if far element >= pivot
            else swap(near++, far--);      //swap near and far
        }
       
        swap(pivot, far);                  //swap far with pivot
        return far;
    }
    
    
    public static void main(String[] args) {
        System.out.println("\nRuning Quick Sort...");
        Scanner scanner = new Scanner(System.in) ;
        
        System.out.print("Enter the size of Integer array:"); 
        int[] input = createRandomArray(scanner.nextInt());
        int[] result = new QuickSort(input).sort();
        System.out.format("Sorted Output :%s\n", Arrays.toString(result));
    }
    
    public static int[] createRandomArray(int size) {
        int[] input = new int[size];
        for (int i = 0; i < input.length; i++) input[i] = rand.nextInt(100, 1000);
        System.out.format("Unsorted Input:%s\n" , Arrays.toString(input));
        return input;
    }
    
     void swap(int a, int b) {
        int temp = data[a];
        data[a] = data[b];
        data[b] = temp;
    }
}
