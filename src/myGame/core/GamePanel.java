package myGame.core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import myGame.FishingMiniGame.FMiniGame;
import myGame.Utilities.KeyHandler;
import myGame.Utilities.LevelManager;
import myGame.Utilities.MapManager;
import myGame.Utilities.SoundManager;
import myGame.Utilities.UIManager;
import myGame.entities.Player;
import myGame.levelsAndSaving.Level;
import myGame.levelsAndSaving.SavePoint;
import myGame.tiles.ObjectCollisionDetector;
import myGame.tiles.TileCollisionDetector;


//this class represent the game panel so actions will be done here
//panel is added to the jFrame 
//its a subclass of jPanel so we can override its methods
//it implements runnable because it contains game loop so we need to create a game thread

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable{
	
	private static GamePanel instance;
	
	public static GamePanel getInstance() {
        return instance;
    }
	
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
	
	private int gameState; // 0 for running, 1 for paused, 2 for gameOver , 3 for fishing
	
	private final int FPS = 60; 
	private Level currentLevel;
	 
	//game object initiating area
	private KeyHandler kh;// to create a key handler object 
	private LevelManager levelManager;
	private SoundManager soundManager;
	private	Player player; 
	private MapManager mapManager;
	private TileCollisionDetector td;
	private ObjectCollisionDetector od;
	private Thread gameThread; // to create a thread object 
	private UIManager uiManager;
	private FMiniGame fMiniGame;
	private int currentLevelID;
	private SavePoint lastSavePoint;
	private long metabolismCounter = 0;
	private int escCounter = 0;
	private int f4Counter = 0;
	private int f5Counter = 0;
	
	//j s o n handling
	ObjectMapper objectMapper = new ObjectMapper();
	final String SAVE_FILE_PATH = "/home/abdullah/Projects/java/Penguime/src/resources/save/lastSaveInfo.json";
	
	public GamePanel() throws StreamReadException, DatabindException, IOException {
		
		
		instance = this;
		gameState = 0;
		currentLevelID=0;
		
		kh = KeyHandler.getInstance(); 
		levelManager = LevelManager.getInstance();
		soundManager = SoundManager.getInstance();
		
		currentLevel = levelManager.getCurrentLevel();

		player = Player.getInstance();// to create a player object
		mapManager = MapManager.getInstance(); //to create a tile manager
		td = TileCollisionDetector.getInstance(); //to create colDetecer 
		od = ObjectCollisionDetector.getInstance();
		uiManager = UIManager.getInstance();
		fMiniGame = FMiniGame.getInstance();
		lastSavePoint = SavePoint.getInstance();
		
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
		soundManager.playMusic();
	}
	
	//game loop is inside the run method
	@Override
	public void run() {
	 
	    // Existing timing variables
	    double drawInterval = 1000000000/FPS;
	    double delta = 0;
	    long lastTime = System.nanoTime();
	    long currentTime;
	    
	    while(gameThread.isAlive()) {
	        currentTime = System.nanoTime();
	        delta += (currentTime - lastTime) / drawInterval;
	        lastTime = currentTime;
	        
	        
	        if(delta >= 1) {
	        	
	        	    try {
		                update();
		            } catch (Exception e) {
		                e.printStackTrace();
		            }
	        	    repaint();
		            delta--;
	        	}
	       
	    }
	}
	
	public void update() throws Exception { 
		
		if(kh.escPressed) {
			escCounter++;
			if(escCounter > 5) {
				reseteEscCounter();
				if(getGameState() == 1 || gameState == 3) {
					setGameState(0);
				}else{
					setGameState(1);
				}
			}
			
		}
		
		if(kh.f4Pressed) {
			f4Counter++;
			if(f4Counter > 5) {
				resetF4Counter();;
				SaveCurrentInfo();
			}
		}
		
		if(kh.f5Pressed) {
			f5Counter++;
			if(f5Counter > 5) {
				resetF5Counter();
				loadLastSaveInfo();
			}
		}
		
		if(gameState == 0) player.update(); //update player 
		if(gameState == 2) uiManager.updateGameState(); //update player 
		if(gameState == 3) fMiniGame.update();
	
		metabolismCounter++;
		if(metabolismCounter > FPS*20 ) { //heal every 20 seconds
			
			if(metabolismCounter > FPS*60*3) { //get hungry every three minutes
				resetMetabolismCounter();
				player.getStatus().reduceHunger();
			}
			
			if(!player.getStatus().canEat() 
			&& player.getStatus().canHeal()) {
				player.getStatus().heal();					
			}

			uiManager.updateStatus();
		}
	}

	//this is the draw method but we can't rename it because this is a java method
	public void paintComponent(Graphics g) {
		// call the java method 
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g; //convert the received graphics to 2d (usual procedure) because Graphics2d has some good functions  
		g2.setFont(new Font("Arial", Font.BOLD, 20));
		g2.setColor(Color.white);
		mapManager.draw(g2); //place it before player's draw
		player.draw(g2);
		uiManager.draw(g2);
		if(gameState == 3) fMiniGame.draw(g2);
		g2.dispose(); //cleaning component to stay memory efficient	
	}
	
	private void SaveCurrentInfo() throws Exception {
	    ObjectMapper mapper = new ObjectMapper();
	   
	    SavePoint savePoint = new SavePoint(
	        player.getWorldX(),
	        player.getWorldY(),
	        player.getStatus(),					
	        player.getInventory(),
	        currentLevelID,
	        currentLevel.getObjectMatrix()
	    );
	    
	    mapper.writerWithDefaultPrettyPrinter()
        .writeValue(new File(SAVE_FILE_PATH), savePoint);
	    
	    uiManager.setSaveDrawCounter(35);
	}

	private void loadLastSaveInfo() throws Exception {
	    ObjectMapper mapper = new ObjectMapper();
	    
	    File saveFile = new File(SAVE_FILE_PATH);
	    if (!saveFile.exists()) return;
	    
	    SavePoint savePoint = mapper.readValue(saveFile, SavePoint.class);
	    
	    if (savePoint != null && savePoint.isModified()) {
	        player.setX(savePoint.getX());
	        player.setY(savePoint.getY());
	        player.setStatus(savePoint.getStatus());
	        player.getInventory().setInventory(savePoint.getInventory().getInventory());
	        levelManager.setCurrentLevelID(savePoint.getLevelId());
	        levelManager.getCurrentLevel().setObjectMatrix(savePoint.getObjectMatrix());
	    }
	    
	    mapManager.updateMap();
	    uiManager.updateStatus();
	    uiManager.setloadDrawCounter(25);
	}
	
	public void updateCurrentLevel(){
		currentLevelID = levelManager.getCurrentLevelID();
		currentLevel = levelManager.getCurrentLevel();
		mapManager.updateMap();
	}
	
	public void resetMetabolismCounter() {
		metabolismCounter = 0;
	}
	
	public int getGameState() {
		return gameState;
	}
	
	public void setGameState(int gameState) {
		this.gameState = gameState;
		uiManager.updateGameState();
		if(getGameState() == 3) {
			fMiniGame.start();
		}else {
			fMiniGame.close();
		}
	}
	
	public int getScreenWidth() {
		return screenWidth;
	}
	
	public int getScreenHeight() {
		return screenHeight;
	}
	
	public int getTileSize() {
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

	public int getMaxScreenCol() {
		return maxScreenCol;
	}

	public int getMaxScreenRow() {
		return maxScreenRow;
	}
	
	public void reseteEscCounter() {
		escCounter = 0;
	}
	
	public void resetF4Counter() {
		f4Counter = 0;
	}

	public void resetF5Counter() {
		f5Counter = 0;
	}
	
	public int getScale() {
		return scale;
	}

}
