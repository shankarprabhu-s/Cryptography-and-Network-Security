import java.util.Scanner;

public class HillCipher {
    private int[][] keyMatrix;
    private int keySize;

    public HillCipher(int[][] keyMatrix) {
        this.keyMatrix = keyMatrix;
        this.keySize = keyMatrix.length;
    }

    // Encrypts a plaintext string using the Hill cipher
    public String encrypt(String plaintext) {
        // Pad the plaintext if its length is not a multiple of keySize
        while (plaintext.length() % keySize != 0) {
            plaintext += 'X'; // Add 'X' as padding character
        }

        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i += keySize) {
            String block = plaintext.substring(i, i + keySize);
            int[] plaintextVector = new int[keySize];
            for (int j = 0; j < keySize; j++) {
                plaintextVector[j] = block.charAt(j) - 'A'; // Convert characters to numeric values
            }
            int[] encryptedVector = multiplyMatrix(keyMatrix, plaintextVector);
            for (int num : encryptedVector) {
                ciphertext.append((char) (num % 26 + 'A')); // Convert numeric values back to characters
            }
        }
        return ciphertext.toString();
    }

    // Helper method to multiply a matrix by a vector
    private int[] multiplyMatrix(int[][] matrix, int[] vector) {
        int[] result = new int[keySize];
        for (int i = 0; i < keySize; i++) {
            for (int j = 0; j < keySize; j++) {
                result[i] += matrix[i][j] * vector[j];
            }
            result[i] %= 26; // Modulo 26 to keep the result within the range of alphabet
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Example key matrix (3x3)
        int[][] keyMatrix = {{6, 24, 1}, {13, 16, 10}, {20, 17, 15}};
        HillCipher hillCipher = new HillCipher(keyMatrix);

        System.out.print("Enter plaintext (uppercase letters only): ");
        String plaintext = scanner.nextLine().toUpperCase();

        String ciphertext = hillCipher.encrypt(plaintext);
        System.out.println("Encrypted ciphertext: " + ciphertext);

        scanner.close();
    }
}
