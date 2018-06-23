/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdsaimplementation;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
/**
 *
 * @author Pravin Roy
 */
public class MessageSV {
    
    public static BigInteger[] messageSign(String msg, BigInteger n, BigInteger[] G, BigInteger a, BigInteger pvkd) throws NoSuchAlgorithmException {
	System.out.println("Signing the message ....");
	BigInteger k, kInv, r, e, s;
	BigInteger[] kG;

	do {
		do {
			k = BigInteger.valueOf(1);
			kG = ECOperations.pointMultiply(G, n, a, k);
			r = kG[0].mod(n);
		  } while (r.compareTo(BigInteger.ZERO) == 0);

		kInv = k.modInverse(n);
		e = new BigInteger(SHAsum(msg.getBytes()), 16);
		s = (kInv.multiply(e.add(pvkd.multiply(r)))).mod(n);
	} while (s.compareTo(BigInteger.ZERO) == 0);

	kG[0] = r;
	kG[1] = s;
	System.out.println("Message SIGNED");
	return kG;
}

	/*
	 * function: messageVerify - sign the message using the private key
	 * returns: boolean  - verification Status
	 */

	public static boolean messageVerify(String msg, BigInteger[] sign, BigInteger n, BigInteger[] G, BigInteger a, BigInteger[] pbkQ) throws NoSuchAlgorithmException {
		System.out.println("Verifying Message ......");
		BigInteger r = sign[0];
		BigInteger s = sign[1];

		if (r.compareTo(n) >= 0) {
			System.out.println("Message NOT VERIFIED");
			return false;
		}
		if (s.compareTo(n) >= 0) {
			System.out.println("Message NOT VERIFIED");
			return false;
		}
		
		BigInteger e = new BigInteger(SHAsum(msg.getBytes()), 16);
		BigInteger w = s.modInverse(n);

		BigInteger u1 = (e.multiply(w)).mod(n);
		BigInteger u2 = (r.multiply(w)).mod(n);

		BigInteger[] X = ECOperations.pointAddition(ECOperations.pointMultiply(G, n, a, u1), ECOperations.pointMultiply(pbkQ, n, a, u2), n); 
 
		BigInteger v = X[0].mod(n);

		if (v.compareTo(r) == 0) {
			System.out.println("Message VERIFIED");
			return true;
		}

		System.out.println("Message NOT VERIFIED");
		return false;
	}

	public static String SHAsum(byte[] convertme) throws NoSuchAlgorithmException {
	    MessageDigest md = MessageDigest.getInstance("SHA-1"); 
	    return byteArray2Hex(md.digest(convertme));
	}

	private static String byteArray2Hex(final byte[] hash) {
	    Formatter formatter = new Formatter();
	    for (byte b : hash) {
	        formatter.format("%02x", b);
	    }
	    return formatter.toString();
	}
    
}
