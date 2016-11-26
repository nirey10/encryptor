package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import algorithms.caesar;

public class caesarTest {

	@Test
	public void smallKeyTest() {
		caesar test = new caesar();
		String originalStr = "Encryption Test", encryptedStr,decryptedStr;
		encryptedStr = test.encrypt(originalStr, 2);
		decryptedStr = test.decrypt(encryptedStr, 2);
		
		assertEquals(originalStr,decryptedStr);
		
	}
	@Test
	public void largeKeyTest() { //go beyond MAXVALUE
		caesar test = new caesar();
		String originalStr = "Encryption Test", encryptedStr,decryptedStr;

		encryptedStr = test.encrypt(originalStr, 240);
		decryptedStr = test.decrypt(encryptedStr, 240);
		
		assertEquals(originalStr,decryptedStr);
		
	}
	
	@Test
	public void differentKeysTest() { 
		caesar test = new caesar();
		String originalStr = "Encryption Test", encryptedStr,decryptedStr;

		encryptedStr = test.encrypt(originalStr, 38);
		decryptedStr = test.decrypt(encryptedStr, 44);
		
		assertNotEquals(originalStr,decryptedStr);
		
	}

}
