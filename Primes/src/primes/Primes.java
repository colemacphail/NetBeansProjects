package primes;

import java.util.*;

public class Primes {

    public static void main(String[] args) {
        
        boolean[] isPrime = new boolean[7000000];
        Arrays.fill(isPrime, true);//make all numbers prime
        
        for (int i = 2; i < isPrime.length; i++) { 
            
            if (isPrime[i]) { //only check multiples of primes
                
                for (int n = 2; i * n < isPrime.length; n++) {//only check numbers up to the square root of the number you're checking
                    
                    isPrime[i * n] = false;//mark all multiples of the prime as not prime
                }
            }
            if (isPrime[i]){//if it's prime, print it
                System.out.println(i);
            }
        }
    }
}