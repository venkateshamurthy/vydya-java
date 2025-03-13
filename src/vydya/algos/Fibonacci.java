package vydya.algos;

import java.util.Random;
import java.util.Scanner;

public class Fibonacci {
    static Random rand = new Random(10L);
    // 1, 1, 2, 3, 5, 8, 13....
    int fibonacci(int n) {
        if (n <= 0) throw new IllegalArgumentException("Invalid argument passed:'"+n+"' to Fibonacci. Always pass a positive number");
        if (n==1 || n==2) return 1;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static void main(String[] args) {

        //System.out.println("Enter th number to calculate fibonacci of that number:");
        //Scanner sc = new Scanner(System.in);
        int n = rand.nextInt(10,20); //sc.nextInt();
        //sc.close();

        Fibonacci fib = new Fibonacci();
        System.out.println("n:"+n+"   Result: "+fib.fibonacci(n));

    }
}
