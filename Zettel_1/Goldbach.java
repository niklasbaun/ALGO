/**
 * For a given even integer number n in the proven Goldbach range,
 * the methods in this class can be used to find two prime integer numbers whose sum eqauls n.
 */
public class Goldbach {

    /**
     * Returns a pair of two prime numbers whose sum equals the given number n.
     * @param n The number to be checked.
     * @return A pair of two prime numbers whose sum equals n.
     */
    public Pair<Integer> goldbach(int n) {
        //Vorbedingung: Zahl muss gerade sein; größer als 2 und sollte kleiner sein a,s 4 * 10^18
        boolean pre = (n % 2 == 0) && (n > 2) && (n < 4 * Math.pow(10, 18));

        //check if the given number is valid
        if (n < 4 || n % 2 != 0) {
            return null;
        } else if (pre) {


            for (int i = 2; i <= n / 2; i++) {
                //check if i and n-i are prime
                //Nachbedingung: 2 Primzahlen, deren Summe gleich der Eingabe ist
                boolean post = (isPrime(i) && isPrime(n-i) && (i + (n-i) == n));
                if (post) {
                    return new Pair<Integer>(i, n - i);
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
    private static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    //TODO: 1.1 b)+c)

}
