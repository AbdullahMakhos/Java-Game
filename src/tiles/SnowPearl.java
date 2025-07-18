package tiles;

import java.awt.image.BufferedImage;
import java.io.IOException;


import levelsAndSaving.LevelManager;
import main.KeyHandler;

public class SnowPearl extends GameObject{
	private KeyHandler kh;
	private LevelManager lm;
	@SuppressWarnings("unused")
	private final int ID = 3;
	private int counter = 0; 
	
	public SnowPearl() throws IOException {
		super();
		itemID = ID;
		super.setCrossable(true); 
        super.setPickable(true);
		
		imagePath = "/tiles/resources/snowPearl.png";
		
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
