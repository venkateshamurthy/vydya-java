package vydya.algos;

import java.time.Duration;
import java.util.Arrays;
import java.util.Random;

public class MergeSort {

    public static void main(String[] args) {
        Random rand = new Random(10L);
        int[] array = new int[rand.nextInt(10, 20)];
        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(10, 250);
        }

        MergeSort mergeSort = new MergeSort(array);

        System.out.println("The input array is: "+ Arrays.toString(array));

        long startTime = System.nanoTime();
        mergeSort.mergeSort(0, array.length - 1);
        long endTime = System.nanoTime();
        System.out.println("Merge Sorted array: " + Arrays.toString(array)
                + "\nDuration: " + Duration.ofNanos(endTime - startTime) + " s");
    }

    final int[] a;
    final int[] temp;

    MergeSort(int [] array) {
        this.a = array;
        //Set the temporary array to half the original array
        temp = new int[(1 + array.length) / 2];
    }

    public void mergeSort(int start, int end) {
        if (start < end) {
            int mid = (start + end)/2;
            mergeSort(start,   mid);
            mergeSort(mid + 1, end);
            merge(start, mid, end);
        }
    }

    private void merge(final int start, final int mid, final int end) {
        int tLen = mid - start + 1;
        //Copy the array[start] to array[mid] to the temp array
        System.arraycopy(a, start, temp, 0, tLen);

        int i = mid + 1, j = 0, k = start;
        while (i <= end && j < tLen) a[k++] = a[i] < temp[j] ? a[i++] : temp[j++];
        while (i <= end)             a[k++] = a[i++];
        while (j <  tLen)            a[k++] = temp[j++];
    }
}
