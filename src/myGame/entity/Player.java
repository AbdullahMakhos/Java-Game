package myGame.entity;


import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.fasterxml.jackson.annotation.JsonProperty;

import myGame.core.KeyHandler;
import myGame.entity.playerThings.Inventory;
import myGame.entity.playerThings.Item;
import myGame.tiles.MapManager;
import myGame.tiles.ObjectCollisionDetector;
import myGame.tiles.TileCollisionDetector;
import myGame.entity.playerThings.Status;


public class Player extends Entity{
	private KeyHandler kh; //the KeyHandler to accept input
	private int screenX;//player's position on the screen
	private int screenY; 
	private Inventory inventory;
	@JsonProperty("status")
	private Status status;
	
	private BufferedImage currentImage,idleCharacterImage
	,upCharacterImage1,upCharacterImage2,leftCharacterImage1
	,leftCharacterImage2,rightCharacterImage1,rightCharacterImage2
	,downCharacterImage1,downCharacterImage2;  //to hold player's images
	  
	private int tileSize; 
	
	private int counter = 0;
 
	public Player() {
		super();
		
		kh = gp.getKeyHandler();
		screenX = gp.screenWidth/2 - (gp.getTileSize()/2);
		screenY = gp.screenHeight/2 - (gp.getTileSize()/2);
		tileSize = gp.getTileSize();
		inventory = new Inventory();
		status = new Status(gp);
		
		setDefaultValues();
		getPlayerImages();
	}
	
	private void setDefaultValues() {
		//player's starting position 
		speed = 5; 
		solidArea = new Rectangle(8 , 16 , 40 , 32);
		worldX = (gp.getMapWidth() * gp.getTileSize())/2 - (gp.getTileSize()/2);;
		worldY = gp.getTileSize()*2;
	}

	//update player method here to keep GamePanel organized 
	public void update() {
		currentImage = idleCharacterImage;
		
		//update information about player's position 
		spriteCounter++; // to make the walking looks like animations
		if(spriteCounter > 10 ) {
			spriteCounter = 0;
			if(spriteID == 0)
			spriteID = 1; 
			else spriteID = 0;
		}
		//player movement 
		if(kh.upPressed) {
			if(canMove(Direction.UP)) {
				worldY -= speed;												
			}
			
			currentImage = (spriteID == 1) ? upCharacterImage1 :upCharacterImage2;
			
		}else if(kh.downPressed) {
			if(canMove(Direction.DOWN)) {
				worldY += speed;
			}			
			
			currentImage = (spriteID == 1) ? downCharacterImage1 : downCharacterImage2;

		}else if(kh.rightPressed) {
			if(canMove(Direction.RIGHT)) {
				worldX += speed;				
			}
			
			currentImage = (spriteID == 1) ? rightCharacterImage1 : rightCharacterImage2;
			
		}else if(kh.leftPressed) {
			if(canMove(Direction.LEFT)) {
				worldX -= speed; 				
			}

			currentImage = (spriteID == 1) ? leftCharacterImage1 : leftCharacterImage2;
			
		}
		
		//player action
		if(kh.pPressed) {
			MapManager ob = gp.getMapManager();
				int playerRow = getPlayerRow();
				int playerCol = getPlayerCol();
				
				if(ob.getObject(playerRow, playerCol).isPickable()) {
					
					inventory.addItem(ob.getObject(playerRow, playerCol));
					ob.deleteObject(playerRow, playerCol);
				}
		}
		
		
		if(kh.spacePressed) {
			counter++;
			
			if(counter > 5) {
				resetCounter();
				Item selectedItem = inventory.getSelectedItem();
				
				if(selectedItem != null) {
					selectedItem.useItem();
				}
			}
		
		}
	}

	private void getPlayerImages() {
		//get images once and storing them to be more efficient
		try {
			idleCharacterImage = ImageIO.read(getClass()
		    .getResourceAsStream("/resources/penguin.png"));
			
			rightCharacterImage1 = ImageIO.read(getClass()
			.getResourceAsStream("/resources/penguinRight1.png")); 
			rightCharacterImage2 = ImageIO.read(getClass()
			.getResourceAsStream("/resources/penguinRight2.png")); 
			
			leftCharacterImage1 = ImageIO.read(getClass()
			.getResourceAsStream("/resources/penguinLeft1.png")); 
			leftCharacterImage2 = ImageIO.read(getClass()
			.getResourceAsStream("/resources/penguinLeft2.png")); 
			
			
			downCharacterImage1 = ImageIO.read(getClass()
			.getResourceAsStream("/resources/penguinFront1.png")); 
			downCharacterImage2 = ImageIO.read(getClass()
			.getResourceAsStream("/resources/penguinFront2.png")); 
			 
			upCharacterImage1 = ImageIO.read(getClass()
			.getResourceAsStream("/resources/penguinAss1.png"));
			upCharacterImage2 = ImageIO.read(getClass()
			.getResourceAsStream("/resources/penguinAss2.png")); 
						
		} catch (IOException e) {
				e.printStackTrace();
		}
		
	}
	
	public void draw(Graphics2D g2) {
		
		g2.drawImage(currentImage ,screenX ,screenY ,tileSize ,tileSize ,null);
		
		
	}
	
	private boolean canMove(Direction direction) {

		TileCollisionDetector cd = gp.getTileCollisionDetecter();
		ObjectCollisionDetector od = gp.getObjectCollisionDetecter();
		
		return cd.canMove(direction) && od.canMove(direction);
	}
	
	public Inventory getInventory() {
	
		return inventory;
	
	}	

	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
		gp.getUIManager().updateStatus();
	}

	public void updateXY() {
		worldX = gp.getLevelManager().getCurrentLevel().getInitialX();
		worldY = gp.getLevelManager().getCurrentLevel().getInitialY();
	}
	
	public void resetCounter() {
		counter = 0;
	}
	
	public int getPlayerCol() {
		return (worldX + getSolidAreaWidth()/2 )/tileSize;
	}
	
	public int getPlayerRow() {
		return (worldY + getSolidAreaHeight()/2)/tileSize;
	}
	
	public int getScreenX() {
		return screenX;
	}
	
	public int getScreenY() {
		return screenY;
	}
	
	public int getSolidAreaX() {
		return solidArea.x;
	}
	
	public int getSolidAreaY() {
		return solidArea.y;
	}
	
	public int getSolidAreaWidth() {
		return solidArea.width;
	}
	
	public int getSolidAreaHeight() {
		return solidArea.height;
	}

	public void setX(int x) {
		worldX = x;
	}
	
	public void setY(int y) {
		worldY = y;
	}
	
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;	
	}


	
}
