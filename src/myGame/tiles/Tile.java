package myGame.tiles;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import myGame.core.GamePanel;


public class Tile {
	private BufferedImage image; //image holder
	private boolean crossable; //is there a collision
	private int tileSize;
	 
	public Tile() {
		setCrossable(true);
		setTileSize(GamePanel.getInstance().getTileSize());
	}
	
	public int getTileSize() {
		return tileSize;
	}

	public void setTileSize(int tileSize) {
		this.tileSize = tileSize;
	}
	
	public void setImage(BufferedImage image) {
		this.image = image;
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
	
	public static Tile createFromID(int itemId) throws IOException {
		Tile tile = new Tile();
    	
		switch (itemId) {
	        case 0:{
	        	tile.setImage(ImageIO.read(
	                    Tile.class.getResourceAsStream("/resources/snow.png")));
	        	tile.setCrossable(true); // Snow tile is crossable
	        	
	        	return tile;
	        }
	        case 1:{
	        	tile.setImage(ImageIO.read(
	        			Tile.class.getResourceAsStream("/resources/tree.png")));
	        	tile.setCrossable(false); // Wall tile is not crossable
	        	return tile;
	        }
	        case 2:{
	        	tile.setImage(ImageIO.read(
	        			Tile.class.getResourceAsStream("/resources/wall.png")));
	        	tile.setCrossable(false); // snowKitty tile is not crossable
	        	return tile;
	        }
	        case 3:{
	        	tile.setImage(ImageIO.read(
	        			Tile.class.getResourceAsStream("/resources/snowKitty.png")));
	        	tile.setCrossable(false); // water tile is not crossable
	        	return tile;
	        }
	        case 4: {
	        	tile.setImage(ImageIO.read(
	        			Tile.class.getResourceAsStream("/resources/water.png")));
	        	tile.setCrossable(false); // water tile is not crossable
	        	return tile;
	        }
	        default: throw new IllegalArgumentException("Unexpected value: " + itemId);
	    }
	}
}
