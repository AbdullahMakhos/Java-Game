package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class UI {
	private GamePanel gp;
	private KeyHandler kh;
	private BufferedImage heartImage;
	private boolean[] playerHealth;
	ArrayList<Object> inventory;
	public UI(GamePanel gp) {
		this.gp = gp;
		kh = gp.getKeyHandler();
		playerHealth = gp.getPlayer().getHealth();
		inventory = gp.getPlayer().getInventory();
		
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
		drawHealth(g2);
		if(kh.ePressed) drawInventory(g2);
		
	}
	
	public void updateHealth() {
		playerHealth = gp.getPlayer().getHealth();
	}
	
	private void drawInventory(Graphics2D g2) {
		ArrayList<Object> inventory = gp.getPlayer().getInventory();
		for(Object item : inventory) {
			g2.drawString("item", 100, 50);				
		}
	
	}

	private void drawHealth(Graphics2D g2) {
		int healthX = 10; int healthY = 0;
		for(boolean h : playerHealth) {
			if(h) g2.drawImage(heartImage,healthX,healthY,null);
			healthX+=48;
		}
	}
	
	
}
