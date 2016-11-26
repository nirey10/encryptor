/**
 * 
 */
package encryptor;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import algorithms.algorithmInterface;
import algorithms.caesar;
import algorithms.doubleAlgo;
import algorithms.multiplication;
import algorithms.reverse;
import algorithms.xor;


/**
 * @author NirBZ
 *
 */
public class mainEX1 {
	static int functionMode; // 0 for encryption 1 for decryption
	static String path;
	static int algorithm; // 0 - caesar; 1 - xor; 2 - multiplication
	static int syncOasync=-1; // 0 - sync; 1- async
	static algorithmInterface aI;
	
	static caesar caesarAlgorithm;
	static xor xorAlgorithm;
	static multiplication multiplicationAlgorithm;
	static doubleAlgo doubleAlgorithm;
	static reverse reverseAlgorithm;
	
	public static void main(String[] args)
	{
		caesarAlgorithm = new caesar();
		xorAlgorithm = new xor();
		multiplicationAlgorithm = new multiplication();
		doubleAlgorithm = new doubleAlgo();
		reverseAlgorithm = new reverse();
		
		functionMode=readMode();
		path = readPath();	
		algorithm = readAlgorithm();
		Path filePath = Paths.get(path);
		
		if (syncOasync!=-1) // we want to do an action of an entire directory
		{
			switch (syncOasync)
			{
				case 0:
					System.out.println("Running sync operation on the directory");
					syncOperation(filePath,functionMode);
					break;
				case 1:
					System.out.println("Running async operation on the directory");
					asyncOperation(filePath,functionMode);
					break;
				default:
					System.out.println("Eror in input, enter again");
					readMode();
					break;		
		}
			
				
		}
		else
			aI.runAlgorithm(filePath,functionMode);
		
		
	}

	private static void asyncOperation(Path path, final int functionMode) {
		
		File dir = new File(path.toString());
		File[] directoryListing = dir.listFiles();
		if (directoryListing!=null)
		{
			for (final File item : directoryListing)
			{
				if (item.isDirectory()==false)
				{
					//aI.runAlgorithm(item.toPath(), functionMode);
					new customThread(item.toPath(), functionMode, aI).start();
					
					
					//D:\maven-java\encryptor\testingFolder
				}
				
			}
		}
		
	}

	private static void syncOperation(Path path, int functionMode) {
		
		File dir = new File(path.toString());
		File[] directoryListing = dir.listFiles();
		if (directoryListing!=null)
		{
			for (File item : directoryListing)
			{
				if (item.isDirectory()==false)
				{
					aI.runAlgorithm(item.toPath(), functionMode);
				}
				
			}
		}
		
	}

	private static int readMode() { // reading mode
		int mode;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter 0 for encryption and 1 for decryption:");
		mode=(scanner.nextInt());
		switch (mode)
		{
			case 0:
				System.out.println("you are in encryption mode");
				break;
			case 1:
				System.out.println("you are in decryption mode");
				break;
			default:
				System.out.println("Eror in input, enter again");
				readMode();
				break;		
	}
		return mode;
			
	}
	
	private static int readAlgorithm() { 
		int type;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the algorithm type: \n0 for caesar\n1 for xor\n2 for multiplication\n3 for double\n4 for reverse");
		type=(scanner.nextInt());
		
		//Path filePath = Paths.get(path);
		switch (type)
		{
			case 0:
				aI = caesarAlgorithm;				
				break;
			case 1:
				aI = xorAlgorithm;
				break;
			case 2:
				aI= multiplicationAlgorithm;
				break;
			case 3:
				if (functionMode==0)
					generalMethods.generateKeys();
				aI = doubleAlgorithm;
				break;
			case 4:
				if (functionMode==0)
					generalMethods.generateKey();
				aI = reverseAlgorithm;
				//reverseAlgorithm.runAlgorithm(filePath,functionMode);
				break;
			default:
				System.out.println("Eror in input, enter again");
				readAlgorithm();
				break;		
	}
		return type;
			
	}
	
	private static String readPath() //reading file path
	{
		String inputPath;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the file path:");
		inputPath=(scanner.next());
		
		
		BufferedReader br = null;					
		try {
			File file = new File(inputPath);
			if (file.isDirectory())
			{
				int mode=-1;
				while(mode!=1 && mode!=0)
				{
					scanner = new Scanner(System.in);
					System.out.println("Enter 0 for sync and 1 for async:");
					mode=(scanner.nextInt());				
					syncOasync = mode;
					if (mode!=0 && mode!=1)
						System.out.println("Eror in input, enter again");									
				}			
			}	
			else
			{
			
				String sCurrentLine;
				br = new BufferedReader(new FileReader(inputPath));	
			}
			} catch (FileNotFoundException  e) {			
				System.out.println("error reading file");
				readPath();				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		 finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
				System.out.println("error closing file");				
			}
		
		}
		
		
		
		
		return inputPath;		
	}


}
