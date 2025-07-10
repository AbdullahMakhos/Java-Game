package tiles;

import java.awt.image.BufferedImage;
import java.io.IOException;

import levelsAndSaving.LevelManager;
import main.KeyHandler;

public class Door extends GameObject{
	private KeyHandler kh;
	private LevelManager lm;
	@SuppressWarnings("unused")
	private final int ID = 1;
	private int counter = 0; 
	
	public Door() throws IOException {
		super();
		super.setCrossable(false);
		super.setPickable(false);
		itemID = ID;
		
		imagePath = "/tiles/resources/closedDoor.png";
		

		kh = gp.getKeyHandler();
		lm=gp.getLevelManager();
		
		loadImage();
	}

	@Override
	public void behavior() {
		if(kh.spacePressed) {
			counter++;
			if(counter > 9) {
				resetCounter();
				lm.doorMovement();				
			}
		}
	}
    
    public BufferedImage getImage() {
    	return image;
    }
    
    private void resetCounter() {
    	counter = 0;
    }
}
