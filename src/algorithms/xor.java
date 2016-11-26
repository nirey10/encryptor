package algorithms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.Random;
import java.util.Scanner;

public class xor implements algorithmInterface{

	
	@Override
	public void runAlgorithm(Path path, int mode) {

		BufferedReader br = null;
		
		try{
		    PrintWriter writer = null;	
		    File file;
		    //D:\maven-java\encryptor\src\encryptor\algorithmTest.txt
		    //D:\maven-java\encryptor\the-file-name.txt
		    long startTime, estimatedTime;
			String sCurrentLine;
			br = new BufferedReader(new FileReader(path.toString()));
			
			System.out.println("please enter the algorithm key between "+ MINVALUE +" and " + MAXVALUE );
			int keyInput;
			Scanner scanner = new Scanner(System.in);
			keyInput=(scanner.nextInt());		
			
			switch (mode)
			{
				case 0:	
					file = new File("D:\\maven-java\\encryptor\\encrypted\\" + path.getFileName());
					
					writer = new PrintWriter(file, "UTF-8");		
					
					System.out.println("Starting xor encryption");	//counting time
					startTime = System.nanoTime();
					
					while ((sCurrentLine = br.readLine()) != null) 
						writer.println(encrypt(sCurrentLine,keyInput));	
					
					estimatedTime = System.nanoTime()-startTime;//counting time
					System.out.println("Encription ended within "+estimatedTime + " nano seconds");
					break;
					
				case 1:
					file = new File("D:\\maven-java\\encryptor\\decrypted\\" + path.getFileName());
					
					writer = new PrintWriter(file, "UTF-8");
					
					System.out.println("Starting xor decryption");	//counting time
					startTime = System.nanoTime();	
					
					while ((sCurrentLine = br.readLine()) != null) 
						writer.println(decrypt(sCurrentLine,keyInput));	
					
					estimatedTime = System.nanoTime()-startTime;//counting time
					System.out.println("Decryption ended within "+estimatedTime + " nano seconds");
					break;
					
			}										
			
			writer.close();
				
		} catch (Exception e) {
			   // do something
		}					
		
	}

	@Override
	public String encrypt(String line, int key) {
		
		char [] encryptedString;
		int length = line.length();
		
		encryptedString = line.toCharArray();
		
		for (int i=0;i<length;i++)
		{
			Byte algorithmKey = (byte) key;
			encryptedString[i] = (char) (encryptedString[i] ^ algorithmKey);			
		}
				
		return String.valueOf(encryptedString);	//returns the encrypted line	
	}

	@Override
	public String decrypt(String line, int key) {
		char [] decryptedString;
		int length = line.length();
		
		decryptedString = line.toCharArray();
		
		for (int i=0;i<length;i++)
		{
			Byte algorithmKey = (byte) key;
			decryptedString[i] = (char) (decryptedString[i] ^ algorithmKey);			
		}
				
		return String.valueOf(decryptedString);	//returns the encrypted line	
	}

}
