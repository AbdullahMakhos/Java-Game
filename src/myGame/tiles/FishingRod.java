package myGame.tiles;

import java.io.IOException;

public class FishingRod extends GameObject{
	private final int ID = 4;
	
	
	public FishingRod() throws IOException {
		super();
		super.setCrossable(true); 
		super.setPickable(true);
		
		itemID = ID;
		imagePath = "/resources/FishingRod.png";
		
		loadImage();
	}
	
	@Override
	public void behavior() {
		gp.setGameState(3);
	}
}
