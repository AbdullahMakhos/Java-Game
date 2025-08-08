package myGame.tilesAndGameObjects.gameObjects;

import java.io.IOException;

public class SticksPack extends GameObject {

	public SticksPack() throws IOException {
		super();
		super.setCrossable(true); 
		super.setPickable(true);
		
		itemID = 8;
		imagePath = "/resources/images/gameObjects/sticks.png";
		
		loadImage();
	}

}
