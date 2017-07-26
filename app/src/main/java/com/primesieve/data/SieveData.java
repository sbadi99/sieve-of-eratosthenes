package com.primesieve.data;

import java.util.ArrayList;
import java.util.List;

/**
 * This class uses a Sieve Of Eratosthenes approach
 * Process and flag prime numbers given a upper limit
 */
public class SieveData {

    /**
     * @param max max
     * @return list with primes marked
     */
    public static List<Primes> primeSieve(int max) {

        boolean[] isPrimeNumber = new boolean[max + 1];
        List<Primes> list = new ArrayList<>();

        for (int i = 2; i < max; i++) {
            isPrimeNumber[i] = true;
        }

        for (int i = 2; i < max; i++) {
            if (isPrimeNumber[i]) {
                //add primes to the list with primes flagged as true
                list.add(new Primes(i, true));
                //mark the multiple of i
                for (int j = i; j * i <= max; j++) {
                    isPrimeNumber[i * j] = false;
                }
            } else {
                //add non-primes to list
                list.add(new Primes(i, false));
            }
        }
        return list;
    }

    /**
     * This class provides ability to flag a given integer as prime or non-prime
     */
    public static class Primes {

        private final int number;
        private final boolean isPrime;

        public Primes(final int number, final boolean isPrime) {
            this.number = number;
            this.isPrime = isPrime;
        }

        public int getNumber() {
            return number;
        }

        public boolean isPrime() {
            return isPrime;
        }

        @Override
        public String toString() {
            return String.valueOf(number);
        }
    }
}
