import java.util.*;

public class PlayfairCipher {
    static char[][] matrix;

    public static List<String> prepareMessage(String msg) {
        msg = msg.replaceAll(" ", "").toUpperCase().replace("J", "I");
        List<String> msgPairs = new ArrayList<>();
        int i = 0;
        while (i < msg.length()) {
            if (i == msg.length() - 1 || msg.charAt(i) == msg.charAt(i + 1)) {
                msgPairs.add(msg.charAt(i) + "X");
                i++;
            } else {
                msgPairs.add(msg.substring(i, i + 2));
                i += 2;
            }
        }
        return msgPairs;
    }

    public static char[][] keyMatrix(String key) {
        key = key.replaceAll(" ", "").toUpperCase().replace("J", "I");
        matrix = new char[5][5];
        Set<Character> keyChars = new HashSet<>();
        for (char c : key.toCharArray()) {
            if (c != ' ' && !keyChars.contains(c)) {
                keyChars.add(c);
            }
        }
        char[] alphabet = "ABCDEFGHIKLMNOPQRSTUVWXYZ".toCharArray();
        int k = 0;
        for (char c : keyChars) {
            matrix[k / 5][k % 5] = c;
            k++;
        }
        for (char c : alphabet) {
            if (c != 'J' && !keyChars.contains(c)) {
                matrix[k / 5][k % 5] = c;
                k++;
            }
        }
        return matrix;
    }

    public static int[] findCoordinates(char c) {
        int[] coordinates = new int[2];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (matrix[i][j] == c) {
                    coordinates[0] = i;
                    coordinates[1] = j;
                    return coordinates;
                }
            }
        }
        return coordinates;
    }

    public static String encrypt(String msg) {
        StringBuilder cipher = new StringBuilder();
        for (String pair : prepareMessage(msg)) {
            char char1 = pair.charAt(0);
            char char2 = pair.charAt(1);
            int[] coordinates1 = findCoordinates(char1);
            int[] coordinates2 = findCoordinates(char2);
            int row1 = coordinates1[0];
            int col1 = coordinates1[1];
            int row2 = coordinates2[0];
            int col2 = coordinates2[1];
            if (row1 == row2) {
                cipher.append(matrix[row1][(col1 + 1) % 5]).append(matrix[row2][(col2 + 1) % 5]);
            } else if (col1 == col2) {
                cipher.append(matrix[(row1 + 1) % 5][col1]).append(matrix[(row2 + 1) % 5][col2]);
            } else {
                cipher.append(matrix[row1][col2]).append(matrix[row2][col1]);
            }
        }
        return cipher.toString();
    }

    public static String decrypt(String cipher) {
        StringBuilder plain = new StringBuilder();
        for (String pair : prepareMessage(cipher)) {
            char char1 = pair.charAt(0);
            char char2 = pair.charAt(1);
            int[] coordinates1 = findCoordinates(char1);
            int[] coordinates2 = findCoordinates(char2);
            int row1 = coordinates1[0];
            int col1 = coordinates1[1];
            int row2 = coordinates2[0];
            int col2 = coordinates2[1];
            if (row1 == row2) {
                plain.append(matrix[row1][(col1 - 1 + 5) % 5]).append(matrix[row2][(col2 - 1 + 5) % 5]);
            } else if (col1 == col2) {
                plain.append(matrix[(row1 - 1 + 5) % 5][col1]).append(matrix[(row2 - 1 + 5) % 5][col2]);
            } else {
                plain.append(matrix[row1][col2]).append(matrix[row2][col1]);
            }
        }
        return plain.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the key: ");
        String key = scanner.nextLine();
        keyMatrix(key);
        System.out.print("Enter the message: ");
        String msg = scanner.nextLine();
        String cipher = encrypt(msg);
        System.out.println("Encrypted Message: " + cipher);
        System.out.println("Decrypted Message: " + decrypt(cipher));
        scanner.close();
    }
}
