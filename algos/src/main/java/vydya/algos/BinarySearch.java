package vydya.algos;

import java.util.Arrays;
import java.util.Random;

public class BinarySearch {
    static Random rand = new Random(10);
    final int[] array;
    
    public BinarySearch(int[] input) {
        array = input;
    }
    
    public int search(int searchKey) {
        return search(0, array.length - 1, searchKey);
    }

    public int search(int low, int far, int key) {
        if (low <= far) {
            int mid = low + (far - low) / 2;
            
            if (array[mid] == key) return mid;
            else if (array[mid] < key) low = mid + 1;
            else far = mid - 1;
            
            return search(low, far, key);
        }
        return  -1;
    }
    
    static int[] createRandomArray() {
        int[] input = new int[rand.nextInt(10, 20)];
        for (int i = 0; i < input.length; i++) input[i] = rand.nextInt(100, 1000);
        System.out.println("Unsorted Input:" + Arrays.toString(input));
        return input;
    }
    
    public static void main(String[] args) {
        System.out.println("\nRunning Binary Search...");
        int[] result = new MergeSort(createRandomArray()).sort();
        System.out.println("Sorted array  :"+Arrays.toString(result));
        
        BinarySearch binarySearch = new BinarySearch(result);
        
        int searchKey = 497;
        System.out.format("Recursively searched %3d at index : %3d\n",31,
                binarySearch.search(31));
        System.out.format("Recursively searched %3d at index : %3d\n",
                searchKey, binarySearch.search(searchKey));
    }
}
