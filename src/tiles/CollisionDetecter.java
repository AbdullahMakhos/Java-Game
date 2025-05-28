package tiles;


import main.GamePanel;

public class CollisionDetecter {
	  GamePanel gp;
	  public CollisionDetecter(GamePanel gp) {
		  this.gp = gp; 
	  }
	
	  // Helper function to convert world coordinates (pixels) to tile rows and columns
	  private int getTileRow(int worldY) {
	      return worldY / gp.getTileSize();
	  }
	
	  private int getTileCol(int worldX) {
	      return worldX / gp.getTileSize();
	  }
	
	  public boolean canMove(String direction) { 
		  //we need to know the max x,y and min x,y
		  
		  int speed = gp.getPlayer().getSpeed(); 
		  int playerLeftWorldX = gp.getPlayer().getWorldX() + gp.getPlayer().getSolidAreaX(); //+ player.getSolidAreaX() to remove the area that doesn't require collision
		  int playerRightWorldX = gp.getPlayer().getWorldX() + gp.getPlayer().getSolidAreaX() + gp.getPlayer().getSolidAreaWidth(); // + player.getSolidAreaWidth() to reach the max right of the solid area
		  int playerTopWorldY = gp.getPlayer().getWorldY() + gp.getPlayer().getSolidAreaY();
		  int playerBottomWorldY = gp.getPlayer().getWorldY() + gp.getPlayer().getSolidAreaHeight()+ gp.getPlayer().getSolidAreaY(); 
		   
		  int playerLeftCol = getTileCol(playerLeftWorldX);
		  int playerRightCol = getTileCol(playerRightWorldX);
		  int playerTopRow = getTileRow(playerTopWorldY); 
		  int playerBottomRow = getTileRow(playerBottomWorldY); 
		  
		  Tile tile1 ,tile2 ; //there is two potential tiles the player may collide with
		  
		  switch (direction){
				case "up": {
					playerTopRow = getTileRow(playerTopWorldY - speed);
					tile1 =  gp.getTileManager().getTile(playerTopRow, playerRightCol); 
					tile2 =  gp.getTileManager().getTile(playerTopRow, playerLeftCol);
					break;
					
				}
				case "down": { 
					playerBottomRow = getTileRow(playerBottomWorldY + speed);
					tile1 = gp.getTileManager().getTile(playerBottomRow, playerRightCol);
					tile2 = gp.getTileManager().getTile(playerBottomRow, playerLeftCol);
					break;
					
				}
				case "left": {
					playerLeftCol = getTileCol(playerLeftWorldX - speed);
					tile1 = gp.getTileManager().getTile(playerTopRow, playerLeftCol);
					tile2 = gp.getTileManager().getTile(playerBottomRow, playerLeftCol); 
					break;
					
				}
				case "right": {
					playerRightCol = getTileCol(playerRightWorldX + speed);
					tile1 = gp.getTileManager().getTile(playerTopRow, playerRightCol);
					tile2 = gp.getTileManager().getTile(playerBottomRow, playerRightCol);
					break;
		
				}
				default: tile1 = null; tile2 = null;
		  }  
		  		return !tile1.isSolid() && !tile2.isSolid();//this needs to be fixed
	}

}