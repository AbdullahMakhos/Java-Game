package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

//this class represent the game panel so actions will be done here
//panel is added to the jFrame 
//its a subclass of jPanel so we can override its methods
//it implements runnable because it contains game loop so we need to create a game thread

class GamePanel extends JPanel implements Runnable{
 
	//size configurations
	final int originalTileSize = 16; //16x16 pixels
	final int scale = 3; //we will need this to adjust size (16 x 3 = 48)
    
	final int tileSize = scale * originalTileSize;//the actual size 48x48
	
	final int maxScreenCol = 16; //16 pixels vertically 
	final int maxScreenRow = 12; //16 pixels horizontally 
	
	final int screenWidth = tileSize * maxScreenCol; //total width 768 pixels
	final int screenHeigth = tileSize * maxScreenRow; //total Height 576 pixels
	
	KeyHandler kh = new KeyHandler(); // to create a key handler object
	Thread gameThread; // to create a thread object 

	//player's position and speed 
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;
	int FPS = 60;
	public GamePanel() {
		this.setVisible(true);
		this.setPreferredSize(new Dimension(screenWidth,screenHeigth));//size setting
		this.setBackground(Color.black);//setting background color
		this.setDoubleBuffered(true);//advanced
		this.addKeyListener(kh);//key listener added
		this.setFocusable(true);//to keep looking for event
		
		startGameThread();
	} 
	
	/*to set the game cycle we need to work with thread
	to get in touch with the fourth dimension(time)*/
	
	public void startGameThread() {
		
		gameThread = new Thread(this); //we pass the panel because run method is now one of its sub methods
		gameThread.start(); //بسم الله الرحمن الرحيم
		
	}
	//game loop is inside the run method
	@Override
	public void run() {	
		//setting update time to 60 per second
		double drawInterval = 1000000000/FPS;//update every (0.01666 s)
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		// game loop will keep repeating this as long as the game thread is alive
		while(gameThread != null) {
		//game loop consists of three stages
			
			currentTime = System.nanoTime(); // update current time
			
			delta += ( currentTime - lastTime ) / drawInterval; 
			
			lastTime = currentTime;
			
			if(delta >= 1) {
				//1.Update : update the game status such as players attributes (health, position...etc)
				update();
				//2.Draw : draw the screen with the updated frame information
				repaint();				
				delta--;
			}
		}
	}
	public void update() {
		
		//update player position
		if(kh.upPressed) {
			playerY -= playerSpeed;
		}
		if(kh.downPressed) {
			playerY += playerSpeed;
		}
		if(kh.rightPressed) {
			playerX += playerSpeed;
		}
		if(kh.leftPressed) {
			playerX -= playerSpeed;
		}
	
	}
	  
	//this is the draw method but we can't rename it because this is a java method
	public void paintComponent(Graphics g) {
		// call the java method 
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g; //convert the received graphics to 2d (usual procedure) because Graphics2d has some good functions  
		g2.setColor(Color.white); // set the color of the component
		g2.fillRect(playerX, playerY, tileSize, tileSize);//draw a rectangle just for testing purposes 
		
		g2.dispose(); //cleaning component to stay memory efficient 
	}
	
	
	 
}
