/**
 * 
 */
package main.java.encryptor;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import jdk.internal.org.xml.sax.SAXException;
import main.java.algorithms.*;



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
	
	static String exeptionName,exeptionStackTrace,exeptionMessage;
	
	public static void main(String[] args)
	{		
		//validation xml schemas
		System.out.println("validating xml schemas...");
		 boolean isValid = generalMethods.validateXMLSchema(xmlHandler.xsdAlgorithmPath,xmlHandler.xmlAlgorithmPath);
		 System.out.println("algorithm xml validation = " + isValid);
		 isValid = generalMethods.validateXMLSchema(xmlHandler.xsdReportPath,xmlHandler.xmlReportPath);
		 System.out.println("report xml validation = " + isValid);
				 
		 //creating algorithm objects
		caesarAlgorithm = new caesar();
		xorAlgorithm = new xor();
		multiplicationAlgorithm = new multiplication();
		doubleAlgorithm = new doubleAlgo();
		reverseAlgorithm = new reverse();
		
		functionMode=readMode(); //reading mode, encryption or decryption
		path = readPath();	//getting the path from the user
		int option;
		Scanner scanner = new Scanner(System.in); //deciding from where to load the algorithm
		System.out.println("Enter 0 for xml algorithm and 1 for new algorithm");
		option=(scanner.nextInt());
		if (option==0)
		{
			algorithm=xmlHandler.getAlgorithm();
			readAlgorithm(algorithm); //setting function pointers
		}
		else
		{
			algorithm = readAlgorithm(-1);
			System.out.println("press 0 if you want to update the xml file to that algorithm");
			option=(scanner.nextInt()); //updating algorithm xml for the user choice
			if (option==0)
				xmlHandler.setNewAlgorithm(algorithm);
		}				
		Path filePath = Paths.get(path);
		System.out.println("Starting Encryption/Decryption process");
		Timer time= new Timer();
		time.startTimer();
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
		time.stopTimer();
		time.printTime();	
		
		if (path==null) //updating the report xml file
		{
			xmlHandler.updateReport(String.valueOf(algorithm), "failed", exeptionName, exeptionMessage, exeptionStackTrace);
		}
		else
		{
			xmlHandler.updateReport(String.valueOf(algorithm), String.valueOf(time.getTime()), "succeed", "succeed", "succeed");
		}
		
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
					new customThread(item.toPath(), functionMode, aI).start();
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
	
	private static int readAlgorithm(int algo) { 
		int type;
		
		if(algo==-1)
		{
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter the algorithm type: \n0 for caesar\n1 for xor\n2 for multiplication\n3 for double\n4 for reverse");
			type=(scanner.nextInt());
		}
		else
			type=algo;
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
				break;
			default:
				System.out.println("Eror in input, enter again");
				readAlgorithm(type);
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
				inputPath=null;
				readPath();				
			} catch (IOException e) {
				exeptionName = "IOException";
				exeptionStackTrace = e.getStackTrace().toString();
				exeptionMessage = e.getMessage();
				inputPath=null;
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
