package objects;

import java.awt.image.BufferedImage;

import main.GamePanel;
import main.KeyHandler;

public class Door extends GameObject{
	GamePanel gp;
	KeyHandler kh;
	int currentImageID = 1;
	BufferedImage image2;

	public Door(GamePanel gp) {
		super();
		this.gp = gp;
		this.kh = gp.getKeyHandler();
	}

	@Override
	public void behavior() {
		if(kh.oPressed) {
			//open
			setCrossable(true);
			currentImageID = -1;
		}else {
			//closed
			setCrossable(false);
			currentImageID = 1;
		}
	}
	
    public void setImage2(BufferedImage image) {
    	this.image2 = image;
    }
    
    public BufferedImage getImage() {
    	return (currentImageID == 1) ? image : image2;
    }

}
