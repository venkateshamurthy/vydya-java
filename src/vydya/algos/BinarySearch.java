package vydya.algos;

import java.util.Arrays;
import java.util.Random;

public class BinarySearch {
    static Random rand = new Random(10);
    public static void main(String[] args) {
        int[] array = new int[rand.nextInt(10, 20)];
        for (int i=0; i< array.length; i++) array[i] = rand.nextInt(10,50);
        System.out.println("Unsorted array:"+Arrays.toString(array));

        MergeSort sorter = new MergeSort(array);
        sorter.mergeSort( 0, array.length - 1);

        System.out.println("Sorted array  :"+Arrays.toString(array));
        BinarySearch binarySearch = new BinarySearch();
        int searchKey = 32;
        System.out.println("Recursively searched "+searchKey+" at index :"+binarySearch.searchRecurse(array, 0, array.length - 1, 32));

        System.out.println("Iteratively searched "+31+" at index :"+binarySearch.search(array, 0, array.length - 1, 31));
        System.out.println("Recursively searched "+31+" at index :"+binarySearch.searchRecurse(array, 0, array.length - 1, 31));
    }

    int search(int[] array, int low, int far, int key) {
         while (low <= far) {
             int mid = low + (far - low) / 2;
             if (array[mid] == key) return mid;
             else if (array[mid] < key) low = mid + 1;
             else far = mid - 1;
         }
         return -1;
    }

    int searchRecurse(int[] array, int left, int right, int key) {
        if (left <= right) {
            int mid = left + (right - left) / 2;
            if (array[mid] == key) return mid;
            else if (array[mid] < key) left = mid + 1;
            else right = mid - 1;
            return searchRecurse(array, left, right, key);
        }
        return  -1;
    }

    void sort(int[] array) {
        int end = array.length - 1;
        for (int i=0; i < end; i++) {
            int minIndex = i;
            for (int j=i+1; j <= end; j++) if (array[j] < array[minIndex]) minIndex = j;
            swap(array, minIndex, i);
        }
    }

    private static void swap(int[] array, int minIndex, int i) {
        if (i == minIndex) return;
        int temp = array[minIndex];
        array[minIndex] = array[i];
        array[i] = temp;
    }
}
