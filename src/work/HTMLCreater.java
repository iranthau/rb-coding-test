package work;

/*****************************************************************
 * Class handles all functions related to creating HTML page. 
 * Has all the html tags and etc.
 * *************************************************************/

import java.util.ArrayList;

public class HTMLCreater {
	private String title;
	private ArrayList<String> aTags;
	private ArrayList<String> imgTags;
	private String styles;
	
	public HTMLCreater() {
		this.title = "";
		this.aTags = new ArrayList<>();
		this.imgTags = new ArrayList<>();
		this.styles = "<style type='text/css'>\n" + 
						"h1 { margin: 20px; text-align: center; }\n" + 
						"nav { text-align: center; margin: 10px; }\n" + 
						"nav a { text-decoration: none; margin: 10px; }\n" +
						"section { margin: 20px; text-align: center; }\n" +
						"</style>\n";
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void addATags(String aTag) {
		aTags.add(aTag);
	}

	public void addImgTag(String imgTag) {
		String htmlTag = "<img src='" + imgTag + "' alt='' />\n";
		imgTags.add(htmlTag);
	}
	
	// A method to create a file name for the saved html files. This method 
	// returns a formatted word when a word is provided. Max length of characters is
	// set to 15 and all in lower case.
	public String generateFileName(String word) {
		String fileName = "";
		if(word.contains(" ")) {
			fileName = word.replaceAll("\\s", "_").toLowerCase();
			if(fileName.length() > 15) {
				fileName = fileName.substring(0, 15);
			}
		} else {
			fileName = word.toLowerCase();
		}
		return fileName;
	}
	
	public String createHTMLPage() {
		return "<!DOCTYPE html>\n<html>\n" + createHTMLHead() + createBody() + "</html>";
	}
	
	//-------------------------- Class only methods ----------------------------------
	
	// Functions below creates each section of the html page separately.
	private String createHTMLHead() {
		return "<head>\n<title>" + title + "</title>\n" + styles + "</head>\n";
	}
	
	private String createHTMLHeader() {
		return "<header>\n<h1>" + title + "</h1>" + createNav() + "</header>\n";
	}
	
	private String createNav() {
		String navTabs = "";
		for(int i = 0; i < aTags.size(); i++) {
			navTabs += "<a href='./" + generateFileName(aTags.get(i)) + ".html'>" + aTags.get(i) + "</a>\n";
		}
		return "\n<nav>\n" + navTabs + "</nav>\n";
	}
	
	private String createImageSection() {
		String images = "";
		for(int i = 0; i < imgTags.size(); i++) {
			images += imgTags.get(i);
		}
		return "<section>\n" + images + "</section>\n";
	}
	
	private String createBody() {
		return "<body>\n" + createHTMLHeader() + createImageSection() + "</body>\n";
	}
}
