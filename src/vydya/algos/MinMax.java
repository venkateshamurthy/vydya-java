package vydya.algos;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;



public class MinMax {
    private static final Random rand = new Random(10L);
    private final int[] data;
    
    public MinMax(int[] data) {
        this.data = data;
    }

    public int[] findMinMax() {
        return findMinMax(Integer.MAX_VALUE, Integer.MIN_VALUE, data.length - 1);
    }

    private int[] findMinMax(int min, int max, int k) {
        if (k >= 0) {
            min = minimum(data[k], min);   // get the minimum of (passed min and data[k])
            max = maximum(data[k], max);   // get the maximum of (passed max and data[k])
            return findMinMax(min, max, k - 1);
        }
        return new int[]{min, max};
    }
    
    private int minimum(int a, int b){return a < b ? a : b;}
    private int maximum(int a, int b){return a > b ? a : b;}

    public static void main(String[] args) {
        System.out.println("\n\nRunning Min Max on an integer array..");
        Scanner scanner = new Scanner(System.in) ;
        
        System.out.print("Enter the size of Integer array:"); 
        int[] input = createRandomArray(scanner.nextInt());
        
        MinMax minMax = new MinMax(input);
        long startTime = System.nanoTime();
        int[] result = minMax.findMinMax();
        long endTime = System.nanoTime();
        System.out.println("Min:"+result[0]+"  Max:"+result[1] + " duration:" + (endTime - startTime));

    }
     
    public static int[] createRandomArray(int size) {
        int[] input = new int[size];
        for (int i = 0; i < input.length; i++) input[i] = rand.nextInt(100, 1000);
        System.out.format("Unsorted Input:%s\n" , Arrays.toString(input));
        return input;
    }
}
