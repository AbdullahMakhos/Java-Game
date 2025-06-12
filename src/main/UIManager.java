package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
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
	private int tileSize;
	
	private BufferedImage heartImage;
	private BufferedImage menuImage;
	private BufferedImage hungerImage;
	private BufferedImage fullInventoryImage;
	private BufferedImage sideInventoryImage;
	
	public UIManager(GamePanel gp) {
		this.gp = gp;
		kh = gp.getKeyHandler();
		inventory = gp.getPlayer().getInventory();
		playerHealth = gp.getPlayer().getHealth();
		playerHunger = gp.getPlayer().getHunger();
		gameState = gp.getGameState();
		tileSize = gp.getTileSize(); //used for general object size too
		loadImages();
	}
	
	private void loadImages() {
		try {
			
			heartImage = ImageIO.read(getClass().getResourceAsStream("/main/resources/heart.png"));
			hungerImage = ImageIO.read(getClass().getResourceAsStream("/main/resources/hunger.png"));
			menuImage = ImageIO.read(getClass().getResourceAsStream("/main/resources/menu.png"));
			fullInventoryImage = ImageIO.read(getClass().getResourceAsStream("/main/resources/fullInventory.png"));
			sideInventoryImage = ImageIO.read(getClass().getResourceAsStream("/main/resources/sideInventory.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2) {
		g2.setFont(new Font( "Arial",Font.BOLD, tileSize/6));
		drawHealth(g2);
		drawHunger(g2);
		
		if(kh.ePressed) {
			
			drawFullInventory(g2);
		
		}else {
			
			drawSideInventory(g2);
		
		}
		
		if(gameState == 1) {
		
			drawMenu(g2);
		
		}else if(gameState == 2) {
		
			drawGameOver(g2);
		
		}
		
		
		
	}
	
	private void drawFullInventory(Graphics2D g2) {
		int invWidth = gp.getScreenWidth()/2;
		int invHeigth = gp.getScreenHeight()/2;
		
		int startingX = (gp.getScreenWidth()/2) - (invWidth/2) ;
		int startingY = (gp.getScreenHeight()/2) - (invHeigth/2);
		
		g2.drawImage(fullInventoryImage, startingX , 
		startingY , invWidth , invHeigth , null);
		
		int colCount = 0;
		
		startingX+=tileSize/2;
		startingY+=tileSize/2;
		
		for(GameObject item : inventory.getBag().keySet()) {
			
			g2.drawImage(item.getImage(),startingX , startingY ,tileSize, tileSize,null);
			
			g2.drawString("x"+String.valueOf(inventory.getItemCount(item))
			, startingX + tileSize/3 , startingY+tileSize);
			
			colCount++;
			startingX+=48;
			if(colCount > 10) {
				colCount = 0;
				startingX = (gp.getScreenWidth()/2) - (invWidth/2) ;
				startingY += tileSize;
			}
		}
	
	}

	private void drawSideInventory(Graphics2D g2) {
		int invWidth = tileSize*2 + tileSize/3;
		int invHeigth = inventory.getSideInventoryMaxSize()*(tileSize + tileSize/3);
		
		int startingX = 0;
		int startingY = gp.getScreenHeight() - (invHeigth);
		
		g2.drawImage(sideInventoryImage, startingX,startingY,invWidth,invHeigth,null);
		
		startingX += invWidth/4 + 5;			
		startingY += tileSize/2;
				
		for(GameObject item : inventory.getBag().keySet()) {
			g2.drawImage(item.getImage() , startingX , startingY, tileSize, tileSize, null);
 
			g2.drawString("x"+String.valueOf(inventory.getItemCount(item))
			, startingX+tileSize/2 , startingY+tileSize);
			
			startingY += invHeigth/inventory.getSideInventoryMaxSize();
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
		g2.setFont(new Font("Arial", Font.BOLD, 100));
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
