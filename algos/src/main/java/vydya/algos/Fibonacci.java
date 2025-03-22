package vydya.algos;

import java.util.Random;

public class Fibonacci {
    static Random rand = new Random(10L);
    
    // 1, 1, 2, 3, 5, 8, 13....
    int fibonacci(int n) {
        if (n <= 0) return -1;
        if (n==1 || n==2) return 1;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static void main(String[] args) {
        System.out.println("\nRunning Fibonacci..");
        int n = rand.nextInt(10,20); //sc.nextInt();

        Fibonacci fib = new Fibonacci();
        System.out.println("n:"+n+"   Result: "+fib.fibonacci(n));

    }
}
