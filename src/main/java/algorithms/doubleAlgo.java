package main.java.algorithms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.util.Random;
import java.util.Scanner;

public class doubleAlgo implements algorithmInterface {

	
	public void runAlgorithm(Path path, int mode) {
		
		BufferedReader br = null;
		int [] keys = new int[2];
			
		try{
		    PrintWriter writer = null;	
		    File file=null;		    		   
			String sCurrentLine;		
			
			switch (mode)
			{
				case 0:	
					file = new File("D:\\maven-java\\encryptor\\encrypted\\" + path.getFileName());
					
					writer = new PrintWriter(file, "UTF-8");	
					//System.out.println("Generating keys");
					String sCurrentLineKey;
					br = new BufferedReader(new FileReader("D:\\maven-java\\encryptor\\keys\\key.bin"));
					int i=0;
					while ((sCurrentLineKey = br.readLine()) != null)
					{
						keys[i]=Integer.parseInt(sCurrentLineKey);
						i++;				
					}
					br = new BufferedReader(new FileReader(path.toString()));
					
					while ((sCurrentLine = br.readLine()) != null)
					{
						writer.println(encrypt(sCurrentLine,keys));					
					}
					
				
					break;
					
				case 1:
					file = new File("D:\\maven-java\\encryptor\\decrypted\\" + path.getFileName());
					
					writer = new PrintWriter(file, "UTF-8");
					
					br = new BufferedReader(new FileReader("D:\\maven-java\\encryptor\\keys\\key.bin"));
					i=0;
					while ((sCurrentLineKey = br.readLine()) != null)
					{
						keys[i]=Integer.parseInt(sCurrentLineKey);
						i++;				
					}
					br = new BufferedReader(new FileReader(path.toString()));
					while ((sCurrentLine = br.readLine()) != null) 
					{
						writer.println(decrypt(sCurrentLine,keys));	
					}
									
					break;				
			}												
			writer.close();		
			
		} catch (Exception e) {
			   // do something
		}					
		
	}
	
	public String encrypt(String line, int[] key) {

		caesar caesarAlgorithm = new caesar();
		xor xorAlgorithm = new xor();
		
		String tempEncryption = caesarAlgorithm.encrypt(line,key);
		int[] secondKey = new int[1];
		secondKey[0] = key[1];
		String finalEncryption = xorAlgorithm.encrypt(tempEncryption,secondKey);		
		
		return finalEncryption;
	}

	
	public String decrypt(String line, int[] key) {

		caesar caesarAlgorithm = new caesar();
		xor xorAlgorithm = new xor();
		int[] secondKey = new int[1];
		secondKey[0] = key[1];
		String tempdecryption = xorAlgorithm.decrypt(line,secondKey);
		
		String finalDecryption = caesarAlgorithm.decrypt(tempdecryption,key);		
		
		return finalDecryption;


	}
	

}
