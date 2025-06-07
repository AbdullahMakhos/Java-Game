package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import objects.GameObject;
import javax.imageio.ImageIO;

public class UIManager {
	private GamePanel gp;
	private KeyHandler kh;
	private boolean[] playerHealth;
	private ArrayList<GameObject> inventory;
	
	private BufferedImage heartImage;
	private BufferedImage inventoryImage;
	private BufferedImage menuImage;
	
	public UIManager(GamePanel gp) {
		this.gp = gp;
		kh = gp.getKeyHandler();
		playerHealth = gp.getPlayer().getHealth();
		inventory = gp.getPlayer().getInventory();
		
		loadImages();
	}
	
	private void loadImages() {
		try {
			heartImage = ImageIO.read(getClass().getResourceAsStream("/main/resources/heart.png"));
			inventoryImage = ImageIO.read(getClass().getResourceAsStream("/main/resources/inventory.png"));
			menuImage = ImageIO.read(getClass().getResourceAsStream("/main/resources/menu.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2) {
		drawHealth(g2);
		if(kh.ePressed) {
			drawInventory(g2);
		}
		if(kh.escPressed) {
			drawMenu(g2);
		}
	}
	
	private void drawInventory(Graphics2D g2) {
		
		g2.drawImage(inventoryImage, 200, 200, 600, 400, null);
		
		int x = 240; int y=248;
		int colCount = 0;
		for(GameObject item : inventory) {
			g2.drawImage(item.getImage(), x, y ,48, 48,null);
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
			if(h) g2.drawImage(heartImage,healthX,healthY,null);
			healthX+=48;
		}
	}


	private void drawMenu(Graphics2D g2) {
		g2.drawImage(menuImage, 200, 200, 600, 400, null);
	}

	
	
	
}
