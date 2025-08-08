package myGame.tilesAndGameObjects.gameObjects;

import java.awt.image.BufferedImage;
import java.io.IOException;

import myGame.Utilities.KeyHandler;
import myGame.Utilities.LevelManager;


public class SnowPearl extends GameObject{
	private KeyHandler kh;
	private LevelManager lm;
	
	public SnowPearl() throws IOException {
		super();
		itemID = 3;
		super.setCrossable(true); 
        super.setPickable(true);
		
		imagePath = "/resources/images/gameObjects/snowPearl.png";
		
		loadImage();
		kh = KeyHandler.getInstance();
		lm = LevelManager.getInstance();
		
	}

	@Override
	public void behavior() {
	
	}
    
    public BufferedImage getImage() {
    	return image;
    }
    
}
