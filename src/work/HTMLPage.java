package work;

import java.util.ArrayList;

public class HTMLPage {
	
	private HTMLCreater htmlCreater;
	private String pageTitle; 
	
	public HTMLPage(String pageTitle) {
		this.pageTitle = pageTitle;
		htmlCreater = new HTMLCreater();
		htmlCreater.setTitle(pageTitle.toUpperCase());
	}
	
	public String getPageName() {
		return pageTitle;
	}
	
	public void addNavLinks(String navLink) {
		htmlCreater.addATags(navLink);
	}
	
	public void addImages(ArrayList<String> images) {
		for(String image: images) {
			htmlCreater.addImgTag(image);
		}
	}
	
	public String generatePage() {
		return htmlCreater.createHTMLPage();
	}
	
	public String getFileName(String word) {
		return htmlCreater.generateFileName(word);
	}
}
