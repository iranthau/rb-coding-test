package work;

/*************************************************
 * This is class handle reading from the provided
 * file. It needs a path to the file to do so.
 * ***********************************************/

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import camera.CameraMake;
import camera.CameraModel;
import exception.ReadFileException;

public class XMLReader {
	private File inputFile;
	private DocumentBuilderFactory dbFactory;
	private DocumentBuilder dBuilder;
	private Document document;
	private NodeList allWorkInTheDocument;
	private int numberOfWork = 10;
	private HashMap<String, CameraMake> cameraMakes;
	private HashMap<String, CameraModel> cameraModels;
	
	public XMLReader(String path) throws ReadFileException {
		cameraMakes = new HashMap<>();
		cameraModels = new HashMap<>();
		inputFile = new File(path);
		dbFactory = DocumentBuilderFactory.newInstance();
		
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			document = dBuilder.parse(inputFile);
		} catch (Exception e) {
			System.err.println("Couldn't read the input file at this time. Please try again");
			e.printStackTrace();
		}

		document.getDocumentElement().normalize();
		allWorkInTheDocument = document.getElementsByTagName("work");
	}
	
	// Read the input file and loop through all the elements in the file.
	// Get each work element and process to create a list of camera makes.
	public ArrayList<CameraMake> getCameraMakeList() {
		System.out.println("Reading file....");
		CameraMake cameraMake;
		CameraModel cameraModel;
		for (int i = 0; i < allWorkInTheDocument.getLength(); i++) {
			Node workNode = allWorkInTheDocument.item(i);
			if (workNode.getNodeType() == Node.ELEMENT_NODE) {
				Element workElement = (Element) workNode;
				if(workElement.getElementsByTagName("make").getLength() != 0) {
					String makeName = workElement.getElementsByTagName("make").item(0).getTextContent();
					String modelName = workElement.getElementsByTagName("model").item(0).getTextContent();
					String pathToImage = workElement.getElementsByTagName("url").item(0).getTextContent();
					
					cameraModel = addImagesToModel(modelName, pathToImage);
					cameraModel.setMake(makeName);
					cameraMake = new CameraMake(makeName);
					cameraMakes.put(makeName, cameraMake);
				}
			}
		}
		addModelsToMake();
		return new ArrayList<>(cameraMakes.values());
	}
	
	// Index page needs only first 10 work's images
	public ArrayList<String> getThumbnailsForIndexPage() {
		ArrayList<String> indexImages = new ArrayList<>();
		for (int i = 0; i < numberOfWork; i++) {
			Node workNode = allWorkInTheDocument.item(i);
			if (workNode.getNodeType() == Node.ELEMENT_NODE) {
				Element workElement = (Element) workNode;
				if(workElement.getElementsByTagName("urls").getLength() != 0) {
					String make = workElement.getElementsByTagName("url").item(0).getTextContent();
					indexImages.add(make);
				}
			}
		}
		return indexImages;
	}
	
	// ------------------ Class only methods ------------------------
	
	private void addModelsToMake() {
		ArrayList<CameraMake> makes = new ArrayList<>(cameraMakes.values());
		ArrayList<CameraModel> models = new ArrayList<>(cameraModels.values());
		for(CameraMake make: makes) {
			for(CameraModel model: models) {
				if(make.getName().equalsIgnoreCase(model.getMake())) {
					make.addModel(model);
				}
			}
		}
	}
	
	private CameraModel addImagesToModel(String modelName, String image) {
		CameraModel model;
		if(cameraModels.containsKey(modelName)) {
			model = cameraModels.get(modelName);
			if(model.getImages().contains(image)) {
				
			} else {
				model.addImage(image);
			}
		} else {
			model = new CameraModel(modelName);
			model.addImage(image);
			cameraModels.put(modelName, model);
		}
		return model;
	}
}
