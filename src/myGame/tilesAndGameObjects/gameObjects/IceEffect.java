package myGame.tilesAndGameObjects.gameObjects;

import java.io.IOException;

public class IceEffect extends GameObject {

	public IceEffect() throws IOException {
		super();
		super.setCrossable(true); 
		super.setPickable(true);
		
		itemID = 9;
		imagePath = "/resources/images/gameObjects/IceEffect.png";
		
		loadImage();
	}

}
