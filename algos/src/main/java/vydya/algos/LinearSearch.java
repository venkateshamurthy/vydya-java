package vydya.algos;

import java.util.Random;
import java.util.Scanner;
//search in array
public class LinearSearch {
    private static final Random RANDOM = new Random(10L);
    //<--[0,1,2,3,4,5,6,7,8,9]-->
    //   [10,15,17,
    // for (int i=0; i < n; i++) if(a[i]==target) return i;
    // return -1;
    //Recursive search needs k as the starting index
    public int search(int[] a, final int k, final int key) {
        if (k < 0 || k > a.length - 1) return -1;
        if (a[k] == key) return k;
        return search(a, k - 1, key);
    }

    public static void main(String[] args) {
        LinearSearch linearSearch = new LinearSearch();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of elements:");
        int[] data = new int[scanner.nextInt()];

        for (int i = 0; i < data.length; i++) data[i] = RANDOM.nextInt(10,50);
        System.out.println("Enter the Input:");

        System.out.print("Enter the target number:");
        int target = scanner.nextInt();
        scanner.close();

        int result = linearSearch.search(data, data.length - 1, target);
        System.out.print("Target number " + target + " is " +
                (result == -1 ? "absent": "present at index:"+result));
    }
}
