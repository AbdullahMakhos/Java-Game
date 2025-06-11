package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import entity.Player;
import levels.Level;
import levels.LevelManager;
import objects.ObjectCollisionDetector;
import objects.ObjectManager;
import tiles.TileCollisionDetector;
import tiles.TileManager;

//this class represent the game panel so actions will be done here
//panel is added to the jFrame 
//its a subclass of jPanel so we can override its methods
//it implements runnable because it contains game loop so we need to create a game thread

public class GamePanel extends JPanel implements Runnable{
  
	//size configurations
	private final int originalTileSize = 16; //16x16 pixels
	private final int scale = 4; //we will need this to adjust size (16 x 3 = 48)
    
	private final int tileSize = scale * originalTileSize;//the actual size 48x48  
	
	private final int maxScreenCol = 16; //16 pixels vertically 
	private final int maxScreenRow = 12; //16 pixels horizontally 
	
	public final int screenWidth = tileSize * maxScreenCol; //total width 768 pixels
	public final int screenHeight = tileSize * maxScreenRow; //total Height 576 pixels
	
	private final int maxWorldCol; //16 pixels vertically 
	private final int maxWorldRow; //16 pixels horizontally 
	
	private int gameState; // 0 for running 1 for paused 2 for gameOver
	
	private final int FPS = 60; 
	private Level currentLevel;
	 
	//game object initiating area
	private KeyHandler kh;// to create a key handler object 
	private LevelManager lm;
	private Player player; 
	private TileManager tm;
	private ObjectManager om;
	private TileCollisionDetector td;
	private ObjectCollisionDetector od;
	private Thread gameThread; // to create a thread object 
	private UIManager ui;
	private int currentLevelID;
	   
	
	private int hungerCounter = 0;
	
	
	public GamePanel() {
		gameState = 0;
		currentLevelID=0;
		
		kh= new KeyHandler(); // to create key handler
		lm = new LevelManager(this);
		
		currentLevel = lm.getCurrentLevel();

		player =  new Player(this);// to create a player object
		tm = new TileManager(this); //to create a tile manager
		om = new ObjectManager(this);
		td = new TileCollisionDetector(this); //to create colDetecer 
		od = new ObjectCollisionDetector(this);
		ui = new UIManager(this);
		
		
		maxWorldCol = getMapWidth();
		maxWorldRow = getMapHeight();
		
		this.setVisible(true);
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));//size setting
		this.setBackground(Color.white);//setting background color
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
		while(gameThread.isAlive()) {
		//game loop consists of three stages
			
			currentTime = System.nanoTime(); // update current time
			
			delta += ( currentTime - lastTime ) / drawInterval; 
			
			lastTime = currentTime;
			
			if(delta >= 1) {
				hungerCounter++;
				if(hungerCounter > 1000) {
					hungerCounter = 0;
					player.reduceHunger();
					ui.updateHungerStatus();
				}
				//1.Update : update the game status such as players attributes (health, position...etc)
				update();
				//2.Draw : draw the screen with the updated frame information
				repaint();
				delta--;
			}
		}
	} 
	
	public void update() { 
		
		if(kh.escPressed) {
			
			setGameState(1); //1 for paused
		
		}
		
		if(gameState == 0) player.update(); //update player 
		if(gameState == 2) ui.updateGameState(); //update player 
		
	}
	   
	//this is the draw method but we can't rename it because this is a java method
	public void paintComponent(Graphics g) {
		// call the java method 
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g; //convert the received graphics to 2d (usual procedure) because Graphics2d has some good functions  
		g2.setFont(new Font("Arial", Font.BOLD, 20));
		g2.setColor(Color.white);
		tm.draw(g2); //place it before player's draw
		player.draw(g2);
		om.draw(g2);
		ui.draw(g2);
		g2.dispose(); //cleaning component to stay memory efficient 
	
	}
	
	
	public void updateCurrentLevel(){
		currentLevel = lm.getCurrentLevel();
		tm.updateTileMatrix();
		om.updateObjectMatrix();
	}
	
	public int getGameState() {
		return gameState;
	}
	
	public void setGameState(int gameState) {
		this.gameState = gameState;
		ui.updateGameState();
	}

	public int getScreenWidth() {
		return screenWidth;
	}
	
	public int getScreenHeight() {
		return screenHeight;
	}
	
	public int getTileSize() {
		//because we are going to use it inside Entity subclasses
		return this.tileSize;
	}

	public int getMapWidth() {
		return currentLevel.getTileMatrix()[0].length;
	}
	 
	public int getMapHeight() {
		return currentLevel.getTileMatrix().length;
	}
	
	public int getMaxWorldCol() {
		return maxWorldCol;
	}
	 
	public int getMaxWorldRow() {
		return maxWorldRow;
	}

	public TileManager getTileManager() {
		return tm;
	}

	public Player getPlayer() {
		return player;
	}
	
	public KeyHandler getKeyHandler() {
		return kh; 
	}
	
	public TileCollisionDetector getTileCollisionDetecter() {
		return td;
	}
	
	public ObjectCollisionDetector getObjectCollisionDetecter() {
		return od;
	}

	public ObjectManager getObjectManager() {
		return om;
	}

	public UIManager getUIManager() {
		return ui;
	}
	
	public int[][] getTileMatrix(){
		return currentLevel.getTileMatrix();
	}

	public int[][] getObjectMatrix() {
		return currentLevel.getObjectMatrix();
	}

	
	public void setCurrentLevelID(int currentLevelID){
		this.currentLevelID = currentLevelID;
	}
	
	public int getCurrentLevelID() {
		return currentLevelID;
	}
	
	public Level getCurrentLevel() {
		return currentLevel;
	}
	
	public LevelManager getLevelManager() {
		return lm;
	}

}
