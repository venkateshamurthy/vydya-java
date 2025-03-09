package vydya.algos;

import java.util.Scanner;

public class NCR {

    long fact(int i) {
        if(i <= 1) return 1;
        else return i * fact(i-1);
    }

    public long ncr(int n, int r) {
        if (n <= r || n <= 0 || r <= 0) return Long.MIN_VALUE;
        return fact(n) / (fact(n - r) * fact(r));
    }

    public static void main(String[] args) {
        NCR ncr = new NCR();
        Scanner sc = new Scanner(System.in);
        System.out.println("To compute NCr please enter the numbers n and r : ");
        int n = sc.nextInt();
        int r = sc.nextInt();
        System.out.println("Result:"+ncr.ncr(n, r));
        sc.close();
    }
}
