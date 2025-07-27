package myGame.tiles;

import java.io.IOException;

public class SnowMan extends GameObject{
	private final int ID = 5;
	
	public SnowMan() throws IOException {
		super();
		super.setCrossable(false); 
		super.setPickable(false);
		itemID = ID;
		imagePath = "/resources/images/external-pack/snowman1.png";
		
		loadImage();
	}
	public void behavior() {
		
	}
}
