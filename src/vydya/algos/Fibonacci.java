package vydya.algos;

import java.util.Random;
import java.util.Scanner;


public class Fibonacci {
    static Random rand = new Random(10L);
    
    // 1, 1, 2, 3, 5, 8, 13....
    int fibonacci(int n) {
        if (n <= 0) {
            System.err.println("The Fibonacci requires n to be natural number"
                    + " \nbut given value is invalid and so returning -1");
            return -1;
        }
        if (n==1 || n==2) return 1;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static void main(String[] args) {
        System.out.println("\nRunning Fibonacci..");
        int n = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the value of n (keep n >=1 )");
        n = scanner.nextInt();
        Fibonacci fib = new Fibonacci();
        System.out.println("n:"+n+"   Result: "+fib.fibonacci(n));
    }
}
