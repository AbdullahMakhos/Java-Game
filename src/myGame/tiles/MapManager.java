package myGame.tiles;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;

import myGame.core.GamePanel;
import myGame.entity.Player;


public class MapManager {
	private GamePanel gp; // To draw the mapMatrix
    private Player player; 

    private static final int TILE_TYPE_COUNT = 5; // Number of tile types
    private int[][] tileMatrix; // The map to manage
    private Tile[] tileTypes; // Tiles types to read the mapMatrix
    
    private static final int OBJECT_TYPE_COUNT = 5; // Number of tile types
    private int[][] objectMatrix; // The map to manage
    private GameObject[] objectTypes; // Tiles types to read the mapMatrix
    
    private int screenHeight;
    private int screenWidth;
    
    private int maxRows;
    private int maxCols;
    
    public MapManager() {
        this.gp = GamePanel.getInstance();
        player = gp.getPlayer();
        
        tileMatrix = gp.getTileMatrix();
        tileTypes = new Tile[TILE_TYPE_COUNT]; // Initialize tile types
        
        objectMatrix = gp.getObjectMatrix();
        objectTypes = new GameObject[OBJECT_TYPE_COUNT]; // Initialize tile types
        
        screenHeight = gp.getScreenHeight();
        screenWidth = gp.getScreenWidth();
        
        updateMaxRowsAndCols();
        // Store tiles images in variables
        loadImages();
    }
    
