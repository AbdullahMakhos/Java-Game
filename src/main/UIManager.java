package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import entity.playerThings.Inventory;
import entity.playerThings.Item;
import entity.playerThings.Status;

public class UIManager {
	private GamePanel gp;
	private int gameState;
	private Inventory inventory;
	private Status playerStatus;

	private BufferedImage heartImage;
	private BufferedImage menuImage;
	private BufferedImage hungerImage;
	private BufferedImage sideInventoryImage;
	private BufferedImage selectedItemImage;
	
	private int tileSize;
	private int selectedItemId;
	private int saveDrawCounter = 0;
	private int loadDrawCounter = 0;

	public UIManager() {
		gp = GamePanel.getInstance();
		gp.getKeyHandler();
		inventory = gp.getPlayer().getInventory();
		playerStatus = gp.getPlayer().getStatus(); 
		gameState = gp.getGameState();
		tileSize = gp.getTileSize(); //used for general object size too
		selectedItemId = inventory.getSelectedItemId();
		
		loadImages();
	}
	
	private void loadImages() {
		try {
			
			heartImage = ImageIO.read(getClass().getResourceAsStream("/main/resources/heart.png"));
			hungerImage = ImageIO.read(getClass().getResourceAsStream("/main/resources/hunger.png"));
			menuImage = ImageIO.read(getClass().getResourceAsStream("/main/resources/menu.png"));
			sideInventoryImage = ImageIO.read(getClass().getResourceAsStream("/main/resources/sideInventory.png"));
			selectedItemImage = ImageIO.read(getClass().getResourceAsStream("/main/resources/selected.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2) {
		g2.setFont(new Font( "Arial",Font.BOLD, tileSize/6));
		drawHealth(g2);
		drawHunger(g2);
		
		drawSideInventory(g2);
		
		if(gameState == 1) {
		
			drawMenu(g2);
		
		}else if(gameState == 2) {
		
			drawGameOver(g2);
		
		}
		
		if(saveDrawCounter  > 0) {
			
			drawSaving(g2);
			saveDrawCounter--;
			
		}
		
		if(loadDrawCounter  > 0) {
			
			drawLoading(g2);
			loadDrawCounter--;
			
		}
		
	}
	
	
	private void drawSaving(Graphics2D g2) {
		
		g2.setFont(new Font("Arial", Font.BOLD, 20));
		g2.setColor(Color.black);
		g2.drawString("Saving....",
			(gp.getScreenWidth() - 2*tileSize), 30);
	
	}
	
	private void drawLoading(Graphics2D g2) {
	
		g2.setFont(new Font("Arial", Font.BOLD, 20));
		g2.setColor(Color.black);
		g2.drawString("Loading....",
			(gp.getScreenWidth() - 2*tileSize), 30);
	
	}

	private void drawSideInventory(Graphics2D g2) {
		
		int invWidth = tileSize + tileSize/3;
		int invHeight = invWidth*inventory.getSideInventoryMaxSize();
		
		int startingX = tileSize/2;
		int startingY = gp.getScreenHeight() - invHeight - tileSize/2;
		
		g2.drawImage(sideInventoryImage, startingX,startingY,invWidth,invHeight,null);
		
		
		if(selectedItemId != -1 ) {
			g2.drawImage(selectedItemImage,startingX
			,startingY + invWidth*selectedItemId , invWidth , invWidth , null);
					
		}
		
		
		ArrayList<Item> bag = inventory.getInventory();
		
		for(int i=0 ; i<bag.size() ; i++) {
		   
		    g2.drawImage(bag.get(i).getItem().getImage(), startingX + invWidth / 7, startingY + invWidth / 7,
		            (invWidth * 3) / 4, (invWidth * 3) / 4, null);
		    
		    g2.drawString("x" + String.valueOf(bag.get(i).getCount()),
		            startingX + (invWidth * 3) / 4, startingY + (invWidth * 3) / 4);
		    
		    startingY += invHeight / inventory.getSideInventoryMaxSize();
		
		}
	
	}
	
	private void drawHealth(Graphics2D g2) {
		int healthX = 10; int healthY = 0;
		for(boolean h : playerStatus.getHealth()) {
			if(h) g2.drawImage(heartImage,healthX,healthY,48,48,null);
			healthX+=55;
		}
	}

	private void drawHunger(Graphics2D g2) {
		int hungerX = 10; int hungerY = 50;
		for(boolean h : playerStatus.getHunger()) {
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

	public void updateStatus() {
		playerStatus = gp.getPlayer().getStatus();
	}
	
	public void updateSelectedItemId() {
		selectedItemId = inventory.getSelectedItemId();
	}
	
	public void setSaveDrawCounter(int saveDrawCounter) {
		this.saveDrawCounter = saveDrawCounter;
	}

	public void setloadDrawCounter(int loadDrawCounter) {
		this.loadDrawCounter = loadDrawCounter;
	}

	public void updateInventory() {
		inventory = gp.getPlayer().getInventory();
	}
	
	
}
