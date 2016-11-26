package encryptor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;

public class generalMethods {
	
	private static final int MAXVALUE = 127;
	private static final int MINVALUE = 0;
	
	public static int[] generateKeys()
	{
		PrintWriter writer = null;	
	    File file=null;
	    int [] keys = new int[2];
		file = new File("D:\\maven-java\\encryptor\\keys\\key.bin" );			
		try {		
			writer = new PrintWriter(file, "UTF-8");
			Random rn = new Random(); //creating a random key between 0-127
			int n = MAXVALUE - MINVALUE + 1;
			int randomKey = rn.nextInt() % n;			
			if (randomKey<0)
				randomKey = -randomKey;
			keys[0]=randomKey;
			writer.println(randomKey);
			
			randomKey = rn.nextInt() % n;
			if (randomKey<0)
				randomKey = -randomKey;
			keys[1]=randomKey;
			writer.println(randomKey);
				    
			writer.close();					
						
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return keys;
		
		
	}
	
	public static int generateKey()
	{
		PrintWriter writer = null;	
	    File file=null;
	    int key=0;
		file = new File("D:\\maven-java\\encryptor\\keys\\key.bin" );			
		try {		
			writer = new PrintWriter(file, "UTF-8");
			Random rn = new Random(); //creating a random key between 0-127
			int n = MAXVALUE - MINVALUE + 1;
			int randomKey = rn.nextInt() % n;			
			if (randomKey<0)
				randomKey = -randomKey;
			key=randomKey;
			writer.println(randomKey);
			writer.close();					
						
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return key;
		
		
	}

}