    private void loadImages() {
    	
        try {
        	
            tileTypes[0] = Tile.createFromID(0);//snow ground
            
            tileTypes[1] = Tile.createFromID(1);//tree
            
            tileTypes[2] = Tile.createFromID(2);//wall
            
            tileTypes[3] = Tile.createFromID(3);//snowKitty
            
            tileTypes[4] = Tile.createFromID(4);//water
            
         
            objectTypes[0] = GameObject.createFromID(0);//GameObject
            
            objectTypes[1] = GameObject.createFromID(1);//Door
            
            objectTypes[2] = GameObject.createFromID(2);//Fish

            objectTypes[3] = GameObject.createFromID(3);//SnowPearl
           
            objectTypes[4] = GameObject.createFromID(4);//FishingRod
            
            
        } catch (IOException e) {
            System.err.println("Error loading resources:");
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        if (tileMatrix == null || objectMatrix == null) return;
        
        int tileSize = gp.getTileSize();
        int playerX = player.getWorldX();
        int playerY = player.getWorldY();
        
        // Calculate visible area once
        int startRow = Math.max(0, (playerY - player.getScreenY()) / tileSize);
        int endRow = Math.min(maxRows - 1, startRow + (screenHeight/tileSize) + 1);
        int startCol = Math.max(0, (playerX - player.getScreenX()) / tileSize);
        int endCol = Math.min(maxCols - 1, startCol + (screenWidth/tileSize) + 1);
        
        // Pre-calculate these once
        int playerScreenX = player.getScreenX();
        int playerScreenY = player.getScreenY();
        
        for (int row = startRow; row <= endRow; row++) {
            int worldY = row * tileSize;
            int screenY = worldY - (playerY - playerScreenY);
            
            for (int col = startCol; col <= endCol; col++) {
                int worldX = col * tileSize;
                int screenX = worldX - (playerX - playerScreenX);
           
                if (row >= 0 && row < maxRows && col >= 0 && col < maxCols) {
                	
                	if (isInView(worldX, worldY, playerX, playerY, tileSize)) {
                		
                		drawTile(g2, tileMatrix[row][col], screenX, screenY);
                		drawObject(g2, objectMatrix[row][col], screenX, screenY);
                		
                	}
                
                }
                
            }
        }
    }
	
    // Method to check if a tile is within the player's view
    private boolean isInView(int WorldX, int WorldY, int playerWorldX, int playerWorldY, int tileSize) {
        return 	WorldX + tileSize > playerWorldX - player.getScreenX() &&
        		WorldX - tileSize < playerWorldX + player.getScreenX() &&
        		WorldY + tileSize > playerWorldY - player.getScreenY() &&
        		WorldY - tileSize < playerWorldY + player.getScreenY();
    }

    private void drawObject(Graphics2D g2, int objectID, int screenX, int screenY) {
        if (objectID >= 0 && objectID < OBJECT_TYPE_COUNT) {
            GameObject obj = objectTypes[objectID];
            
          
            if (obj.getImage() != null) {
                g2.drawImage(obj.getImage()
                , screenX, screenY, obj.getObjectSize(), obj.getObjectSize(), null);
            }
            
            if (obj instanceof Door) {
                if (isPlayerNearDoor(screenX, screenY,obj.getObjectSize())) {
                    ((Door) obj).behavior();
                }
            }
        
        }
    }

    
    // Method to draw a tile based on its ID
    private void drawTile(Graphics2D g2, int tileID, int screenX, int screenY) {
    	int tileSize = gp.getTileSize();
		if (tileID >= 0 && tileID < tileTypes.length) {
            Tile tile = tileTypes[tileID];
            if (tile.getImage() != null) {
                g2.drawImage(tile.getImage(), screenX, screenY
                , tile.getTileSize(), tile.getTileSize() , null);
            } else {
                // Draw error tile
                g2.setColor(Color.ORANGE);
                g2.fillRect(screenX, screenY, tile.getTileSize(), tile.getTileSize());
            }
        } else {
            // Invalid tile ID
            g2.setColor(Color.MAGENTA);
            g2.fillRect(screenX, screenY, tileSize, tileSize);
        }
    }

    

    public Tile getTile(int row, int col) {
        // Check for valid indices before accessing the mapMatrix
        if (row < 0 || row >= tileMatrix.length || col < 0 || col >= tileMatrix[row].length) {
            throw new IndexOutOfBoundsException("Invalid tile coordinates: (" + row + ", " + col + ")");
        }
        int tileId = tileMatrix[row][col];
        return (tileId >= 0 && tileId < tileTypes.length) ? tileTypes[tileId] : null;
    }
    
    public GameObject getObject(int row, int col) {
        // Check for valid indices before accessing the mapMatrix
        if (row < 0 || row >= objectMatrix.length || col < 0 || col >= objectMatrix[row].length) {
            throw new IndexOutOfBoundsException("Invalid object coordinates: (" + row + ", " + col + ")");
        }
        int objectID = objectMatrix[row][col];
        return (objectID >= 0 && objectID < objectTypes.length) ? objectTypes[objectID] : null;
    }
    
    private boolean isPlayerNearDoor(int screenX, int screenY, int size) {
    	int playerWorldX = player.getWorldX();
        int playerWorldY = player.getWorldY();
        int doorWorldX = screenX + (playerWorldX - player.getScreenX());
        int doorWorldY = screenY + (playerWorldY - player.getScreenY());
        
        return Math.abs(playerWorldX - doorWorldX) <= size && 
               Math.abs(playerWorldY - doorWorldY) <= size;
    }
    
    public boolean isPlayerNearWater() {
        int tileSize = GamePanel.getInstance().getTileSize();
        
        // Correct tile index calculation
        int playerRow = player.getPlayerRow();
        int playerCol = player.getPlayerCol();
        
        // Check bounds
        if (playerRow < 0 || playerCol < 0 || 
        	playerRow >= tileMatrix.length || playerCol >= tileMatrix[0].length) {
            return false;
        }
        
        // Check 4 adjacent tiles (top, bottom, left, right)
        int[][] directions = {
            {0, 1},  // Right (X+0, Y+1)
            {0, -1}, // Left  (X+0, Y-1)
            {1, 0},   // Down  (X+1, Y+0)
            {-1, 0}   // Up    (X-1, Y+0)
        };
        
        for (int[] dir : directions) {
            int x = playerRow + dir[0];
            int y = playerCol + dir[1];
            
            if (x >= 0 && y >= 0 && x < tileMatrix.length && y < tileMatrix[0].length) {
                if (tileMatrix[x][y] == 4) { // 4 = water tile
                    return true;
                }
            }
        }
        return false;
    }
    
    public void deleteObject(int row, int col) {
    	objectMatrix[row][col] = 0;
    	gp.updateCurrentLevel();
    }

    public void updateMap() {
    	updateTileMatrix();
    	updateObjectMatrix();
    }
    private void updateObjectMatrix() {
		objectMatrix = gp.getObjectMatrix();
		updateMaxRowsAndCols();
	}

    private void updateTileMatrix() {
    	tileMatrix = gp.getTileMatrix();
    	updateMaxRowsAndCols();
    }
    
    private void updateMaxRowsAndCols() {
		maxCols = tileMatrix[0].length;
		maxRows = tileMatrix.length;
	}
    
    public GameObject[] getObjectTypesMatrix() {
    	return objectTypes;
    }
}
