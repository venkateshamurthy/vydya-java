package vydya.algos;

import java.util.Random;
import java.util.Scanner;

public class NCR {
    static Random rand = new Random(10L);
    
    long fact(int i) {
        if(i <= 1) return 1;
        else return i * fact(i - 1);
    }

    public long ncr(int n, int r) {
        if (n <= r || n <= 0 || r <= 0) return Long.MIN_VALUE;
        return fact(n) / (fact(n - r) * fact(r));
    }

    public static void main(String[] args) {
        System.out.println("\nRunning NCR..");
        int n = rand.nextInt(10,20); 
        int r = rand.nextInt(5, 10);
        
        NCR ncr = new NCR();
        System.out.println("Result:"+ncr.ncr(n, r));
    }
}
