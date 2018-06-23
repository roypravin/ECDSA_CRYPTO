/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdsaimplementation;

import java.math.BigInteger;
import java.util.Random;
/**
 *
 * @author Pravin Roy
 */
public class BigIntExtend {
    
    public static BigInteger randomLessThanN(BigInteger n) {
		BigInteger r;
		Random rnd = new Random();
		do {
		    r = new BigInteger(n.bitLength(), rnd);
		} while (r.compareTo(n) >= 0); //ALSO greater than 0
		return r;
	}
    
}
