package vydya.algos;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class QuickSort {
    static Random rand = new Random(10L);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the size of the array: ");
        int[] array = new int[scanner.nextInt()];
        scanner.close();
        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(10, 10000);
        }

        QuickSort quickSort = new QuickSort();

        System.out.println("The input array is: "+ Arrays.toString(array));
        long startTime = System.nanoTime();
        quickSort.quickSort(array, 0, array.length - 1);
        long endTime = System.nanoTime();
        System.out.println("Quick Sorted array: " + Arrays.toString(array)
        + "\nDuration: " + (endTime - startTime) + " ns");

    }

    int[] a;
    public QuickSort(int [] array) {
        this.a = array;
    }

    public void quickSort(int start, int end) {
        if (start < end) {
            int partition = partition( start, end);
            quickSort(start, partition - 1);
            quickSort(partition + 1, end);
        }
    }

    int partition(int start, int end) {
        // st                                           end
        // 0      1       2    3    4    5    6    7    8
        // 5      1       8    3    9    4    2    7    6
        //       small-->                            <--large
        int small = start + 1, large = end;
        while (small <= large) {
            if      (a[small] <  a[start]) small++;
            else if (a[large] >= a[start]) large--;
            else swap(a, small++, large--);
        }
        swap(a, start, large);
        return large;
    }

    static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

}
