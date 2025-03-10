package vydya.algos;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;



public class MinMax {
    private static final Random random = new Random(10L);

    private int min(int a, int b){return a < b ? a : b;}
    private int max(int a, int b){return a > b ? a : b;}

    private int[] findMinMax(int[] data, int min, int max, int k) {
        if (k >= 0) {
            min = min(data[k], min);   // get the minimum of (passed min and data[k])
            max = max(data[k], max);   // get the maximum of (passed max and data[k])
            return findMinMax(data, min, max, k - 1);
        }
        return new int[]{min, max};
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter size:");
        int[] data = new int[scanner.nextInt()];
        for(int i=0; i< data.length; i++)data[i]= random.nextInt(10,50);
        scanner.close();

        System.out.println("Input:"+ Arrays.toString(data));
        MinMax minMax = new MinMax();
        int[] result = minMax.findMinMax(data, Integer.MAX_VALUE, Integer.MIN_VALUE, data.length - 1);
        System.out.println("Min:"+result[0]+"  Max:"+result[1]);

    }
}
