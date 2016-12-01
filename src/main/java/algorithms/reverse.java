package main.java.algorithms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.util.Random;

public class reverse implements algorithmInterface {


	
	public void runAlgorithm(Path path, int mode) {
		BufferedReader br = null;
		int key=0;

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
					String sCurrentLineKey;
					br = new BufferedReader(new FileReader("D:\\maven-java\\encryptor\\keys\\key.bin"));
					int i=0;
					while ((sCurrentLineKey = br.readLine()) != null)
					{
						key=Integer.parseInt(sCurrentLineKey);
						i++;				
					}
					
					System.out.println("Starting caesar encryption");	 //counting time
					br = new BufferedReader(new FileReader(path.toString()));
					keys[0]=key;
					while ((sCurrentLine = br.readLine()) != null)
					{						
						writer.println(encrypt(sCurrentLine,keys));						
					}
					
				
					break;
					
				case 1:
					file = new File("D:\\maven-java\\encryptor\\decrypted\\" + path.getFileName());
					
					writer = new PrintWriter(file, "UTF-8");
					
					System.out.println("Starting caesar decryption"); //counting time
					
					
					br = new BufferedReader(new FileReader("D:\\maven-java\\encryptor\\keys\\key.bin"));
					i=0;
				
					while ((sCurrentLineKey = br.readLine()) != null)
					{
						key=Integer.parseInt(sCurrentLineKey);
						i++;				
					}
					keys[0] = key;
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
		xor xorAlgorithm = new xor();
		String encrypted = xorAlgorithm.decrypt(line,key);

		return encrypted;
	}

	
	public String decrypt(String line, int[] key) {

		xor xorAlgorithm = new xor();
		String decrypted = xorAlgorithm.encrypt(line,key);
		
		
		return decrypted;
	}
	
	
	

}
