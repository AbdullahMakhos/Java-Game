package objects;

import java.awt.image.BufferedImage;

import levels.LevelManager;
import main.GamePanel;
import main.KeyHandler;

public class Door extends GameObject{
	private GamePanel gp;
	private KeyHandler kh;
	private LevelManager lm;
	private int counter = 0; 
	
	public Door(GamePanel gp) {
		super(gp);
		kh = gp.getKeyHandler();
		lm=gp.getLevelManager();
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
