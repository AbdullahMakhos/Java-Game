package myGame.tilesAndGameObjects.gameObjects;

import java.awt.image.BufferedImage;
import java.io.IOException;

import myGame.Utilities.KeyHandler;
import myGame.Utilities.LevelManager;


public class Door extends GameObject{
	private KeyHandler kh;
	private LevelManager lm;
	private int counter = 0; 
	
	public Door() throws IOException {
		super();
		super.setCrossable(false);
		super.setPickable(false);
		itemID = 1;
		
		imagePath = "/resources/images/gameObjects/closedDoor.png";

		kh = KeyHandler.getInstance();
		lm = LevelManager.getInstance();
		
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
