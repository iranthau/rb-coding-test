package camera;

import java.util.ArrayList;

public class CameraModel {
	private String name;
	private ArrayList<String> images;
	private String make;
	
	public CameraModel(String name) {
		this.name = name;
		images = new ArrayList<>();
	}
	
	public String getName() {
		return name;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String model) {
		this.make = model;
	}

	public ArrayList<String> getImages() {
		return images;
	}
	
	public void addImage(String image) {
		images.add(image);
	}
}
