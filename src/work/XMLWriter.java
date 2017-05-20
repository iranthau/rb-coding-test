package work;

/****************************************************
 * This class handle all task in related to creating
 * HTML output.
 * **************************************************/

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import camera.CameraMake;
import camera.CameraModel;
import exception.WriteFileException;

public class XMLWriter {
	
	private XMLReader xmlReader;
	private String pathToFolder;
	
	public XMLWriter(XMLReader xmlReader, String pathToFolder) {
		this.xmlReader = xmlReader;
		this.pathToFolder = pathToFolder;
	}
	
	public void createHTMLPages() throws Exception {
		System.out.println("Creating html files....");
		createIndexPage();
		for(CameraMake make: xmlReader.getCameraMakeList()) {
			createCameraMakePage(make);
			for(CameraModel model: make.getModelList()) {
				createCameraModelPage(model, make);
			}
		}
	}
	
	//---------------------- Class only Methods ----------------------------
	
	private void createIndexPage() throws Exception {
		HTMLPage indexPage = new HTMLPage("Index");
		for(CameraMake make: xmlReader.getCameraMakeList()) {
			indexPage.addNavLinks(make.getName());
		}
		indexPage.addImages(xmlReader.getThumbnailsForIndexPage());
		saveFiles(indexPage);
	}
	
	private void createCameraMakePage(CameraMake make) throws Exception {
		HTMLPage makePage = new HTMLPage(make.getName());
		makePage.addNavLinks("Index");
		for(CameraModel model: make.getModelList()) {
			makePage.addNavLinks(model.getName());
		}
		makePage.addImages(make.getImageList());
		saveFiles(makePage);
	}
	
	private void createCameraModelPage(CameraModel model, CameraMake make) throws Exception {
		HTMLPage modelPage = new HTMLPage(model.getName());
		modelPage.addNavLinks("Index");
		modelPage.addNavLinks(make.getName());
		modelPage.addImages(model.getImages());
		saveFiles(modelPage);
	}
	
	private void saveFiles(HTMLPage page) throws WriteFileException {
		File file = new File(pathToFolder + page.getFileName(page.getPageName()) + ".html");
		
		if (!file.exists()) {
			file.getParentFile().mkdirs();
		}
		
		try {
			FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(page.generatePage());
			bufferedWriter.close();
		} catch (IOException e) {
			System.err.println("Could not save files. Try again.");
			e.printStackTrace();
		}
	}
}
