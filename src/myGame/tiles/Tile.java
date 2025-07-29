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
	                    Tile.class.getResourceAsStream("/resources/images/tiles/snow1.png")));
	        	tile.setCrossable(true); // Snow tile is crossable
	        	
	        	return tile;
	        }
	        case 1:{
	        	tile.setImage(ImageIO.read(
	        			Tile.class.getResourceAsStream("/resources/images/tiles/snow2.png")));
	        	tile.setCrossable(true); // snow tile is crossable
	        	return tile;
	        }
	        case 2: {
	        	tile.setImage(ImageIO.read(
	        			Tile.class.getResourceAsStream("/resources/images/tiles/waterTop1,waterLeft1.png")));
	        	tile.setCrossable(false); // water tile is not crossable
	        	return tile;
	        }
	        case 3: {
	        	tile.setImage(ImageIO.read(
	        			Tile.class.getResourceAsStream("/resources/images/tiles/waterTop2.png")));
	        	tile.setCrossable(false); // water tile is not crossable
	        	return tile;
	        }
	        case 4: {
	        	tile.setImage(ImageIO.read(
	        			Tile.class.getResourceAsStream("/resources/images/tiles/waterTop3,waterRight1.png")));
	        	tile.setCrossable(false); // water tile is not crossable
	        	return tile;
	        }
	        case 5: {
	        	tile.setImage(ImageIO.read(
	        			Tile.class.getResourceAsStream("/resources/images/tiles/waterLeft2.png")));
	        	tile.setCrossable(false); // water tile is not crossable
	        	return tile;
	        }
	        case 6: {
	        	tile.setImage(ImageIO.read(
	        			Tile.class.getResourceAsStream("/resources/images/tiles/waterMiddle.png")));
	        	tile.setCrossable(false); // water tile is not crossable
	        	return tile;
	        }
	        case 7: {
	        	tile.setImage(ImageIO.read(
	        			Tile.class.getResourceAsStream("/resources/images/tiles/waterRight2.png")));
	        	tile.setCrossable(false); // water tile is not crossable
	        	return tile;
	        }
	        case 8: {
	        	tile.setImage(ImageIO.read(
	        			Tile.class.getResourceAsStream("/resources/images/tiles/waterLeft3,waterBottom1.png")));
	        	tile.setCrossable(false); // water tile is not crossable
	        	return tile;
	        }
	        case 9: {
	        	tile.setImage(ImageIO.read(
	        			Tile.class.getResourceAsStream("/resources/images/tiles/waterBottom2.png")));
	        	tile.setCrossable(false); // water tile is not crossable
	        	return tile;
	        }
	        case 10: {
	        	tile.setImage(ImageIO.read(
	        			Tile.class.getResourceAsStream("/resources/images/tiles/waterBottom3,waterRight3.png")));
	        	tile.setCrossable(false); // water tile is not crossable
	        	return tile;
	        }
	        case 11:{
	        	tile.setImage(ImageIO.read(
	        			Tile.class.getResourceAsStream("/resources/images/tiles/wall.png")));
	        	tile.setCrossable(false); // wall tile is not crossable
	        	return tile;
	        }
	        case 12:{
	        	tile.setImage(ImageIO.read(
	        			Tile.class.getResourceAsStream("/resources/images/tiles/rightRoad.png")));
	        	tile.setCrossable(true); // road tile is crossable
	        	return tile;
	        }
	        case 13:{
	        	tile.setImage(ImageIO.read(
	        			Tile.class.getResourceAsStream("/resources/images/tiles/leftRoad.png")));
	        	tile.setCrossable(true); // road tile is crossable
	        	return tile;
	        }
	        case 14:{
	        	tile.setImage(ImageIO.read(
	        			Tile.class.getResourceAsStream("/resources/images/tiles/topRoad.png")));
	        	tile.setCrossable(true); // road tile is crossable
	        	return tile;
	        }
	        case 15:{
	        	tile.setImage(ImageIO.read(
	        			Tile.class.getResourceAsStream("/resources/images/tiles/bottomRoad.png")));
	        	tile.setCrossable(true); // road tile is crossable
	        	return tile;
	        }
	        case 16:{
	        	tile.setImage(ImageIO.read(
	        			Tile.class.getResourceAsStream("/resources/images/tiles/horizontalRoad.png")));
	        	tile.setCrossable(true); // road tile is crossable
	        	return tile;
	        }
	        case 17:{
	        	tile.setImage(ImageIO.read(
	        			Tile.class.getResourceAsStream("/resources/images/tiles/verticalRoad.png")));
	        	tile.setCrossable(true); // road tile is crossable
	        	return tile;
	        }
	        case 18:{
	        	tile.setImage(ImageIO.read(
	        			Tile.class.getResourceAsStream("/resources/images/tiles/middleRoad.png")));
	        	tile.setCrossable(true); // road tile is crossable
	        	return tile;
	        }
	        
	        default: throw new IllegalArgumentException("Unexpected value: " + itemId);
	    }
	}
}
