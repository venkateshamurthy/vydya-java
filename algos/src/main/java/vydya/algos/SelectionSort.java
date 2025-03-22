/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vydya.algos;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author vydya
 */
public class SelectionSort {
    static final  Random rand = new Random(10L);
    final int[] data;

    public SelectionSort(int[] input) {
        data = input;
    }
    
    
    int[] sort() {
        sort(0, data.length - 1);
        return data;
    }
   
    public void sort(int start, int end) {
        for (int i = start; i< end - 1; i++){
            int minIndex = i;
            for (int j = i + 1; j<=end; j++){
                if (data[j] < data[minIndex]) {
                    minIndex = j;
                }
            }
            swap(i, minIndex);
        }
    }
    
    public static void main(String[] args) {
        System.out.println("\nRuning Selection Sort...");
        int[] result = new SelectionSort(createRandomArray()).sort();
        System.out.format("Sorted Output :%s\n", Arrays.toString(result));
    }
    
    static int[] createRandomArray() {
        int[] input = new int[rand.nextInt(10, 20)];
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
