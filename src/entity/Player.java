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
		 
		if(x > gp.screenWidth || y > gp.screenHeigth || x < 0 || y < 0) {
			g2.setColor(Color.red);
			g2.setFont(new Font(null, 0, 60));
			g2.drawString("Player Lost", 230
					, 100);
			
		}
	}
	

	
}
