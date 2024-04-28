import java.util.Random;

public class FeistelCipher {

    // Random bits key generation
    public static String randKey(int length) {
        StringBuilder keyBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int bit = random.nextInt(2);
            keyBuilder.append(bit);
        }
        return keyBuilder.toString();
    }

    // Function to implement bit exor
    public static String exor(String a, String b) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == b.charAt(i)) {
                result.append("0");
            } else {
                result.append("1");
            }
        }
        return result.toString();
    }

    // Feistel Cipher
    public static void main(String[] args) {
        String PT = "Hello";
        System.out.println("Plain Text is: " + PT);

        // Converting the plain text to ASCII
        StringBuilder PT_Bin = new StringBuilder();
        for (char c : PT.toCharArray()) {
            PT_Bin.append(String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0'));
        }

        int n = PT_Bin.length() / 2;
        String L1 = PT_Bin.substring(0, n);
        String R1 = PT_Bin.substring(n);

        // Generate Key K1 for the first round
        String K1 = randKey(R1.length());

        // Generate Key K2 for the second round
        String K2 = randKey(R1.length());

        // first round of Feistel
        String f1 = exor(R1, K1);
        String R2 = exor(f1, L1);
        String L2 = R1;

        // Second round of Feistel
        String f2 = exor(R2, K2);
        String R3 = exor(f2, L2);
        String L3 = R2;

        // Cipher text
        String binData = L3 + R3;
        StringBuilder strData = new StringBuilder();

        for (int i = 0; i < binData.length(); i += 7) {
            String tempData = binData.substring(i, Math.min(i + 7, binData.length()));
            int decimalData = Integer.parseInt(tempData, 2);
            strData.append((char) decimalData);
        }

        System.out.println("Cipher Text: " + strData.toString());

        // Decryption
        String L4 = L3;
        String R4 = R3;

        String f3 = exor(L4, K2);
        String L5 = exor(R4, f3);
        String R5 = L4;

        String f4 = exor(L5, K1);
        String L6 = exor(R5, f4);
        String R6 = L5;

        String PT1 = L6 + R6;
        StringBuilder RPT = new StringBuilder();

        for (int i = 0; i < PT1.length(); i += 8) {
            String tempData = PT1.substring(i, Math.min(i + 8, PT1.length()));
            int decimalData = Integer.parseInt(tempData, 2);
            RPT.append((char) decimalData);
        }

        System.out.println("Retrieved Plain Text is: " + RPT.toString());
    }
}
