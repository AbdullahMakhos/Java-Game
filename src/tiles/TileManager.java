package tiles;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
	int[][] mapMatrix ;//the map to manage
	Tile[] tileTypes; // tiles types to read the mapMatrix
	GamePanel gp; // to draw the mapMatrix
	
	public TileManager(GamePanel gp,int[][] mapMatrix) {
		 this.gp = gp; 
		 this.mapMatrix = mapMatrix;
		 this.tileTypes = new Tile[3]; //two types of tiles
		 
		 //store tiles images in variables
		 try {
			tileTypes[0] =  
			new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/resources/snow.png")));
			
			tileTypes[1] = 
			new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/resources/noSnow.png")));
			
			tileTypes[2] = 
			new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/resources/wall.png")));
					
			
		 }catch (IOException e) {
	            System.err.println("Error loading tile resources:");
	            e.printStackTrace();
	     }
	}
	
	public void draw(Graphics2D g2) {
		int currentTileWorldX = 0; 
		int currentTileWorldY = 0; 
		 
		int currentTileScreenX = 0;
		int currentTileScreenY = 0;
		
		int currentTileID;   
		int tileSize = gp.getTileSize();
		   
		for(int row = 0 ; row < mapMatrix.length ; row++ ) { //iterate through columns
			currentTileWorldX = 0; //reset at the beginning of each row
			for(int col = 0 ; col < mapMatrix[row].length ; col++) { //iterate through rows
				currentTileID = mapMatrix[row][col];  // get informations about the current tile

				 
				currentTileWorldX = col * tileSize;  
				//tile placement = original place like if there is no camera - (the distance between the map and the screen)
				currentTileScreenX = currentTileWorldX - (gp.player.worldX - gp.player.screenX); 
				 
				//check if this id is valid or invalid 
				if(currentTileID >= tileTypes.length || currentTileID < 0) { 
					//if invalid draw a red square instead  
				 	g2.setColor(Color.magenta); 
					g2.drawRect(currentTileWorldX, currentTileWorldY, tileSize, tileSize);
				  
				}else {  
				
					//if valid just draw the tile
					g2.drawImage(tileTypes[currentTileID].tileImage
					, currentTileScreenX, currentTileScreenY , tileSize , tileSize, null);
				
				} 
			    
			 
			}
			currentTileWorldY = row*tileSize;
			//tile placement = original place like if there is no camera - (the distance between the map and the screen)
			currentTileScreenY = currentTileWorldY - (gp.player.worldY - gp.player.screenY) ;
		} 
		
	}
	 
}
