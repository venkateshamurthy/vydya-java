package vydya.algos;

import java.util.Scanner;

public class Fibonacci {
    int fibonacci(int n) {
        if (n <= 0) throw new IllegalArgumentException("Invalid argument passed:'"+n+"' to Fibonacci. Always pass a positive number");
        if (n <= 2) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
    public static void main(String[] args) {

        System.out.println("Enter th number to calculate fibonacci of that number:");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.close();

        Fibonacci fib = new Fibonacci();
        System.out.println("Result: "+fib.fibonacci(n));

    }
}
