package main.java.algorithms;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.Scanner;

public class multiplication implements algorithmInterface{

	private int randomKey=0;
	
	
	public void runAlgorithm(Path path, int mode) {
		BufferedReader br = null;
		
		try{
		    PrintWriter writer = null;		       		    		
		    //D:\maven-java\encryptor\src\encryptor\algorithmTest.txt
		    //D:\maven-java\encryptor\the-file-name.txt

			String sCurrentLine;
			br = new BufferedReader(new FileReader(path.toString()));
			
			System.out.println("please enter the algorithm key between "+ MINVALUE +" and " + MAXVALUE );
			int keyInput;
			Scanner scanner = new Scanner(System.in);
			keyInput=(scanner.nextInt());		
			
			switch (mode)
			{
				case 0:	
					writer = new PrintWriter("the-file-name.txt", "UTF-8");
					
					while ((sCurrentLine = br.readLine()) != null) 
						//writer.println(encrypt(sCurrentLine,randomKey));											
					break;
					
				case 1:
					writer = new PrintWriter("the-file-name-decrypt.txt", "UTF-8");
								
					while ((sCurrentLine = br.readLine()) != null) 
						//writer.println(decrypt(sCurrentLine,keyInput));											
					break;
					
			}										
			
			writer.close();
				
		} catch (Exception e) {
			   // do something
		}					
		
	}

	
	public String encrypt(String line, int[] key) {
		char [] encryptedString;
		int length = line.length();
		
		encryptedString = line.toCharArray();
		
		for (int i=0;i<length;i++)
		{
			//Byte algorithmKey = (byte) key;
			//encryptedString[i] = (char) (encryptedString[i] ^ algorithmKey);			
		}
				
		return String.valueOf(encryptedString);	//returns the encrypted line	
	}

	
	public String decrypt(String line, int[] key) {
		char [] decryptedString;
		int length = line.length();
		
		decryptedString = line.toCharArray();
		
		for (int i=0;i<length;i++)
		{
			//Byte algorithmKey = (byte) key;
			//decryptedString[i] = (char) (decryptedString[i] ^ algorithmKey);			
		}
				
		return String.valueOf(decryptedString);	//returns the encrypted line	
	}

}
