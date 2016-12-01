package main.java.encryptor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import com.sun.javafx.scene.layout.region.Margins.Converter;

public class xmlHandler {
	static String xmlAlgorithmPath = "D:\\maven-java\\encryptor\\src\\schemasAndXML\\algorithm.xml";
	static String xmlReportPath = "D:\\maven-java\\encryptor\\src\\schemasAndXML\\report.xml";
	static String xsdAlgorithmPath = "D:\\maven-java\\encryptor\\src\\schemasAndXML\\algorithmSchema.xsd";
	static String xsdReportPath = "D:\\maven-java\\encryptor\\src\\schemasAndXML\\reportSchema.xsd";
	
	
	public static int getAlgorithm()
	{
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File(xmlAlgorithmPath);
		int algorithmNum = -1;
		Document doc;
		try {
			doc = (Document) builder.build(xmlFile);
		
		Element rootNode = doc.getRootElement();		
		Element staff = rootNode.getChild("algorithm");
		algorithmNum = Integer.parseInt(staff.getChildText("type"));	
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return algorithmNum;
	}
	
	public static void setNewAlgorithm(int algoNum)
	{
		 try {

				SAXBuilder builder = new SAXBuilder();
				File xmlFile = new File(xmlAlgorithmPath);

				Document doc = (Document) builder.build(xmlFile);
				Element rootNode = doc.getRootElement();
				Element staff = rootNode.getChild("algorithm");
				
				staff.getChild("type").setText(String.valueOf(algoNum));

				XMLOutputter xmlOutput = new XMLOutputter();

				xmlOutput.setFormat(Format.getPrettyFormat());
				xmlOutput.output(doc, new FileWriter(xmlAlgorithmPath));

				System.out.println("algorithm updated!");
			  } catch (IOException io) {
				io.printStackTrace();
			  } catch (JDOMException e) {
				e.printStackTrace();
			  }
	}
	
	public static void updateReport(String type,String executionTime,String exeptionName,String exeptionMessage,String exeptionStackTrace)
	{
		 try {

				SAXBuilder builder = new SAXBuilder();
				File xmlFile = new File(xmlReportPath);

				Document doc = (Document) builder.build(xmlFile);
				Element rootNode = doc.getRootElement();

				Element staff = rootNode.getChild("report");

				// update salary value
				staff.getChild("type").setText(String.valueOf(type));
				staff.getChild("executionTime").setText(String.valueOf(executionTime));
				staff.getChild("exeptionName").setText(String.valueOf(exeptionName));
				staff.getChild("exeptionMessage").setText(String.valueOf(exeptionMessage));
				staff.getChild("exeptionStackTrace").setText(String.valueOf(exeptionStackTrace));

				XMLOutputter xmlOutput = new XMLOutputter();

				xmlOutput.setFormat(Format.getPrettyFormat());
				xmlOutput.output(doc, new FileWriter(xmlReportPath));

				System.out.println("report updated!");
			  } catch (IOException io) {
				io.printStackTrace();
			  } catch (JDOMException e) {
				e.printStackTrace();
			  }
	
	}
}
