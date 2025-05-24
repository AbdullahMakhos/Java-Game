package entity;

import java.awt.Color;
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
		if(kh.upPressed) {
			y -= speed;
		}
		if(kh.downPressed) {
			y += speed;
		}
		if(kh.rightPressed) {
			x += speed;
		}
		if(kh.leftPressed) {
			x -= speed;
		}
	} 
	//draw player method to keep GamePanel organized
	public void draw(Graphics2D g2) {
		getImage();//get the updated image
		//draw character image
		g2.drawImage(characterImage, x, y, gp.getTileSize(), gp.getTileSize(), null);
	}
	
	public void getImage() {
		try {
			characterImage = ImageIO.read(getClass().getResourceAsStream("/entity/resources/Untitled.png")); 
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
