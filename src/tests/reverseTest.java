package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import algorithms.doubleAlgo;
import algorithms.reverse;
import algorithms.xor;

public class reverseTest {

	@Test
	public void basicTest() {
		reverse test = new reverse();
		String originalStr = "Encryption Test", encryptedStr,decryptedStr;
		int[] key = new int[1];
		key[0]=41;
		encryptedStr = test.encrypt(originalStr, key);
		decryptedStr = test.decrypt(encryptedStr, key);
		assertEquals(originalStr,decryptedStr);
	}
	
	@Test
	public void checkingReverse() {
		
			reverse reverseTest = new reverse();
			xor xorTest = new xor();
			String originalStr = "Encryption Test", encryptedStr,decryptedStr;
	
			int[] key = new int[1];
			key[0]=41;
			encryptedStr = reverseTest.encrypt(originalStr, key);
			decryptedStr = xorTest.decrypt(originalStr, key);
			
			assertEquals(encryptedStr,decryptedStr);
		}

}
