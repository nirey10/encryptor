package main.java.algorithms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.Random;
import java.util.Scanner;





public class caesar implements algorithmInterface {
	
	
	public void runAlgorithm (Path path, int mode) // manages the user ui for the specific algorithm
	{			
		
		BufferedReader br = null;
		
		try{
		    PrintWriter writer = null;	
		    File file=null;
		    int[] keys = new int[1];
			String sCurrentLine;
			br = new BufferedReader(new FileReader(path.toString()));		
			
			switch (mode)
			{
				case 0:	
					file = new File("D:\\maven-java\\encryptor\\encrypted\\" + path.getFileName());
					
					writer = new PrintWriter(file, "UTF-8");					
					Random rn = new Random(); //creating a random key between 0-255
					int n = MAXVALUE - MINVALUE + 1;
					int randomKey = rn.nextInt() % n;	
					
					if (randomKey<0)
						randomKey = -randomKey;
					System.out.println("The random key is: "+randomKey);
					
					System.out.println("Starting caesar encryption");	 //counting time
					
					keys[0]=randomKey;
					while ((sCurrentLine = br.readLine()) != null) 						
						writer.println(encrypt(sCurrentLine,keys));
				
					
					
					break;
					
				case 1:
					file = new File("D:\\maven-java\\encryptor\\decrypted\\" + path.getFileName());
					
					writer = new PrintWriter(file, "UTF-8");
					System.out.println("please enter a decryption key between "+ MINVALUE +" and " + MAXVALUE );
					int keyInput;
					Scanner scanner = new Scanner(System.in);
					keyInput=(scanner.nextInt());	
					System.out.println("Starting caesar decryption"); //counting time
						
					keys[0] = keyInput;
					while ((sCurrentLine = br.readLine()) != null) 
						writer.println(decrypt(sCurrentLine,keys));	
					
					
					break;
					
			}										
			
			writer.close();						
		} catch (Exception e) {
			   // do something
		}					
	}
	
	
	public String encrypt(String line,int[] key)
	{
		char [] encryptedString;
		int length = line.length();
		
		encryptedString = line.toCharArray();
		
		for (int i=0;i<length;i++)
		{
			
			encryptedString[i] = (char) ((int)encryptedString[i] + key[0]);			
			encryptedString[i] = (char) ((int)encryptedString[i]%MAXVALUE);//checking boundaries						
		}
				
		return String.valueOf(encryptedString);	//returns the encrypted line	
	}
	
	public String decrypt(String line,int[] key)
	{
		
		char [] decryptedString;
		int length = line.length();
		int newChar;
		decryptedString = line.toCharArray();
		
		for (int i=0;i<length;i++)
		{
			newChar = ((int)decryptedString[i] - key[0]);
			if (newChar<0)
				decryptedString[i] = (char) (newChar + MAXVALUE);
			else
				decryptedString[i] = (char) newChar;
		}
				
		return String.valueOf(decryptedString);	//returns the encrypted line		
	}	

}
