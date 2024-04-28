 import java.util.Scanner;

public class RSAEncryption {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the value for p: ");
        int p = scanner.nextInt();

        System.out.print("Enter the value for q: ");
        int q = scanner.nextInt();

        System.out.print("Enter the message: ");
        int msg = scanner.nextInt();

        int n = p * q;
        int phi = (p - 1) * (q - 1);
        int e = 2;

        // Find suitable e
        while (e < phi) {
            if (gcd(e, phi) == 1) {
                break;
            } else {
                e++;
            }
        }

        // Find d using modular inverse of e
        int d = modInverse(e, phi);

        System.out.println("Public key: (" + e + "," + n + ")");
        System.out.println("Private key: (" + d + "," + n + ")");

        // Encrypt message
        int encryptedMsg = modPow(msg, e, n);
        System.out.println("Encrypted message: " + encryptedMsg);

        // Decrypt message
        int decryptedMsg = modPow(encryptedMsg, d, n);
        System.out.println("Decrypted message: " + decryptedMsg);

        scanner.close();
    }

    // Method to calculate greatest common divisor (gcd)
    private static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }

    // Method to calculate modular inverse
    private static int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) {
                return x;
            }
        }
        return 1;
    }

    // Method to calculate modular exponentiation
    private static int modPow(int base, int exp, int modulus) {
        base = base % modulus;
        int result = 1;
        while (exp > 0) {
            if (exp % 2 == 1) {
                result = (result * base) % modulus;
            }
            base = (base * base) % modulus;
            exp = exp / 2;
        }
        return result;
    }
}
 
