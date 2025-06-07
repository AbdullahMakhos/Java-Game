package tiles;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.font.TextMeasurer;
import java.io.IOException;
import javax.imageio.ImageIO;

import entity.Player;
import main.GamePanel;
import objects.Door;
import objects.GameObject;

public class TileManager {
    private static final int TILE_TYPE_COUNT = 4; // Number of tile types
    private int[][] tileMatrix; // The map to manage
    private Tile[] tileTypes; // Tiles types to read the mapMatrix
    private GamePanel gp; // To draw the mapMatrix
    private Player player; 
    private int screenHeight;
    private int screenWidth;
    
    public TileManager(GamePanel gp) {
        this.gp = gp;
        player = gp.getPlayer();
        tileMatrix = gp.getMapMatrix();
        tileTypes = new Tile[TILE_TYPE_COUNT]; // Initialize tile types
        screenHeight = gp.getScreenHeight();
        screenWidth = gp.getScreenWidth();
        
        // Store tiles images in variables
        loadTileImages();
    }
    
    private void loadTileImages() {
    	int originalTileSize = gp.getTileSize();
    	
        try {
            tileTypes[0] = new Tile();
            tileTypes[0].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/resources/snow.png")));
            tileTypes[0].setCrossable(true); // Snow tile is crossable
            tileTypes[0].setTileSize(originalTileSize);
            
            tileTypes[1] = new Tile();
            tileTypes[1].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/resources/tree.png")));
            tileTypes[1].setCrossable(false); // tree tile is crossable
            tileTypes[1].setTileSize(originalTileSize);
            
            tileTypes[2] = new Tile();
            tileTypes[2].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/resources/wall.png")));
            tileTypes[2].setCrossable(false); // Wall tile is not crossable
            tileTypes[2].setTileSize(originalTileSize);
            
            
            tileTypes[3] = new Tile();
            tileTypes[3].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/resources/thing.png")));
            tileTypes[3].setCrossable(false); // thing tile is not crossable
            tileTypes[3].setTileSize(originalTileSize);
            
            
            
        } catch (IOException e) {
            System.err.println("Error loading tile resources:");
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int tileSize = gp.getTileSize(); // Get the size of each tile from the GamePanel

        // Player's world position
        int playerWorldX = player.getWorldX(); // Get the player's current world X position
        int playerWorldY = player.getWorldY(); // Get the player's current world Y position

        int startRow = Math.max(0, (playerWorldY - player.getScreenY()) / tileSize);
        int endRow = Math.min(tileMatrix.length - 1, startRow + (screenHeight/tileSize) + 1); // Adjust based on screen height
        int startCol = Math.max(0, (playerWorldX - player.getScreenX()) / tileSize);
        int endCol = Math.min(tileMatrix[0].length - 1, startCol + (screenWidth/tileSize) + 1); // Adjust based on screen width
        
        // Loop through each row of the tileMatrix
        for (int row = startRow; row <= endRow; row++) {
            int currentTileWorldY = row * tileSize; // Calculate the world Y position of the current tile
            int currentTileScreenY = currentTileWorldY - (playerWorldY - player.getScreenY()); // Calculate screen Y position

            // Loop through each column of the tileMatrix
            for (int col = startCol; col <= endCol; col++) {
                int currentTileID = tileMatrix[row][col]; // Get the ID of the current tile
                int currentTileWorldX = col * tileSize; // Calculate the world X position of the current tile
                int currentTileScreenX = currentTileWorldX - (playerWorldX - player.getScreenX()); // Calculate screen X position

                // Check if the tile is within the player's view
                if (isTileInView(currentTileWorldX, currentTileWorldY, playerWorldX, playerWorldY, tileSize)) {
                    drawTile(g2, currentTileID, currentTileScreenX, currentTileScreenY, tileSize);
                }
            }
        }
    }

    // Method to check if a tile is within the player's view
    private boolean isTileInView(int tileWorldX, int tileWorldY, int playerWorldX, int playerWorldY, int tileSize) {
        return tileWorldX + tileSize > playerWorldX - player.getScreenX() &&
               tileWorldX - tileSize < playerWorldX + player.getScreenX() &&
               tileWorldY + tileSize > playerWorldY - player.getScreenY() &&
               tileWorldY - tileSize < playerWorldY + player.getScreenY();
    }

    // Method to draw a tile based on its ID
    private void drawTile(Graphics2D g2, int tileID, int screenX, int screenY, int tileSize) {
        if (tileID < 0 || tileID >= tileTypes.length) {
            g2.setColor(Color.MAGENTA); // Set color to MAGENTA for invalid tiles
            g2.drawRect(screenX, screenY, tileSize, tileSize); // Draw a rectangle for invalid tiles
        } else {
            // Draw the corresponding tile image for valid tiles
            g2.drawImage(tileTypes[tileID].getImage(), screenX, screenY,
                         tileTypes[tileID].getTileSize(), tileTypes[tileID].getTileSize(), null);
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
}
