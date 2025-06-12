package entity;

import main.GamePanel;
import main.Inventory;
import main.KeyHandler;
import objects.Fish;
import objects.GameObject;
import objects.ObjectCollisionDetector;
import objects.ObjectManager;
import tiles.TileCollisionDetector;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;

public class Player extends Entity{
	private static final int TOTAL_HEALTH = 3;
	private static final int TOTAL_HUNGER = 3;
	private GamePanel gp;//the gamePanel to be displayed on
	private KeyHandler kh; //the KeyHandler to accept input
	private int screenX;//player's position on the screen
	private int screenY; 
	private boolean[] health;
	private boolean[] hunger;
	private Inventory inventory;
	private boolean starving;
	
	private BufferedImage currentImage,idleCharacterImage
	,upCharacterImage1,upCharacterImage2,leftCharacterImage1
	,leftCharacterImage2,rightCharacterImage1,rightCharacterImage2
	,downCharacterImage1,downCharacterImage2;  //to hold player's images
	  
	private int tileSize; 

	public Player(GamePanel gp) {
		super();
		
		this.gp = gp;
		kh = gp.getKeyHandler();
		screenX = gp.screenWidth/2 - (gp.getTileSize()/2);
		screenY = gp.screenHeight/2 - (gp.getTileSize()/2);
		tileSize = gp.getTileSize();
		health = new boolean[TOTAL_HEALTH];
		hunger = new boolean[TOTAL_HUNGER];
		inventory = new Inventory();
		
		setDefaultValues();
		getPlayerImages();
	}
	
	private void setDefaultValues() {
		//player's starting position 
		speed = 5; 
		solidArea = new Rectangle(8 , 16 , 40 , 32);
		worldX = (gp.getMapWidth() * gp.getTileSize())/2 - (gp.getTileSize()/2);;
		worldY = gp.getTileSize()*2;
		starving = false;
		
		Arrays.fill(health, true);
		Arrays.fill(hunger, true);
		
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
			ObjectManager ob = gp.getObjectManager();
			int playerRow = getPlayerRow();
			int playerCol = getPlayerCol();
			
			if(ob.getObject(playerRow, playerCol).isPickable()) {
				
				inventory.addItem(ob.getObject(playerRow, playerCol));
				ob.deleteObject(playerRow, playerCol);
			}
		}
		
		if(kh.nPressed) {
			if (gp.getCurrentLevel().canPass()) {
				gp.getLevelManager().goToNextLevel();
			}
			
		}
		
		if(kh.qPressed) {
			if(canEat()) {
				System.out.println("Yummy");
				GameObject selectedItem = inventory.getItem(new Fish());
				if(inventory.getItemCount(selectedItem) > 0) {
					inventory.removeItem(selectedItem);
					eat();
					gp.getUIManager().updateHungerStatus();
				}
			}
		}
		
		
	} 
	

	private void getPlayerImages() {
		//get images once and storing them to be more efficient
		try {
			idleCharacterImage = ImageIO.read(getClass()
					.getResourceAsStream("/entity/resources/penguin.png"));
			
			rightCharacterImage1 = ImageIO.read(getClass()
					.getResourceAsStream("/entity/resources/penguinRight1.png")); 
			rightCharacterImage2 = ImageIO.read(getClass()
					.getResourceAsStream("/entity/resources/penguinRight2.png")); 
			
			leftCharacterImage1 = ImageIO.read(getClass()
					.getResourceAsStream("/entity/resources/penguinLeft1.png")); 
			leftCharacterImage2 = ImageIO.read(getClass()
					.getResourceAsStream("/entity/resources/penguinLeft2.png")); 
			
			
			downCharacterImage1 = ImageIO.read(getClass()
					.getResourceAsStream("/entity/resources/penguinFront1.png")); 
			downCharacterImage2 = ImageIO.read(getClass()
					.getResourceAsStream("/entity/resources/penguinFront2.png")); 
			 
			upCharacterImage1 = ImageIO.read(getClass()
					.getResourceAsStream("/entity/resources/penguinAss1.png"));
			upCharacterImage2 = ImageIO.read(getClass()
					.getResourceAsStream("/entity/resources/penguinAss2.png")); 
						
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
	
	public void takeDamage() {
		
		int lastHeartIndex = -1;
		
		for(int i=TOTAL_HEALTH - 1 ; i >= 0 ; i--) {
			if(health[i]) {
				lastHeartIndex = i;
				break;
			}
		}
		
		if(lastHeartIndex != -1) {
			health[lastHeartIndex] = false;
		}
		
	}
	
	public void reduceHunger() {
		
		if(starving) {
			for(int i=TOTAL_HEALTH - 1 ; i>=0 ; i--) {
				if(health[i]) {
					health[i] = false;
					if(i == 0) {
						gp.setGameState(2); //game over
					}
					break;
				}
			}
			
		}else {
			
			for(int i=TOTAL_HUNGER - 1 ; i>=0 ; i--) {
				if(hunger[i]) {
					hunger[i] = false;
					
					if(i == 0) {
					
						starving = true;
					
					}else {
					
						starving = false;
					
					}
					
					break;
				}
			}
		}
		
		
	}
 
	
	public boolean canEat() {
		return !Arrays.equals(hunger,new boolean[] {true,true,true});
	}

	private void eat() {
		for(int i=0 ; i<hunger.length ; i++) {
			if(!hunger[i]) {
				hunger[i] = true;
				break;
			}
		}
	}
	
	public Inventory getInventory() {
		return inventory;
	}	
	
	
	public boolean[] getHealth() {
		return health;
	}
	
	public boolean[] getHunger() {
		return hunger;
	}
	
	public void updateXY() {
		worldX = gp.getLevelManager().getCurrentLevel().getInitialX();
		worldY = gp.getLevelManager().getCurrentLevel().getInitialY();
	}
	
	public int getPlayerCol() {
		return worldX/tileSize;
	}
	
	public int getPlayerRow() {
		return worldY/tileSize;
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
	
}
