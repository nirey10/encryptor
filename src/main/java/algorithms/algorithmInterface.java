package main.java.algorithms;

import java.nio.file.Path;

public interface algorithmInterface {
	
	int MAXVALUE = 127; //key values
	int MINVALUE = 0;
	
	
	public void runAlgorithm (Path path, int mode); //the algorithm manager
	public String encrypt(String line,int[] key); //encryption operation
	public String decrypt(String line,int[] key);//decryption operation

}
