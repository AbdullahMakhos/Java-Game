package tiles;

import java.awt.image.BufferedImage;

//objects like box fire...etc not monsters , monsters will be entities 

public abstract class Object extends Tile {
	public abstract void behavior(); // each object will override this method 
    BufferedImage image;
    
    public Object() { 
        this.setCrossable(false);
    }
    
    @Override
    public void setImage(BufferedImage image) {
    	this.image = image;
    }

}
