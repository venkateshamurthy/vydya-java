package vydya.algos;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

//search in array
public class LinearSearch {
    private static final Random rand = new Random(10L);
    private final int[] data;

    public LinearSearch(int[] data) {
        this.data = data;
    }
    
    public int search(final int target) {
        int result = search(data.length - 1, target);
        System.out.format("Target number %d is " +
                (result == -1 ? "absent": "present at index:%d\n"),
                target, result);
        return result;
    }
    
    //Recursive search needs k as the starting index
    private int search(final int k, final int key) {
        if (k < 0 || k > data.length - 1) return -1;
        if (data[k] == key) return k;
        return search(k - 1, key);
    }
    
    
    // Generate: 880, 493, 190, 446, 956, 497, 788, 481, 614, 623, 399, 691, 808
     
    static int[] createRandomArray() {
        int size=10;
        Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the size of Integer array:"); 
            size = scanner.nextInt();
        
        return createRandomArray(size);
    }
    
    static int[] createRandomArray(int size) {
        int[] input = new int[size];
        for (int i = 0; i < input.length; i++) input[i] = rand.nextInt(100, 1000);
        System.out.println("Random array Input:" + Arrays.toString(input));
        return input;
    }
    
    public static void main(String[] args) {
        System.out.println("\nRunning Linear Search on an integer array.."+Arrays.toString(args));
        LinearSearch searcher;
        if(Arrays.stream(args).anyMatch("auto"::contains))
            searcher = new LinearSearch(createRandomArray(rand.nextInt(10,20)));
        else
            searcher = new LinearSearch(createRandomArray());
        searcher.search(788);  //present
        searcher.search(-100); //absent
    }
}
