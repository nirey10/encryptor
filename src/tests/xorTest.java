package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import algorithms.xor;

public class xorTest {

	@Test
	public void simpleTest() {
		
		xor test = new xor();
		String originalStr = "Encryption Test", encryptedStr,decryptedStr;

		encryptedStr = test.encrypt(originalStr, 41);
		decryptedStr = test.decrypt(encryptedStr, 41);
		
		assertEquals(originalStr,decryptedStr);
		
	}
	
	@Test
	public void differentKeysTest() {
		
		xor test = new xor();
		String originalStr = "Encryption Test", encryptedStr,decryptedStr;

		encryptedStr = test.encrypt(originalStr, 41);
		decryptedStr = test.decrypt(encryptedStr, 188);
		
		assertNotEquals(originalStr,decryptedStr);
		
	}

}
