package myGame.tiles;

import java.awt.image.BufferedImage;
import java.io.IOException;

import myGame.Utilities.KeyHandler;
import myGame.Utilities.LevelManager;


public class SnowPearl extends GameObject{
	private KeyHandler kh;
	private LevelManager lm;
	@SuppressWarnings("unused")
	private final int ID = 3;
	
	public SnowPearl() throws IOException {
		super();
		itemID = ID;
		super.setCrossable(true); 
        super.setPickable(true);
		
		imagePath = "/resources/images/gameObjects/snowPearl.png";
		
		loadImage();
		kh = gp.getKeyHandler();
		lm=gp.getLevelManager();
		
	}

	@Override
	public void behavior() {
	
	}
    
    public BufferedImage getImage() {
    	return image;
    }
    
}
