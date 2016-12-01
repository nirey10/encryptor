package test.java.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import main.java.algorithms.xor;

public class xorTest {

	@Test
	public void simpleTest() {
		
		xor test = new xor();
		String originalStr = "Encryption Test", encryptedStr,decryptedStr;

		int[] key = new int[1];
		key[0]=41;
		encryptedStr = test.encrypt(originalStr, key);
		decryptedStr = test.decrypt(encryptedStr, key);
		
		assertEquals(originalStr,decryptedStr);
		
	}
	
	@Test
	public void differentKeysTest() {
		
		xor test = new xor();
		String originalStr = "Encryption Test", encryptedStr,decryptedStr;

		int[] key = new int[1];
		key[0]=41;
		encryptedStr = test.encrypt(originalStr, key);
		key[0]=111;
		decryptedStr = test.decrypt(encryptedStr, key);
		
		assertNotEquals(originalStr,decryptedStr);
		
	}
	
	
	

}
