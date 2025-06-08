package objects;

import java.awt.Graphics2D;
import java.io.IOException;
import java.rmi.server.UID;

import javax.imageio.ImageIO;

import entity.Player;
import main.GamePanel;

public class ObjectManager {
	   private static final int OBJECT_TYPE_COUNT = 3; // Number of tile types
	    private int[][] objectMatrix; // The map to manage
	    private GameObject[] objectTypes; // Tiles types to read the mapMatrix
	    private GamePanel gp; // To draw the mapMatrix
	    private Player player; 
	    private int screenHeight;
	    private int screenWidth;
	    
	    public ObjectManager(GamePanel gp) {
	        this.gp = gp;
	        player = gp.getPlayer();
	        objectMatrix = gp.getObjectMatrix();
	        objectTypes = new GameObject[OBJECT_TYPE_COUNT]; // Initialize tile types
	        screenHeight = gp.getScreenHeight();
	        screenWidth = gp.getScreenWidth();
	        
	        // Store tiles images in variables
	        loadObjectImages();
	    }
	    
	    private void loadObjectImages() {
	    	int objectSize = gp.getTileSize();
	    	
	    	try {

	            objectTypes[0] = new GameObject();
	            objectTypes[0].setCrossable(true); 
	            objectTypes[0].setPickable(false);
	            
	            objectTypes[1] = new Door(gp);
	            objectTypes[1].setImage(ImageIO.read(getClass().
	            getResourceAsStream("/objects/resources/closedDoor.png")));
	            ((Door) objectTypes[1]).setImage2(ImageIO.read(getClass().
	            getResourceAsStream("/objects/resources/openDoor.png")));
	            objectTypes[1].setCrossable(false); 
	            objectTypes[1].setPickable(false);
	            objectTypes[1].setObjectSize(objectSize);
	            
	            objectTypes[2] = new Fish();
	            objectTypes[2].setImage(ImageIO.read(getClass().
	    	    getResourceAsStream("/objects/resources/fish.png")));
	            objectTypes[2].setCrossable(true); 
	            objectTypes[2].setPickable(true);
	            objectTypes[2].setObjectSize(objectSize);
	            
	        } catch (IOException e) {
	            System.err.println("Error loading object resources:");
	            e.printStackTrace();
	        }
	    }

	    public void draw(Graphics2D g2) {
	        int objectSize = gp.getTileSize(); // Get the size of each tile from the GamePanel

	        // Player's world position
	        int playerWorldX = player.getWorldX(); // Get the player's current world X position
	        int playerWorldY = player.getWorldY(); // Get the player's current world Y position

	        int startRow = Math.max(0, (playerWorldY - player.getScreenY()) / objectSize);
	        int endRow = Math.min(objectMatrix.length - 1, startRow + (screenHeight / objectSize) + 1); // Adjust based on screen height
	        int startCol = Math.max(0, (playerWorldX - player.getScreenX()) / objectSize);
	        int endCol = Math.min(objectMatrix[0].length - 1, startCol + (screenWidth / objectSize) + 1); // Adjust based on screen width
	        
	        // Loop through each row of the objectMatrix
	        for (int row = startRow; row <= endRow; row++) {
	            int currentObjectWorldY = row * objectSize; // Calculate the world Y position of the current tile
	            int currentObjectScreenY = currentObjectWorldY - (playerWorldY - player.getScreenY());

	            // Loop through each column of the objectMatrix
	            for (int col = startCol; col <= endCol; col++) {
	                int currentObjectID = objectMatrix[row][col]; // Get the ID of the current tile from the mapMatrix
	                int currentObjectWorldX = col * objectSize; // Calculate the world X position of the current tile
	                int currentObjectScreenX = currentObjectWorldX - (playerWorldX - player.getScreenX());

	                // Check if the object is within the player's view
	                if (isObjectInView(currentObjectWorldX, currentObjectWorldY, playerWorldX, playerWorldY, objectSize)) {
	                    // Check if the object ID is valid
	                    if (currentObjectID >= 0 && currentObjectID < OBJECT_TYPE_COUNT) {
	                        // Draw the corresponding object image
	                    	g2.drawImage(objectTypes[currentObjectID].getImage(),
	                                currentObjectScreenX, currentObjectScreenY,
	                                objectTypes[currentObjectID].getObjectSize(),
	                                objectTypes[currentObjectID].getObjectSize(), null);
	                        // Check if the object is a door and if the player is near it
	                        if (objectTypes[currentObjectID] instanceof Door) {
	                            if (isPlayerNearDoor(col, row, playerWorldX, playerWorldY, objectSize)) {
	                                ((Door) objectTypes[currentObjectID]).behavior();
	                            }
	                        }
	                    }
	                }
	            }
	        }
	    }

	    private boolean isObjectInView(int objWorldX, int objWorldY, int playerWorldX, int playerWorldY, int objectSize) {
	        return objWorldX + objectSize > playerWorldX - player.getScreenX() &&
	               objWorldX - objectSize < playerWorldX + player.getScreenX() &&
	               objWorldY + objectSize > playerWorldY - player.getScreenY() &&
	               objWorldY - objectSize < playerWorldY + player.getScreenY();
	    }

	    private boolean isPlayerNearDoor(int col, int row, int playerWorldX, int playerWorldY, int objectSize) {
	        int playerCol = playerWorldX / objectSize;
	        int playerRow = playerWorldY / objectSize;

	        return col == playerCol && (row == playerRow || row == (playerRow - 1) || row == (playerRow + 1));
	    }

 
	    public GameObject getObject(int row, int col) {
	        // Check for valid indices before accessing the mapMatrix
	        if (row < 0 || row >= objectMatrix.length || col < 0 || col >= objectMatrix[row].length) {
	            throw new IndexOutOfBoundsException("Invalid object coordinates: (" + row + ", " + col + ")");
	        }
	        int objectID = objectMatrix[row][col];
	        return (objectID >= 0 && objectID < objectTypes.length) ? objectTypes[objectID] : null;
	    }
	    
	    public void deleteObject(int row, int col) {
	    	objectMatrix[row][col] = 0;
	    }

		public void updateObjectMatrix() {
			objectMatrix = gp.getObjectMatrix();
		}

	
	} 

