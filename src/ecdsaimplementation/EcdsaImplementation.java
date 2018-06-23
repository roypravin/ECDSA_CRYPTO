/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdsaimplementation;


import java.util.Scanner;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
/**
 *
 * @author Pravin Roy
 */
public class EcdsaImplementation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NoSuchAlgorithmException{
        // TODO code application logic here
        
        EllipticCurve ec1 = new EllipticCurve();
        
        BigInteger n = ec1.getN();
	BigInteger a = ec1.getA();
	BigInteger[] G = ec1.getXyG();

	KeyPair kp = new KeyPair(G, n, a);

	Scanner s = new Scanner(System.in);
	System.out.println("Enter your message on one line:");
	String msg = s.nextLine();

	BigInteger[] signature = MessageSV.messageSign(msg, n, G, a, kp.getPrivateKey());

	MessageSV.messageVerify(msg, signature, n, G, a, kp.getPublicKey());
    }
    
}
