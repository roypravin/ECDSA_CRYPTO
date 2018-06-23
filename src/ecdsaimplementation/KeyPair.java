/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdsaimplementation;

import java.math.BigInteger;

/**
 *
 * @author Pravin Roy
 */
public class KeyPair {
    
    public BigInteger privateKey;
	public BigInteger[] publicKey;


	public KeyPair(BigInteger[] point, BigInteger n, BigInteger a) {
		System.out.println("Creating Key Pair .......");
		privateKey = BigIntExtend.randomLessThanN(n);
		publicKey = ECOperations.pointMultiply(point, n, a, privateKey);
		System.out.println("Key Pair CREATED");
	}

	public BigInteger[] getPublicKey() {
		return this.publicKey;
	}

	public BigInteger getPrivateKey() {
		return this.privateKey;
 	}
    
}
