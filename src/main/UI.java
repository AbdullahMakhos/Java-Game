package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class UI {
	private GamePanel gp;
	private boolean[] playerHealth;
	private BufferedImage heartImage;
	
	public UI(GamePanel gp) {
		this.gp = gp;
		this.playerHealth = gp.getPlayer().getHealth();
		
		loadImages();
	}
	
	private void loadImages() {
		try {
			heartImage = ImageIO.read(getClass().getResourceAsStream("/main/resources/heart.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void draw(Graphics2D g2) {
		int x = 10; int y = 0;
		for(boolean h : playerHealth) {
			if(h) g2.drawImage(heartImage, x, y, null);
			x+=48;
		}
	}
	
	
}
