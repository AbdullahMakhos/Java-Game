package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import objects.GameObject;
import javax.imageio.ImageIO;

public class UIManager {
	private GamePanel gp;
	private KeyHandler kh;
	private int gameState;
	private Inventory inventory;
	private boolean[] playerHealth;
	private boolean[] playerHunger;
	
	private BufferedImage heartImage;
	private BufferedImage inventoryImage;
	private BufferedImage menuImage;
	private BufferedImage hungerImage;
	
	public UIManager(GamePanel gp) {
		this.gp = gp;
		kh = gp.getKeyHandler();
		inventory = gp.getPlayer().getInventory();
		playerHealth = gp.getPlayer().getHealth();
		playerHunger = gp.getPlayer().getHunger();
		gameState = gp.getGameState();
		
		loadImages();
	}
	
	private void loadImages() {
		try {
			
			heartImage = ImageIO.read(getClass().getResourceAsStream("/main/resources/heart.png"));
			hungerImage = ImageIO.read(getClass().getResourceAsStream("/main/resources/hunger.png"));
			inventoryImage = ImageIO.read(getClass().getResourceAsStream("/main/resources/inventory.png"));
			menuImage = ImageIO.read(getClass().getResourceAsStream("/main/resources/menu.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2) {
		
		drawHealth(g2);
		drawHunger(g2);
		
		if(kh.ePressed) {
			
			drawInventory(g2);
		
		}else if(gameState == 1) {
		
			drawMenu(g2);
		
		}else if(gameState == 2) {
		
			drawGameOver(g2);
		
		}
		
		
		
	}
	
	private void drawInventory(Graphics2D g2) {
		
		g2.drawImage(inventoryImage, 200, 200, 600, 400, null);
		
		int x = 240; int y=248;
		int colCount = 0;
		for(GameObject item : inventory.getBag().keySet()) {
			
			g2.drawImage(item.getImage(), x, y ,50, 50,null);
			g2.drawString("x"+String.valueOf(inventory.getItemCount(item)), x+45, y+45);
			
			colCount++;
			x+=48;
			if(colCount > 10) {
				colCount = 0;
				x = 240;
				y += 48;
			}
		}
	
	}

	private void drawHealth(Graphics2D g2) {
		int healthX = 10; int healthY = 0;
		for(boolean h : playerHealth) {
			if(h) g2.drawImage(heartImage,healthX,healthY,48,48,null);
			healthX+=55;
		}
	}

	private void drawHunger(Graphics2D g2) {
		int hungerX = 10; int hungerY = 50;
		for(boolean h : playerHunger) {
			if(h) g2.drawImage(hungerImage,hungerX,hungerY,70,70,null);
			hungerX+=70;
		}
	}

	private void drawMenu(Graphics2D g2) {
		g2.drawImage(menuImage, 200, 200, 600, 400, null);
	}

	public void drawGameOver(Graphics2D g2) {
		g2.setFont(new Font("Arial", 2, 100));
		g2.setColor(Color.red);
		g2.drawString("GAME OVER", 250, 400);
	}

	public void updateGameState() {
		gameState = gp.getGameState();
	}
	
	public void updateHungerStatus() {
		playerHunger = gp.getPlayer().getHunger();
	}

	public void updateHealthStatus() {
		playerHealth = gp.getPlayer().getHealth();
	}
	
	
	
}
