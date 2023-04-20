/**
 * For a given even integer number n in the proven Goldbach range,
 * the methods in this class can be used to find two prime integer numbers whose sum equals n.
 * @author baun, niklas
 */
public class Goldbach {

    /**
     * Checks if the given number n is in the proven Goldbach range.
     * @param n The number to be checked.
     * @return true if n is in the proven Goldbach range, false otherwise.
     */
    private boolean pre(long n){
        //Vorbedingung: Zahl muss gerade sein; größer als 2 und sollte kleiner sein als 4 * 10^18
        return (n % 2 == 0) && (n > 2) && (n < 4 * Math.pow(10, 18));
    }

    /**
     * Checks if the given number n is the sum of two prime numbers.
     * @param n The number to be checked.
     * @param i The first prime number.
     * @return true if n is the sum of two prime numbers, false otherwise.
     */
    private boolean post(long n, long i){
        //Nachbedingung: 2 Primzahlen, deren Summe gleich der Eingabe ist
        return (isPrime(i) && isPrime(n-i) && (i + (n-i) == n));
    }

    /**
     * Returns a pair of two prime numbers whose sum equals the given number n.
     * @param n The number to be checked.
     * @return A pair of two prime numbers whose sum equals n.
     */
    public Pair<Long> goldbach(long n) {

        boolean pre = pre(n);
        //check if the given number is valid
        if (n < 4 || n % 2 != 0) {
            return null;

        } else if (pre) {
            //check all possible pairs of prime numbers
            for (long i = 2; i <= n / 2; i++) {
                //check if i and n-i are prime
                //Nachbedingung: 2 Primzahlen, deren Summe gleich der Eingabe ist
                if (post(n, i)) {
                    return new Pair<Long>(i, n - i);
                }
            }
        }
        return null;
    }
    /**
     * check if a number is a prime number
     * @param n the number to be checked
     * @return true if n is a prime number, false otherwise
     **/
    private static boolean isPrime(long n) {
        //check if n is a valid input (n > 1)
        if (n <= 1) {
            return false;
        }
        //walk through all numbers from 2 to n-1 and check if n is divisible by any of them
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
