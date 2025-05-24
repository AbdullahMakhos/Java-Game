package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{
	GamePanel gp;//the gamePanel to be displayed on
	KeyHandler kh; //the KeyHandler to accept input
	BufferedImage characterImage;  //to hold player's image
	
	public Player(GamePanel gp,KeyHandler kh) {
		super();//call the Entity constructor to get default values 
		this.gp = gp;
		this.kh = kh;
	}

	//update player method to keep GamePanel organized 
	public void update() {
		//update information about player's position and image
		
		try {
			characterImage = ImageIO.read(getClass()
					.getResourceAsStream("/entity/resources/right and front.png")); 
		
			if(kh.upPressed) {
				y -= speed;
				characterImage = ImageIO.read(getClass()
						.getResourceAsStream("/entity/resources/right and front.png")); 
			}
			if(kh.downPressed) {
				y += speed;
				characterImage = ImageIO.read(getClass()
						.getResourceAsStream("/entity/resources/left and front.png")); 
			}
			if(kh.rightPressed) {
				x += speed;
				characterImage = ImageIO.read(getClass()
						.getResourceAsStream("/entity/resources/right and front.png")); 
			}
			if(kh.leftPressed) {
				x -= speed;
				characterImage = ImageIO.read(getClass()
						.getResourceAsStream("/entity/resources/left and front.png")); 
			}
		} catch (IOException e) {
				e.printStackTrace();
		}
		
	} 
	//draw player method to keep GamePanel organized
	public void draw(Graphics2D g2) {
				
		g2.drawImage(characterImage, x, y,
		gp.getTileSize(), gp.getTileSize(), null); //draw character image
	
	}
	

	
	
}
