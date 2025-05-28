package tiles;

import java.awt.image.BufferedImage;

public class Tile {
	private BufferedImage image; //image holder
	private boolean crossable; //is there a collision
	private int tileSize;
	 
	public Tile(BufferedImage tileImage) {
		this.image = tileImage;
	}
	
	public int getTileSize() {
		return tileSize;
	}

	public void setTileSize(int tileSize) {
		this.tileSize = tileSize;
	}
	public BufferedImage getImage() {
		return image;
	}

	public boolean isCrossable() {
		return this.crossable; 
	}  
	
	public void setCrossable(boolean crossable) {
		this.crossable = crossable;
	}
}
