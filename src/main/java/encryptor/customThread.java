package main.java.encryptor;

import java.nio.file.Path;

import main.java.algorithms.algorithmInterface;
import main.java.algorithms.doubleAlgo;

public class customThread extends Thread {
	
	 private Path path;
	 private int funcMode;
	 private algorithmInterface aI;
	 
	    public customThread(Path path, int funcMode,algorithmInterface aI ) { //constructor
	        this.path = path;
	        this.funcMode = funcMode;
	        this.aI = aI;
	        
	    }

	    @Override
	    public void run() {
	    	aI.runAlgorithm(path, funcMode);  //running the encryption/decryption operation
	    }
}
