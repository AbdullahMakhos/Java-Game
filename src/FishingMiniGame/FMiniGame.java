package FishingMiniGame;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class FMiniGame {
	private boolean running;
	//private int cursorY;
	private BufferedImage backGroundImage;
	private BufferedImage CursorImage;
	private BufferedImage fishImage;
	
	public FMiniGame() throws IOException{
		running = false;
		
		loadImages();
	}
	
	private void loadImages() throws IOException {
		backGroundImage = ImageIO.read(getClass()
		.getResourceAsStream
		("/FishingMiniGame/resources/FishingMenu.png"));
	}

	public void update() {
		if(running) {
			
		}
	}
	
	public void draw(Graphics2D g2) {
		if(running) {
			int tileSize = GamePanel.getInstance().getTileSize(); 
			g2.drawImage(backGroundImage
			, GamePanel.getInstance().getScreenWidth() - (tileSize*4), tileSize*2 ,tileSize*3,tileSize*7, null);				
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
}