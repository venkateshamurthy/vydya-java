package vydya.algos;

import java.util.Arrays;
import java.util.Random;

public class BinarySearch {
    static Random rand = new Random(10);
    final int[] data;
    
    public BinarySearch(int[] input) {
        data = input;
    }
    
    public int search(int target) {
        int result = search(0, data.length - 1, target);
        System.out.format("Target number %d is " +
                (result == -1 ? "absent": "present at index:%d\n"),
                target, result);
        return result;
    }

    private int search(int low, int far, int key) {
        if (low <= far) {
            int mid = low + (far - low) / 2;
            
            if (data[mid] == key) return mid;
            else if (data[mid] < key) low = mid + 1;
            else far = mid - 1;
            
            return search(low, far, key);
        }
        return  -1;
    }
    
    static int[] createRandomArray() {
        int[] input = new int[rand.nextInt(10, 20)];
        for (int i = 0; i < input.length; i++) input[i] = rand.nextInt(100, 1000);
        System.out.println("Random Array Input:" + Arrays.toString(input));
        return input;
    }
    
    public static void main(String[] args) {
        System.out.println("\n\nRunning Binary Search on sorted array...");
        int[] result = new MergeSort(createRandomArray()).sort();
        System.out.println("Sorted array  :"+Arrays.toString(result));
        
        BinarySearch searcher = new BinarySearch(result);
        
        searcher.search(788);  //present
        searcher.search(-100); //absent
    }
}
