package vydya.algos;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class NCR {
    static final Random rand = new Random(10L);
    
    long fact(int i) {
        if(i <= 1) return 1;
        else return i * fact(i - 1);
    }

    public long ncr(int n, int r) {
        if (n <= r || n <= 0 || r <= 0) {
            System.err.println("The nCr requires both n and r to be natural numbers"
                    + " and n>=r;\nbut given values are invalid and so returning -1");
            return -1L;
        }
        return fact(n) / (fact(n - r) * fact(r));
    }

    public static void main(String[] args) {
        System.out.println("\nRunning NCR..");
        
        int n = 0; 
        int r = 0;
        if (Arrays.stream(args).anyMatch("auto"::contains)){
            n = rand.nextInt(8, 12);
            r = rand.nextInt(5, 8);
        } else {
            Scanner scanner = new Scanner(System.in);
                System.out.println("Enter the values of n and r (keep n > r)");
                n = scanner.nextInt();
                r = scanner.nextInt();
            
        }
        
        NCR ncr = new NCR();
        System.out.format("n=%d, r=%d nCr=%d\n", n, r, ncr.ncr(n, r));
    }
}
