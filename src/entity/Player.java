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
	GamePanel gp;//the gamePanel to be displayed on
	KeyHandler kh; //the KeyHandler to accept input
	BufferedImage currentImage,upCharacterImage,downCharacterImage,leftCharacterImage,rightCharacterImage;  //to hold player's images
	 
	public Player(GamePanel gp,KeyHandler kh) {
		super();//call the Entity constructor to get default values 
		this.gp = gp;
		this.kh = kh; 
		
		getPlayerImages();
		currentImage = downCharacterImage;
	}
 
	//update player method here to keep GamePanel organized 
	public void update() {
		currentImage = downCharacterImage;
		//update information about player's position 
		if(kh.upPressed) {
			y -= speed;
			currentImage = upCharacterImage;
		}else if(kh.downPressed) {
			y += speed;
			currentImage = downCharacterImage;
		}else if(kh.rightPressed) {
			x += speed;
			currentImage = rightCharacterImage;
		}else if(kh.leftPressed) {
			x -= speed;
			currentImage = leftCharacterImage;
		}
		
	} 
	
	private void getPlayerImages() {
		//get images once and storing them to be more efficient
		try {
			upCharacterImage = ImageIO.read(getClass()
						.getResourceAsStream("/entity/resources/penguinAss.png")); 
			downCharacterImage = ImageIO.read(getClass()
						.getResourceAsStream("/entity/resources/penguinFront.png")); 
			rightCharacterImage = ImageIO.read(getClass()
						.getResourceAsStream("/entity/resources/penguinRight.png")); 
			leftCharacterImage = ImageIO.read(getClass()
						.getResourceAsStream("/entity/resources/penguinLeft.png")); 
		} catch (IOException e) {
				e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2) {
		
		int tileSize = gp.getTileSize();
		g2.drawImage(currentImage ,x ,y ,tileSize ,tileSize ,null);
		
		//if the player is out of the map print "Player Lost"
		if(x > gp.screenWidth || y > gp.screenHeight || x < 0 || y < 0) {
			g2.setColor(Color.red); 
			g2.setFont(new Font("Arial", Font.PLAIN, 60));
			g2.drawString("Player Lost", 230
					, 100);
			
		}
	}

	
}
