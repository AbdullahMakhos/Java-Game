package tiles;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;
import javax.imageio.ImageIO;

import entity.Player;
import main.GamePanel;

public class TileManager {
    private static final int TILE_TYPE_COUNT = 3; // Number of tile types
    private int[][] mapMatrix; // The map to manage
    private Tile[] tileTypes; // Tiles types to read the mapMatrix
    private GamePanel gp; // To draw the mapMatrix
    private Player player; 

    public TileManager(GamePanel gp) {
        this.gp = gp;
        this.player = gp.getPlayer();
        this.mapMatrix = gp.getMapMatrix();
        
        this.tileTypes = new Tile[TILE_TYPE_COUNT]; // Initialize tile types
        
        // Store tiles images in variables
        loadTileImages();
    }
    
    private void loadTileImages() {
        try {
            tileTypes[0] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/resources/snow.png")));
            tileTypes[0].setCrossable(true); // Snow tile is crossable
            
            tileTypes[1] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/resources/noSnow.png")));
            tileTypes[1].setCrossable(true); // No snow tile is crossable
            
            tileTypes[2] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/resources/wall.png")));
            tileTypes[2].setCrossable(false); // Wall tile is not crossable
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
        
        int currentTileScreenY; // Variable to store the screen Y position of the current tile

        // Loop through each row of the mapMatrix
        for (int row = 0; row < mapMatrix.length; row++) {
            int currentTileWorldY = row * tileSize; // Calculate the world Y position of the current tile
            // Calculate the screen Y position of the current tile based on player's position
            currentTileScreenY = currentTileWorldY - (playerWorldY - player.getScreenY());

            // Loop through each column of the mapMatrix
            for (int col = 0; col < mapMatrix[row].length; col++) {
                int currentTileID = mapMatrix[row][col]; // Get the ID of the current tile from the mapMatrix
                int currentTileWorldX = col * tileSize; // Calculate the world X position of the current tile
                // Calculate the screen X position of the current tile based on player's position
                int currentTileScreenX = currentTileWorldX - (playerWorldX - player.getScreenX());

                // If the tile ID is invalid, draw a red square instead
                if (currentTileID < 0 || currentTileID >= tileTypes.length) {
                    g2.setColor(Color.MAGENTA); // Set color to MAGENTA for invalid tiles
                    g2.drawRect(currentTileWorldX, currentTileWorldY, tileSize, tileSize); // Draw a rectangle
                } else {
                    // If valid, draw the corresponding tile image
                    g2.drawImage(tileTypes[currentTileID].getImage(),
                                 currentTileScreenX, currentTileScreenY, 
                                 tileSize, tileSize, null); // Draw the tile image at calculated screen position
                }
            }
        }
    }

    public Tile getTile(int row, int col) {
        // Check for valid indices before accessing the mapMatrix
        if (row < 0 || row >= mapMatrix.length || col < 0 || col >= mapMatrix[row].length) {
            throw new IndexOutOfBoundsException("Invalid tile coordinates: (" + row + ", " + col + ")");
        }
        int tileId = mapMatrix[row][col];
        return (tileId >= 0 && tileId < tileTypes.length) ? tileTypes[tileId] : null;
    }
}
