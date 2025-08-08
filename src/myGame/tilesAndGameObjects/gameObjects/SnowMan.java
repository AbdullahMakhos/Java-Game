package myGame.tilesAndGameObjects.gameObjects;

import java.io.IOException;

public class SnowMan extends GameObject{
	
	public SnowMan() throws IOException {
		super();
		super.setCrossable(false); 
		super.setPickable(false);
		itemID = 5;
		imagePath = "/resources/images/external-pack/snowman1.png";
		
		loadImage();
	}
	public void behavior() {
		
	}
}
