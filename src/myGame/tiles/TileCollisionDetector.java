package myGame.tiles;

import myGame.Utilities.MapManager;
import myGame.core.GamePanel;
import myGame.entities.Direction;
import myGame.entities.Player;


public class TileCollisionDetector {
    private GamePanel gp;

    public TileCollisionDetector() {
        gp  =GamePanel.getInstance(); 
    }
  
    // Helper function to convert world coordinates (pixels) to tile rows and columns
    private int getTileRow(int worldY) {
        return worldY / gp.getTileSize();
    }
  
    private int getTileCol(int worldX) {
        return worldX / gp.getTileSize();
    }
  
    public boolean canMove(Direction direction) { 
        Player player = gp.getPlayer();
        MapManager tm = gp.getMapManager();
      
        int speed = player.getSpeed(); 
        int playerLeftWorldX = player.getWorldX() + player.getSolidAreaX();
        int playerRightWorldX = player.getWorldX() + player.getSolidAreaX() + player.getSolidAreaWidth();
        int playerTopWorldY = player.getWorldY() + player.getSolidAreaY();
        int playerBottomWorldY = player.getWorldY() + player.getSolidAreaHeight() + player.getSolidAreaY(); 
       
        int playerLeftCol = getTileCol(playerLeftWorldX);
        int playerRightCol = getTileCol(playerRightWorldX);
        int playerTopRow = getTileRow(playerTopWorldY); 
        int playerBottomRow = getTileRow(playerBottomWorldY); 
      
        Tile tile1, tile2; // Potential tiles for collision detection
      
        switch (direction) {
            case UP:
                playerTopRow = getTileRow(playerTopWorldY - speed);
                tile1 = tm.getTile(playerTopRow, playerRightCol); 
                tile2 = tm.getTile(playerTopRow, playerLeftCol);
                break;
              
            case DOWN: 
                playerBottomRow = getTileRow(playerBottomWorldY + speed);
                tile1 = tm.getTile(playerBottomRow, playerRightCol);
                tile2 = tm.getTile(playerBottomRow, playerLeftCol);
                break;
              
            case LEFT:
                playerLeftCol = getTileCol(playerLeftWorldX - speed);
                tile1 = tm.getTile(playerTopRow, playerLeftCol);
                tile2 = tm.getTile(playerBottomRow, playerLeftCol); 
                break;
              
            case RIGHT:
                playerRightCol = getTileCol(playerRightWorldX + speed);
                tile1 = tm.getTile(playerTopRow, playerRightCol);
                tile2 = tm.getTile(playerBottomRow, playerRightCol);
                break;
    
            default:
                return false; // Invalid direction
        }  

        // Check if both tiles are not null and are crossable
	        return (tile1 == null || tile1.isCrossable()) && 
	               (tile2 == null || tile2.isCrossable());
		}
	}
