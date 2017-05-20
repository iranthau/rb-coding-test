package camera;

import java.util.ArrayList;

public class CameraMake {
	private String name;
	private ArrayList<CameraModel> cameraModels;
	private ArrayList<String> images;
	
	public CameraMake(String name) {
		this.name= name;
		cameraModels = new ArrayList<>();
		images = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public ArrayList<CameraModel> getModelList() {
		return cameraModels;
	}

	public ArrayList<String> getImageList() {
		for(CameraModel model: cameraModels) {
			for(String image: model.getImages()) {
				images.add(image);
			}
		}
		return images;
	}
	
	public void addModel(CameraModel model) {
		cameraModels.add(model);
	}
}
