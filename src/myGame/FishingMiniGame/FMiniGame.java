package myGame.FishingMiniGame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import myGame.core.GamePanel;
import myGame.tiles.GameObject;

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
	
	
	// Fish variables
	private int fishX;
	private int fishY;
	private int fishWidth;
	private int fishHeight;
	private int fishSpeed;
	private int fishDirection; // 1 for moving up, -1 for moving down
	private Random random;
	
	
	//fishing variables
	private double fishingSuccessRate;
	private int successBarWidth ;  
	private int successBarHeight; 
	private int successBarX; 
	private int successBarY;  

	public FMiniGame() throws IOException {
	    loadImages();
	    running = false;
	    tileSize = GamePanel.getInstance().getTileSize();
	    greenBarHeight = (tileSize*3)/2;
	    resetClickCounter();
	    FX = GamePanel.getInstance().getScreenWidth() - (tileSize*4);
	    FY = tileSize*2;
	    Fwidth = tileSize*2;
	    Fheight = tileSize*7;
	    minY = tileSize*2; //top y
	    maxY = FY + Fheight - greenBarHeight - (tileSize/7) ; //bottom y // -(tileSize/7) for better drawing
	    
	   
		successBarWidth = tileSize/4;  
		successBarHeight = Fheight; 
		successBarX = FX + Fwidth - (tileSize/3); 
		successBarY = FY;  

	    setDefaultValues();
	    	    
	}
	

	private void setDefaultValues() {
	 
	    greenBarX = FX + tileSize - (tileSize/10);
	    greenBarY = maxY;
	    
		
		
	    random = new Random();
	    fishWidth = tileSize;
	    fishHeight = tileSize/2;
	    fishX = FX + 2*(tileSize/3); 
	    fishY = minY + random.nextInt(maxY - minY - fishHeight);
	    fishSpeed = 1;
	    fishDirection = random.nextBoolean() ? 1 : -1; // Random initial direction

	    fishingSuccessRate = 0.0;
	}


	private void loadImages() throws IOException {
		backGroundImage = ImageIO.read(getClass()
		.getResourceAsStream
		("/resources/images/FishingMenu.png"));
		fishImage = ImageIO.read(getClass()
		.getResourceAsStream
		("/resources/images/fishIcon.png"));
	}

	public void update() throws IOException {
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
		    
		    
		    // Fish AI movement
	        fishY += fishSpeed * fishDirection;
	        
	        // Change direction randomly or when hitting boundaries
	        if(fishY <= minY || fishY >= maxY - fishHeight || random.nextInt(100) < 2) {
	            fishDirection *= -1; // Reverse direction
	        }
	        
	
	        // Random speed changes for more natural movement
	        if(random.nextInt(100) < 3) {
	            fishSpeed = 1 + random.nextInt(3); // Speed between 1-3
	        }
		}
		
		
		//fishing process 
		if(isCaught()) {
			fishingSuccessRate= Math.min(1.0,fishingSuccessRate+0.01);
		}else {
			fishingSuccessRate= Math.max(0.0,fishingSuccessRate-0.005);
		}
		if(fishingSuccessRate == 1.0) {
			GamePanel.getInstance().getPlayer()
			.getInventory().addItem(GameObject.createFromID(2));
			GamePanel.getInstance().setGameState(0);
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
	        
	        
	        // Draw fish
	        g2.drawImage(fishImage
    		, fishX
    		, fishY
    		, fishWidth
    		, fishHeight
    		,null);
		}
		
		// Draw vertical success rate indicator 
		// Background of the progress bar (empty)
		g2.setColor(Color.darkGray);
		g2.fillRect(successBarX, successBarY, successBarWidth, successBarHeight);
		
		// Filled portion based on success rate (grows upward)
		int filledHeight = (int)(successBarHeight * fishingSuccessRate);
		g2.setColor(new Color(0, 200, 0)); // Bright green
		g2.fillRect(successBarX, successBarY + (successBarHeight - filledHeight), 
		            successBarWidth, filledHeight);

		// Border
		g2.setColor(Color.BLACK);
		g2.drawRect(successBarX, successBarY, successBarWidth, successBarHeight);

	}

	//collision detector 
	private boolean isCaught() {
		return
		fishY > greenBarY &&
		fishY < greenBarY + greenBarHeight;
	} 
	
	public void start() {
		running = true;
	}
	
	public void close() {
		running = false;
		setDefaultValues();
	}

	public boolean isRunning() {
		return running;
	}
	
	private void resetClickCounter() {
		clickCounter = 0;
	}
	
}