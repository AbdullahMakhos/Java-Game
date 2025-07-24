package FishingMiniGame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class FMiniGame {
	private boolean running;
	private int tileSize;

	private BufferedImage backGroundImage;
	private BufferedImage fishImage;
	
	private int FX;
	private int FY;
	private int Fwidth;
	private int Fheight;
	private int greenBarX;
	private int greenBarY;
	private int greenBarHeight;
	
	private int maxY;
	private int minY; 
	
	private int clickCounter;
	
	public FMiniGame() throws IOException {
	    loadImages();
	    running = false;
	    tileSize = GamePanel.getInstance().getTileSize();
	    greenBarHeight = tileSize;
	    resetClickCounter();
	    
	    FX = GamePanel.getInstance().getScreenWidth() - (tileSize*4);
	    FY = tileSize*2;
	    Fwidth = tileSize*2;
	    Fheight = tileSize*7;
	    minY = tileSize*2; //top y
	    
	    // Fixed maxY calculation:
	    maxY = FY + Fheight - greenBarHeight - (tileSize/7) ; //bottom y // -(tileSize/7) for better drawing
	    
	    greenBarX = FX + tileSize - (tileSize/10);
	    greenBarY = maxY;
	    
	}
	

	private void loadImages() throws IOException {
		backGroundImage = ImageIO.read(getClass()
		.getResourceAsStream
		("/FishingMiniGame/resources/FishingMenu.png"));
	}

	public void update() {
		if(running) {
			if (GamePanel.getInstance().getKeyHandler().spacePressed) {
		        clickCounter++;
		    }

		    if (clickCounter > 2) {
		    	if(greenBarY > minY + 10) {
		    		greenBarY -= 10; // Move up		    		
		    	}
		        resetClickCounter(); 
		    } else if (clickCounter <= 10){
		        if (greenBarY < maxY + 2) {
		        	greenBarY+=2; // Move down 
		        }
		    }
		}
	}
	
	public void draw(Graphics2D g2) {
		if(running) {
			g2.drawImage(backGroundImage
			,FX
			,FY 
			,Fwidth
			,Fheight
			, null);				
			
			g2.setColor(Color.GREEN);
	        g2.fillRect(
	            greenBarX,
	            greenBarY,
	            tileSize / 2,
	            greenBarHeight
	        );
		}
	}
	
	public void start() {
		running = true;
	}
	
	public void close() {
		running = false;
	}

	public boolean isRunning() {
		return running;
	}
	
	private void resetClickCounter() {
		clickCounter = 0;
	}
}