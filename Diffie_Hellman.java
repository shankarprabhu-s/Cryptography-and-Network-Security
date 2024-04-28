import java.util.Scanner;

public class Diffie_Hellman {
    String url = "https://www.geeksforgeeks.org/implementation-diffie-hellman-algorithm/"; // REFER THIS FOR MORE CLARITY
    
    static long power(long public_key1 , long private_key , long public_key2 )
    {
        return (long)(Math.pow(public_key1,private_key)%public_key2);
    }

    public static void main(String[] args) {
         
        Scanner sc = new Scanner(System.in);
        long P = 23 , G = 9 ; //respective public key

        // choosing the private key
        System.out.println("enter the 2 private keys");
        long pk1 = sc.nextLong(); 
        long pk2 = sc.nextLong();

        //key generation
        long k1 = power(G,pk1,P);
        long k2 = power(G,pk2,P);

        //EXCHANGE KEY 
        // GENEARTE SECRET KEY
        long k1_ = k1;
        k1 =  power(k2, pk1, P);
        k2 =  power(k1_, pk2, P);
        

        System.out.println("Secret key for FIRST PERSON "+ k1);
        System.out.println("Secret key for SECOND PERSON "+ k2);

        sc.close();

    }
}
