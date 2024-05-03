import java.util.Scanner;

/**
 * PolyalphabeticCipher
 */
public class PolyalphabeticCipher {

    static String encryption(String plainText , String key)
    {
      

        StringBuilder res = new StringBuilder("");
        for(int i=0;i<plainText.length();i++)
        {
            int plainTextInt = (int) (plainText.charAt(i)-'a');
            int keyInt = (int) (key.charAt(i % key.length())-'a');
            int encryptedInt = (plainTextInt+keyInt) - 26;
            res.append((char) (encryptedInt + (int) 'a'));
        }

        return res.toString();
    }

    static String decrypt(String cipherText , String key)
    {
        

        StringBuilder res = new StringBuilder("");
        for(int i=0;i<cipherText.length();i++)
        {
            int cipherTextInt = (int) (cipherText.charAt(i)-'a');
            int keyInt = (int) (key.charAt(i % key.length())-'a');
            int decryptedInt = (cipherTextInt-keyInt);
            if(decryptedInt < 1)
            decryptedInt += 26;
            res.append((char) (decryptedInt + (int) 'a'));
        }

        return res.toString();
    }

    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        System.out.println("enter the plain text");
        String plainText = sc.nextLine();
        plainText = plainText.toLowerCase();
        System.out.println("enter the key");
        String key = sc.nextLine();
        
       
        System.out.println("encrypted text");
        System.out.println(encryption(plainText, key));
        System.out.println("decryted text");
        System.out.println(decrypt(encryption(plainText, key) ,key));
        sc.close();

    }


}
