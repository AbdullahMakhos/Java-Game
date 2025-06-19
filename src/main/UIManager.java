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
	private KeyHandler kh;
	private int gameState;
	private Inventory inventory;
	private Status playerStatus;

	private BufferedImage heartImage;
	private BufferedImage menuImage;
	private BufferedImage hungerImage;
	private BufferedImage fullInventoryImage;
	private BufferedImage sideInventoryImage;
	private BufferedImage selectedItemImage;
	
	private int tileSize;
	private int selectedItemId;
	
	public UIManager(GamePanel gp) {
		this.gp = gp;
		kh = gp.getKeyHandler();
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
			fullInventoryImage = ImageIO.read(getClass().getResourceAsStream("/main/resources/fullInventory.png"));
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
		int invHeight = gp.getScreenHeight()/2;
		
		int startingX = (gp.getScreenWidth()/2) - (invWidth/2) ;
		int startingY = (gp.getScreenHeight()/2) - (invHeight/2);
		
		g2.drawImage(fullInventoryImage, startingX , 
		startingY , invWidth , invHeight , null);
		
		int colCount = 0;
		
		startingX+=tileSize/2;
		startingY+=tileSize/2;
		
		for(Item item : inventory.getBag()) {
			
			g2.drawImage(item.getItem().getImage(),startingX , startingY ,tileSize, tileSize,null);
			
			g2.drawString("x"+String.valueOf(item.getCount())
			, startingX + tileSize/2 , startingY+tileSize);
			
			colCount++;
			startingX += ( (tileSize) + (tileSize/2));
			if(colCount > 10) {
				colCount = 0;
				startingX = (gp.getScreenWidth()/2) - (invWidth/2) ;
				startingY += tileSize;
			}
		}
		
		
	
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
		
		
		ArrayList<Item> bag = inventory.getBag();
		
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
	
	
}
