import java.util.Scanner;

public class MonoaphabeticCipher {
   
    static String alphabets = "abcdefghijklmnopqrstuvwxyz";
    static String shuffled_alphabets = "qwertyuiopasdfghjklzxcvbnm";

    static String encrypt(String plainText)
    {   
        if(plainText.equals("")) return "";
        StringBuffer res = new StringBuffer("");

        for(int i=0;i<plainText.length();i++)
        {
            int index = alphabets.indexOf(plainText.charAt(i));
            if(index == -1)
            res.append(plainText.charAt(i));
            else
            {
                res.append(shuffled_alphabets.charAt(index));
            }
        }
        return res.toString();
    }

    static String decrypt(String cipherText)
    {
        if(cipherText.equals("")) return "";
        StringBuffer res = new StringBuffer("");

        for(int i=0;i<cipherText.length();i++)
        {
            int index =  shuffled_alphabets.indexOf(cipherText.charAt(i));
            if(index == -1)
            res.append(cipherText.charAt(i));
            else
            {
                res.append(alphabets.charAt(index));
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter the plain text: ");
        String plainText = sc.nextLine();
        plainText = plainText.toLowerCase(); 
        System.out.println(plainText);
        System.out.println("CipherText : "+encrypt(plainText) );
        System.out.println("DecryptedText "+ decrypt(encrypt(plainText)) );
        sc.close();

    }

}
