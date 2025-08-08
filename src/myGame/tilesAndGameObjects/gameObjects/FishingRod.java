package myGame.tilesAndGameObjects.gameObjects;

import java.io.IOException;

public class FishingRod extends GameObject{
	
	public FishingRod() throws IOException {
		super();
		super.setCrossable(true); 
		super.setPickable(true);
		
		itemID = 4;
		imagePath = "/resources/images/gameObjects/fishingRod.png";
		
		loadImage();
	}
	
	@Override
	public void behavior() {
		gp.setGameState(3);
	}
}
