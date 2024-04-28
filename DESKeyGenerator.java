import java.security.SecureRandom;

public class DESKeyGenerator {

    public static byte[] generateKey() {
        // Initialize an array to store the key
        byte[] key = new byte[8];

        // Generate 64 random bits
        SecureRandom random = new SecureRandom();
        random.nextBytes(key);

        // Apply parity bits
        applyParityBits(key);

        return key;
    }

    private static void applyParityBits(byte[] key) {
        // Calculate parity for each byte
        for (int i = 0; i < key.length; i++) {
            key[i] = calculateParity(key[i]);
        }
    }

    private static byte calculateParity(byte b) {
        // Count the number of set bits in the byte
        int numBits = 0;
        for (int i = 0; i < 7; i++) {
            if (((b >> i) & 1) == 1) {
                numBits++;
            }
        }

        // Set the parity bit to make the total number of set bits odd
        if (numBits % 2 == 0) {
            return (byte) (b | 1);
        } else {
            return (byte) (b & 0xFE);
        }
    }

    public static void main(String[] args) {
        byte[] key = generateKey();
        System.out.print("Generated Key: ");
        for (byte b : key) {
            System.out.print(String.format("%02X", b));
        }
    }
}
