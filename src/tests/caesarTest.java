package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import algorithms.caesar;

public class caesarTest {

	@Test
	public void smallKeyTest() {
		caesar test = new caesar();
		String originalStr = "Encryption Test", encryptedStr,decryptedStr;
		int[] key = new int[1];
		key[0]=2;
		encryptedStr = test.encrypt(originalStr, key);
		decryptedStr = test.decrypt(encryptedStr, key);
		
		assertEquals(originalStr,decryptedStr);
		
	}
	@Test
	public void largeKeyTest() { //go beyond MAXVALUE
		caesar test = new caesar();
		String originalStr = "Encryption Test", encryptedStr,decryptedStr;

		int[] key = new int[1];
		key[0]=125;
		encryptedStr = test.encrypt(originalStr, key);
		decryptedStr = test.decrypt(encryptedStr, key);
		
		assertEquals(originalStr,decryptedStr);
		
	}
	
	@Test
	public void differentKeysTest() { 
		caesar test = new caesar();
		String originalStr = "Encryption Test", encryptedStr,decryptedStr;

		int[] key = new int[1];
		key[0]=38;
		encryptedStr = test.encrypt(originalStr, key);
		key[0]=44;
		decryptedStr = test.decrypt(encryptedStr, key);
		
		assertNotEquals(originalStr,decryptedStr);
		
	}

}
