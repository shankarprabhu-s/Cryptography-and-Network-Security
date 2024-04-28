import java.util.Scanner;

/**
 * CaesarCiphermy name
 */
public class CaesarCipher {

    public static String encrypt(String plainText, int key) {
        if (plainText.equals(""))
            return "";
        StringBuilder res = new StringBuilder("");

        for (int i = 0; i < plainText.length(); i++) {

            char c = (char) (plainText.charAt(i));
            if (plainText.charAt(i) == ' ')
                c = ' ';
            else if (Character.isUpperCase(c)) {
                if ((char) c + key > 'Z') {
                    c = (char) ((c + key) - 26);
                } else {
                    c = (char) (c + key);
                }

            } else if (Character.isLowerCase(c)) {
                if ((char) c + key > 'z') {
                    c = (char) ((c + key) - 26);
                } else {
                    c = (char) (c + key);
                }
            }

            res.append(c);
        }

        return res.toString();
    }

    public static String decrypt(String cipherText, int key) {
        if (cipherText.equals(""))
            return "";
        StringBuilder res = new StringBuilder("");

        for (int i = 0; i < cipherText.length(); i++) {

            char c = (char) (cipherText.charAt(i));
            if (cipherText.charAt(i) == ' ')
                c = ' ';
            else if (Character.isUpperCase(c)) {
                if ((char) c + key < 'A') {
                    c = (char) ((c - key) + 26);
                } else {
                    c = (char) (c - key);
                }

            } else if (Character.isLowerCase(c)) {
                if ((char) c - key < 'a') {
                    c = (char) ((c - key) + 26);
                } else {
                    c = (char) (c - key);
                }
            }

            res.append(c);
        }
        return res.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter the PLAIN_TEXT");
        String plainText = sc.nextLine();
        System.out.println("enter the KEY");
        int key = sc.nextInt();
        System.out.print("CIPHER_TEXT IS : " + encrypt(plainText, key));
        System.out.println();
        System.out.println("DECRYPTED_TEXT IS : " + decrypt(encrypt(plainText, key), key));
        sc.close();

    }
}