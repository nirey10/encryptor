package algorithms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.util.Random;

public class reverse implements algorithmInterface {


	@Override
	public void runAlgorithm(Path path, int mode) {
		BufferedReader br = null;
		int key=0;
		
		caesar caesarAlgorithm = new caesar();
		xor xorAlgorithm = new xor();
		
		try{
		    PrintWriter writer = null;	
		    File file=null;		    		    
		    
		    //D:\maven-java\encryptor\src\encryptor\algorithmTest.txt
		    //D:\maven-java\encryptor\encrypted\algorithmTest.txt
		    long startTime, estimatedTime;
			String sCurrentLine;
			br = new BufferedReader(new FileReader(path.toString()));
			
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
						key=Integer.parseInt(sCurrentLineKey);
						i++;				
					}
					
					System.out.println("Starting caesar encryption");	 //counting time
					startTime = System.nanoTime();
					
					while ((sCurrentLine = br.readLine()) != null)
					{						
						writer.println(xorAlgorithm.decrypt(sCurrentLine,key));						
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
						key=Integer.parseInt(sCurrentLineKey);
						i++;				
					}
					br = new BufferedReader(new FileReader(path.toString()));
					while ((sCurrentLine = br.readLine()) != null) 
					{
						writer.println(xorAlgorithm.encrypt(sCurrentLine,key));	
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
