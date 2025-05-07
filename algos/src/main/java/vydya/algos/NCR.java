package vydya.algos;

import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;
/**
 * This calculates the NCr with N and r being natural numbers where N > r.
 * <br>
 * So as we know the NCr = Factorial(N) / ( Factorial(N-r) * Factorial(r))
 * <p>
 * <b>Note:</b>However due to large values getting computed while computing 
 * factorial we need to use {@link java.math.BigInteger} that can hold 
 * very large integer values.Hence note that the  methods here do use BigInteger
 * <p>
 * Reference: https://www.calculatorsoup.com/calculators/discretemathematics/combinations.php.
 * 
 * @author vydya
 */
public class NCR {
    static final Random rand = new Random(10L);
    
    /**
     * Factorial computation.
     * @param i an integer for which we need the factorial
     * @return BigInteger representing the factorial of i
     */
    BigInteger fact(int i) {
        
        if(i == 1)        return BigInteger.ONE;  // 1 x 1
        
        else if (i == 2)  return BigInteger.TWO;  // 2 x 1
        
        else {
            //Please note You cannot just multiply BigIntegers with * symbol
            
            //First get the BigInteger of i with Biginteger.valueOf(i) method
            BigInteger bigI =  BigInteger.valueOf(i);
            
            //Next Multiply using BigInteger.multiply method as follows
            return fact(i - 1).multiply( bigI );
        }
    }
    
    public BigInteger ncr(int n, int r) {
        if (n <= r || n <= 0 || r <= 0) {
            System.err.println("n & r are invalid or negative so returning -1");
            return BigInteger.valueOf(-1L);
        }
        
        BigInteger numerator =   fact(n);
        //                       -----------------------------
        BigInteger denominator = fact(n - r).multiply( fact(r) );
        
        return numerator.divide( denominator );
    }

    public static void main(String[] args) {
        System.out.println("\nRunning NCR..");
        
        int n = 0; 
        int r = 0;
        // so this must be manual hence do enter n and r by yourself.
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the values of n and r (keep n > r)");
        n = scanner.nextInt();
        r = scanner.nextInt();
        // Dont close Scanner here as you may be running other programs
        
        NCR ncr = new NCR();
        System.out.format("n=%d, r=%d nCr=%d%n", n, r, ncr.ncr(n, r));
    }
}
