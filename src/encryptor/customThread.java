package encryptor;

import java.nio.file.Path;

import algorithms.algorithmInterface;
import algorithms.doubleAlgo;

public class customThread extends Thread {
	
	 private Path path;
	 private int funcMode;
	 private algorithmInterface aI;

	    public customThread(Path path, int funcMode,algorithmInterface aI ) {
	        this.path = path;
	        this.funcMode = funcMode;
	        this.aI = aI;
	        
	    }

	    @Override
	    public void run() {
	    	aI.runAlgorithm(path, funcMode);
	    }
}
