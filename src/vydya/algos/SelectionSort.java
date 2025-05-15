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
public class SelectionSort {
    private static final  Random rand = new Random(10L);
    private final int[] data;

    public SelectionSort(int[] input) {data = input;}
    
    int[] sort() {
        int n = data.length;
        for (int i = 0; i < n - 1; i++) {
            int jMin = i;
            for (int j = i + 1; j < n; j++) {
                if (data[j] < data[jMin]) {
                    jMin = j;
                }
            }
            swap(i, jMin);
        }
        return data;
    }

    private static void sortInput(int[] input) {
        System.out.println("Input:"+Arrays.toString(input));
        SelectionSort selectionSort = new SelectionSort(input);
        selectionSort.sort();
        System.out.println("Sorted output:"+Arrays.toString(input));
    }
    
    public static void main(String[] args) {
        System.out.println("\nRunning Selection Sort on user inputs...");
        sortInput(new int[]{89,45,68,90,29,34,17});

        Scanner scanner = new Scanner(System.in) ;
        System.out.print("Enter the size of Integer array:"); 
        int[] input = createRandomArray(scanner.nextInt());
        sortInput(input);
    }
    
    public static int[] createRandomArray(int size) {
        int[] input = new int[size];
        for (int i = 0; i < input.length; i++) input[i] = rand.nextInt(100, 1000);
        return input;
    }
    
     void swap(int a, int b) {
        int temp = data[a];
        data[a] = data[b];
        data[b] = temp;
    }
    
}
