package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import algorithms.caesar;
import algorithms.doubleAlgo;
import algorithms.xor;

public class doubleTest {

	@Test
	public void basicTest() {
		doubleAlgo test = new doubleAlgo();
		String originalStr = "Encryption Test", encryptedStr,decryptedStr;
		int[] key = new int[2];
		key[0]=2;key[1]=14;
		encryptedStr = test.encrypt(originalStr, key);
		decryptedStr = test.decrypt(encryptedStr, key);
		assertEquals(originalStr,decryptedStr);
	}
	
	@Test
	public void encryptionTest() {
		doubleAlgo test = new doubleAlgo();
		caesar testCeasar = new caesar();
		xor testXor = new xor();
		String originalStr = "Encryption Test",doubleEncryption,encryptedStr1,encryptedStr2;
		int[] key = new int[2];
		key[0]=2;key[1]=14;
		doubleEncryption = test.encrypt(originalStr, key);
		encryptedStr1 = testCeasar.encrypt(originalStr, key);
		int[] key2 = new int[1];
		key2[0]=key[1];
		encryptedStr2 = testXor.encrypt(encryptedStr1, key2);
		
		assertEquals(doubleEncryption,encryptedStr2);
		
	}

}

