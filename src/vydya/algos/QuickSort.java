package vydya.algos;

import java.util.*;
import java.util.stream.Stream;

public class QuickSort {
    static Random rand = new Random(10L);

    public static void main(String[] args) {
        int[] array = new int[rand.nextInt(10, 20)];
        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(10, 50);
        }

        QuickSort quickSort = new QuickSort(array);

        System.out.println("The input array is: "+ Arrays.toString(array));
        long startTime = System.nanoTime();
        quickSort.quickSort(0, array.length - 1);
        long endTime = System.nanoTime();
        System.out.println("Quick Sorted array: " + Arrays.toString(array)
                + "\nDuration: " + (endTime - startTime) + " ns");

    }

    final int[] a;
    public QuickSort(int [] array) {
        this.a = array;
    }

    public void quickSort(int left, int right) {
        if (left < right) {
            int p = partition(left, right);
            quickSort(left, p - 1);
            quickSort(p + 1,   right);
        }
    }

    int partition(int left, int right) {
        // 5      1       8    3    9    4    2    7    6   --> Values
        // 0      1       2    3    4    5    6    7    8   --> Indexes
        // left   left+1                                right
        // pivot  i-------->                     <------j

        int pivot = a[left];
        int i =     left + 1;
        int j =     right;

        while (i <= j) {
            if      (a[i] <  pivot) i++;
            else if (a[j] >= pivot) j--;
            else swap(i++, j--);
        }
        swap(left, j);
        return j;
    }

    void swap(int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
