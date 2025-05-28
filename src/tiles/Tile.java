package tiles;

import java.awt.image.BufferedImage;

public class Tile {
	public BufferedImage tileImage; //image holder
	boolean collidable; //is there a collision
	 
	public Tile(BufferedImage tileImage) {
		this.tileImage = tileImage;
	}
	
	public boolean isSolid() {
		return this.collidable; 
	}  
}
