package algorithms;

import java.nio.file.Path;

public interface algorithmInterface {
	
	int MAXVALUE = 127;
	int MINVALUE = 0;
	
	
	public void runAlgorithm (Path path, int mode);
	public String encrypt(String line,int key);
	public String decrypt(String line,int key);

}
