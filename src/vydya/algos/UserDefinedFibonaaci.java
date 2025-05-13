package vydya.algos;

import java.util.Scanner;

public class UserDefinedFibonaaci {
    static int[] input = {5, 23, 38, 61};
    static int fibonaci(int n) {
        if (n < input.length) return input[n];
        else return fibonaci(n - 1) + fibonaci(n - 2);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of fibonacci terms");
        int n = sc.nextInt();
        String s = "";
        for (int i = 0; i < n; i++) s = s + " " + fibonaci(i);
        System.out.println(s);
    }
}
