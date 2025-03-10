package vydya.algos;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class SelectionSort {
    static Random rand = new Random(10L);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the size of the array: ");
        int[] array = new int[scanner.nextInt()];
        scanner.close();
        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(10, 10000);
        }

        SelectionSort selectionSort = new SelectionSort();

        System.out.println("The input array is: "+ Arrays.toString(array));
        long startTime = System.nanoTime();
        selectionSort.selectionSort(array);
        long endTime = System.nanoTime();
        System.out.println("Selection Sorted array: " + Arrays.toString(array)
                + "\nDuration: " + (endTime - startTime) + " ns");
    }


    public void selectionSort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            int minIndex = i;
            int min = a[minIndex];
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < min) {
                    min = a[j];
                    minIndex = j;
                }
            }
            swap(a, i, minIndex);
        }
    }

    static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
