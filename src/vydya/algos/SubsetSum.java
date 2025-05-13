package vydya.algos;

import java.util.*;

public class SubsetSum {
    static boolean found = false;
    static int target;
    static int[] arr;

    static void findSubset(int index, int sum, String subset) {
        if (sum == target) {
            System.out.println("Subset is: " + subset.trim());
            found = true;
            return;
        }
        if (index == arr.length || sum > target) return;

        findSubset(index + 1, sum + arr[index], subset + arr[index] + " ");
        findSubset(index + 1, sum, subset);
    }

    public static void main(String[] args) {
        // 3 1 2 5 4
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of elements: ");
        int n = scanner.nextInt();

        SubsetSum.arr = new int[n];
        System.out.println("Enter elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        System.out.print("Enter target sum: ");
        SubsetSum.target = scanner.nextInt();

        findSubset(0, 0, "");

        if (!found) {
            System.out.println("No subset found with the given sum.");
        }
    }
}