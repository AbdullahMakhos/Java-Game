package tiles;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
	GamePanel gp; // to draw
	Tile[] tileTypes; // tiles types 
	int[][] mapMatrix = //map informations is here (if i added more levels(maps) i would add this to the constructor)
	{ {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0} 
	, {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0} 
	, {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}  
	, {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
	, {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
	, {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
	, {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
	, {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0} 
	, {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0} 
	, {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0} 
	, {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
	, {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
	 
	public TileManager(GamePanel gp) {
		 this.gp = gp; 
		 this.tileTypes = new Tile[1]; //two types of tiles

		 //store tiles images in variables
		 try {
			tileTypes[0] = 
			new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/resources/snow.png")));
			 
			
		 }catch (IOException e) {
	            System.err.println("Error loading tile resources:");
	            e.printStackTrace();
	     }
	}
	
	public void draw(Graphics2D g2) {
		int currentTileX = 0; 
		int currentTileY = 0;
		int currentTileID; 
		int tileSize = gp.getTileSize();
		 
		for(int row = 0 ; row<mapMatrix.length ; row++ ) { //iterate through columns
			currentTileX = 0; //reset when a new row starts
			for(int col = 0 ;col<mapMatrix[row].length ;col++) { //iterate through rows
				currentTileID = mapMatrix[row][col];  // get informations about the current tile
				//check if this id is valid or invalid
				if(currentTileID >= tileTypes.length || currentTileID < 0) { 
					// if invalid just draw a red square 
				 	g2.setColor(Color.magenta);
					g2.drawRect(currentTileX, currentTileY, tileSize, tileSize);
				}else { 
					//valid just draw the tile
					g2.drawImage(tileTypes[currentTileID].tileImage, currentTileX, currentTileY, tileSize , tileSize, null);
				} 
				currentTileX+=gp.getTileSize(); //next tile x (new column = new x)
			}
			currentTileY+=gp.getTileSize();//new row = new y 
		} 
		
	}
	 
}
