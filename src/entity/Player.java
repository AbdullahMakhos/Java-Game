package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Player extends Entity{
	private GamePanel gp;//the gamePanel to be displayed on
	private KeyHandler kh; //the KeyHandler to accept input
	
	//player's position on the screen
	public int screenX;
	public int screenY; 
	
	private BufferedImage currentImage,idleCharacterImage
	,upCharacterImage1,upCharacterImage2,leftCharacterImage1
	,leftCharacterImage2,rightCharacterImage1,rightCharacterImage2
	,downCharacterImage1,downCharacterImage2;  //to hold player's images
	  
	int tileSize; 

	public Player(GamePanel gp,KeyHandler kh) {
		this.gp = gp;
		this.kh = kh;
		this.screenX = gp.screenWidth/2 - (gp.getTileSize()/2);
		this.screenY = gp.screenHeight/2 - (gp.getTileSize()/2);
		this.tileSize = gp.getTileSize();
		
		setDefaultValues();
		getPlayerImages();
	} 
	
	private void setDefaultValues() {
		//player's starting position 
		this.worldX = (gp.getMapWidth() * tileSize)/2 - tileSize;
		this.worldY = (gp.getMapHeight() * tileSize)/2 - tileSize;
		this.speed = 4; 
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

		if(kh.upPressed) {
			worldY -= speed;
			if(spriteID == 1) {
				currentImage = upCharacterImage1;
			}else {
				currentImage = upCharacterImage2;
			}
		}else if(kh.downPressed) {
			worldY += speed;
			if(spriteID == 1) {
				currentImage = downCharacterImage1;
			}else {
				currentImage = downCharacterImage2;
			}
		}else if(kh.rightPressed) {
			worldX += speed;
			if(spriteID == 1) {
				currentImage = rightCharacterImage1;
			}else {
				currentImage = rightCharacterImage2;
			}
		}else if(kh.leftPressed) {
			worldX -= speed; 
			if(spriteID == 1) {
				currentImage = leftCharacterImage1;
			}else {
				currentImage = leftCharacterImage2;
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

	
}
