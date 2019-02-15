
import java.util.Scanner;
import java.util.Random;
//import com.google.common.base.Stopwatch;
import java.math.BigInteger; 
/** Class SolovayStrassen **/
public class SolovayStrassen
{
    /** Function to calculate jacobi (a/b) **/
    public long Jacobi(long a, long b)
    {
        if (b <= 0 || b % 2 == 0)
            return 0;
        long j = 1L;
        if (a < 0)
        {
            a = -a;
            if (b % 4 == 3)
                j = -j;
        }
        while (a != 0)
        {
            while (a % 2 == 0)
            {
                a /= 2;
                if (b % 8 == 3 || b % 8 == 5)
                    j = -j;
            }
 
            long temp = a;
            a = b;
            b = temp;
 
            if (a % 4 == 3 && b % 4 == 3)
                j = -j;
            a %= b;
        }
        if (b == 1)
            return j;
        return 0;
    }
    /** Function to check if prime or not **/
    public boolean isPrime(long n, int iteration)
    {
        /** base case **/
        if (n == 0 || n == 1)
            return false;
        /** base case - 2 is prime **/
        if (n == 2)
            return true;
        /** an even number other than 2 is composite **/
        if (n % 2 == 0)
            return false;
 
        Random rand = new Random();
        for (int i = 0; i < iteration; i++)
        {
            long r = Math.abs(rand.nextLong());            
            long a = r % (n - 1) + 1;
            System.out.println("here1");
            long jacobian = (n + Jacobi(a, n)) % n;
            System.out.println("here2");
            String as = Long.toString(a);
            String bs = Long.toString((n-1)/2);
            String cs = Long.toString(n);
            BigInteger base = new BigInteger(as);
            BigInteger exp = new BigInteger(bs);
            BigInteger mod = new BigInteger(cs);
            //long mod = modPow(a, (n - 1)/2, n);
            BigInteger res = new BigInteger("0");
            res = base.modPow(exp, mod);
            BigInteger jac = new BigInteger(Long.toString(jacobian));
            if(jacobian == 0 || res.compareTo(jac) != 0) 
                return false;
        }
        return true;        
    }
    /** Function to calculate (a ^ b) % c **/
    public long modPow(long a, long b, long c)
    {
        long res = 1;
        for (int i = 0; i < b; i++)
        {
            res *= a;
            res %= c; 
            System.out.println("i: " + i);
        }
        return res % c;
    }    
    /** Main function **/
    public static void main (String[] args) 
    {
    	SolovayStrassen ss = new SolovayStrassen();

        int k = 1;
        long num = 941083987; // number to test;
        long startTime = System.nanoTime();
        boolean prime = ss.isPrime(num, k);
        long endTime = System.nanoTime();
        if (prime)
            System.out.println("\n"+ num +" is prime");
        else
            System.out.println("\n"+ num +" is composite");        
        
        long duration = (endTime - startTime); 
        System.out.println("Duration:" + duration);
    	
    }
}