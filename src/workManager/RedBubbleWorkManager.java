package workManager;

import java.util.Scanner;

import work.XMLReader;
import work.XMLWriter;

/******************************************************
 * @author iranr
 * Created: 24th October 2015
 * Main Class.
 ******************************************************/
public class RedBubbleWorkManager {
	public static void main(String[] args) throws Exception {
		
		String pathToInputFile; 
		String pathToOutPutFolder; 
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("---------------- Welcome To RedBubble ------------------\n\n");
		System.out.print("Tell me what do you want me to read \n"
				+ "If no input I will read \nfrom src/inputs/works.xml: ");
		
		pathToInputFile = input.nextLine();
		
		// By default the program read from the works.xml in the inputs folder.
		if(pathToInputFile.isEmpty()) {
			pathToInputFile = "src/inputs/works.xml";
		}
		
		System.out.println("\n\nWhere would you like me to save this project\n"
				+ "If no input I will save output files to \n/Users/iranr/Downloads/redbubble/: ");
		
		pathToOutPutFolder = input.nextLine();
		
		// By default program outputs to a folder named redbubble in the downloads folder.
		if(pathToOutPutFolder.isEmpty()) {
			pathToOutPutFolder = "/Users/iranr/Downloads/redbubble/";
		}
		
		
		XMLReader xmlReader = new XMLReader(pathToInputFile);
		XMLWriter xmlWriter = new XMLWriter(xmlReader, pathToOutPutFolder);
		xmlWriter.createHTMLPages();
		
		System.out.println("\nProgram executed successfully.\n");
		System.out.println("---------------- Thank you ------------------\n\n");
		
		input.close();
	}
}
