package algorithms;

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

	@Override
	public void runAlgorithm(Path path, int mode) {
		
		BufferedReader br = null;
		int [] keys = new int[2];
		
		caesar caesarAlgorithm = new caesar();
		xor xorAlgorithm = new xor();
		
		try{
		    PrintWriter writer = null;	
		    File file=null;		    		    
		    
		    //D:\maven-java\encryptor\src\encryptor\algorithmTest.txt
		    //D:\maven-java\encryptor\encrypted\algorithmTest.txt
		    long startTime, estimatedTime;
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
					System.out.println("Starting caesar encryption");	 //counting time
					startTime = System.nanoTime();
					
					while ((sCurrentLine = br.readLine()) != null)
					{
						String tempEncryption = caesarAlgorithm.encrypt(sCurrentLine,keys[0]);
						writer.println(xorAlgorithm.encrypt(tempEncryption,keys[1]));
						
					}
					
					estimatedTime = System.nanoTime()-startTime; //counting time
					System.out.println("Encription ended within "+estimatedTime + " nano seconds");
					
					break;
					
				case 1:
					file = new File("D:\\maven-java\\encryptor\\decrypted\\" + path.getFileName());
					
					writer = new PrintWriter(file, "UTF-8");
					
					System.out.println("Starting caesar decryption"); //counting time
					startTime = System.nanoTime();
					
					
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
						String tempDecryption = xorAlgorithm.decrypt(sCurrentLine,keys[1]);
						writer.println(caesarAlgorithm.decrypt(tempDecryption,keys[0]));	
					}
					
					estimatedTime = System.nanoTime()-startTime; //counting time
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String decrypt(String line, int key) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
