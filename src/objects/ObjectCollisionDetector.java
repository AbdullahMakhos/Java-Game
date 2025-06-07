package objects;

import entity.Direction;
import entity.Player;
import main.GamePanel;

public class ObjectCollisionDetector {
    private GamePanel gp;

    public ObjectCollisionDetector(GamePanel gp) {
        this.gp = gp; 
    }
  
    // Helper function to convert world coordinates (pixels) to tile rows and columns
    private int getObjectRow(int worldY) {
        return worldY / gp.getTileSize();
    }
  
    private int getObjectCol(int worldX) {
        return worldX / gp.getTileSize();
    }
    
    public boolean canMove(Direction direction) { 
        Player player = gp.getPlayer();
        ObjectManager om = gp.getObjectManager();
      
        int speed = player.getSpeed(); 
        int playerLeftWorldX = player.getWorldX() + player.getSolidAreaX();
        int playerRightWorldX = player.getWorldX() + player.getSolidAreaX() + player.getSolidAreaWidth();
        int playerTopWorldY = player.getWorldY() + player.getSolidAreaY();
        int playerBottomWorldY = player.getWorldY() + player.getSolidAreaHeight() + player.getSolidAreaY(); 
       
        int playerLeftCol = getObjectCol(playerLeftWorldX);
        int playerRightCol = getObjectCol(playerRightWorldX);
        int playerTopRow = getObjectRow(playerTopWorldY); 
        int playerBottomRow = getObjectRow(playerBottomWorldY); 
      
        GameObject object1, object2; // Potential tiles for collision detection
      
        switch (direction) {
            case UP:
                playerTopRow = getObjectRow(playerTopWorldY - speed);
                object1 = om.getObject(playerTopRow, playerRightCol); 
                object2 = om.getObject(playerTopRow, playerLeftCol);
                break;
              
            case DOWN: 
                playerBottomRow = getObjectRow(playerBottomWorldY + speed);
                object1 = om.getObject(playerBottomRow, playerRightCol);
                object2 = om.getObject(playerBottomRow, playerLeftCol);
                break;
              
            case LEFT:
                playerLeftCol = getObjectCol(playerLeftWorldX - speed);
                object1 = om.getObject(playerTopRow, playerLeftCol);
                object2 = om.getObject(playerBottomRow, playerLeftCol); 
                break;
              
            case RIGHT:
                playerRightCol = getObjectCol(playerRightWorldX + speed);
                object1 = om.getObject(playerTopRow, playerRightCol);
                object2 = om.getObject(playerBottomRow, playerRightCol);
                break;
    
            default:
                return false; // Invalid direction
        }  
        
        // Check if both tiles are not null and are crossable
        return (object1 != null && object2 != null && object1.isCrossable() && object2.isCrossable());
    }
}
