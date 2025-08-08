package myGame.Utilities;

import myGame.core.GamePanel;
import myGame.entities.Player;
import myGame.levelsAndSaving.Level;

public class LevelManager {
	private static final int LEVELS_NUMBER = 2;
	private GamePanel gp;
	private int currentLevelID;
	private Level[] levels;
	private boolean inside;
	
	private static final LevelManager instance = new LevelManager();
	public static LevelManager getInstance() {return instance;}
	private LevelManager() {
		this.gp = GamePanel.getInstance();
		currentLevelID = 0; 
		levels = new Level[LEVELS_NUMBER];
		updateInside();
		loadLevels();
	}
	
	private void updateInside() {
		inside = (currentLevelID == 0);
	}

	private void loadLevels() {
		
		int[][]houseTileMatrix = 
		{ {11,11,11,11,11,11,11,11,11}
		, {11, 0, 0, 0, 0, 0, 0, 0,11}
		, {11, 0, 0, 0, 0, 0, 0, 0,11}
		, {11, 0, 0, 0, 0, 0, 0, 0,11}
		, {11, 0, 0, 0, 0, 0, 0, 0,11}
		, {11,11,11,11, 0,11,11,11,11}
		};
		
		int[][]houseObjectMatrix = 
		{ {0,0,0,0,0,0,0,0,0}
		, {0,0,0,0,0,0,0,0,0}
		, {0,0,0,0,0,0,0,0,0}
		, {0,0,0,0,0,0,0,0,0}
		, {0,0,0,0,0,0,0,0,0}
		, {0,0,0,0,1,0,0,0,0}
		};
		
		levels[0] = new Level(gp,houseTileMatrix,houseObjectMatrix);
		
		int[][] tileMatrix0 = 
		    { {11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11} 
		    , {11, 0, 1, 0, 0, 0, 0, 0,11, 0, 0, 0,11, 1, 1, 1, 0, 0, 0, 0,11} 
		    , {11, 0, 1, 1, 0, 0, 0, 0,11, 0, 0, 0,11, 1, 0, 1, 1, 1, 0, 0,11} 
		    , {11, 0, 0, 1, 0, 1, 0, 0,11,11, 0,11,11, 0, 0, 1, 1, 1, 0, 0,11}
		    , {11, 0, 1, 1, 0, 1, 0, 0, 0, 0,14, 1, 0, 0, 1, 1, 1, 0, 1, 0,11}
		    , {11, 0, 0, 1, 1, 1, 0, 1, 1, 0,16, 1, 1, 0, 0, 1, 1, 0, 1, 1,11}
		    , {11, 0, 0, 0, 0, 0, 0, 0, 1, 0,16, 1, 1, 0, 1, 1, 1, 0, 0, 1,11}
		    , {11, 0, 1, 0, 0, 1, 0,13,17,17,18,17,17,17,17,20, 1, 0, 0, 0,11} 
		    , {11, 0, 0, 0, 0, 0, 0, 0, 1, 0,16, 0, 1, 0, 0,16, 0, 0, 0, 0,11} 
		    , {11, 0, 0, 0, 1, 1, 0, 1, 1, 0,16, 0, 1, 1, 1,16, 1, 1, 0, 0,11} 
		    , {11, 0, 0, 0, 0, 0, 0, 1, 0, 0,16, 1, 1, 1, 1,16, 0, 1, 0, 0,11}
		    , {11, 0, 1, 0, 1, 1, 0, 0, 0, 0,15, 0, 0, 1, 1,15, 0, 1, 1, 1,11}
		    , {11, 0, 0, 0, 0, 1, 2, 3, 3, 3, 3, 3, 3, 3, 4, 0, 1, 1, 0, 0,11}
		    , {11, 1, 1, 0, 0, 1, 5, 6, 6, 6, 6, 6, 6, 6, 7, 0, 1, 1, 0, 0,11}
		    , {11, 0, 1, 1, 0, 0, 5, 6, 6, 6, 6, 6, 6, 6, 7, 0, 0, 0, 0, 0,11}
		    , {11, 1, 1, 0, 0, 0, 5, 6, 6, 6, 6, 6, 6, 6, 7, 0, 0, 0, 0, 0,11}
		    , {11, 0, 1, 0, 0, 1, 8, 9, 9, 9, 9, 9, 9, 9,10, 0, 0, 0, 0, 0,11}
		    , {11, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0,11}
		    , {11, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0,11}
		    , {11, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0,11}
		    , {11, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0,11}
		    , {11, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0,11}
		    , {11, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0,11}
		    , {11, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,11}
		    , {11, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,11}
		    , {11, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,11}
		    , {11, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,11}
		    , {11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11}
		    }; 
		
		int[][] objectMatrix0 =
			{ {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0} 
			, {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0} 
			, {0,0,0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
			, {0,0,0,0,0,0,0,0,0,0,1,0,0,9,0,0,0,0,0,0,0}
			, {0,0,0,0,0,0,0,0,0,0,0,0,0,0,8,0,0,0,0,0,0}
			, {0,0,0,0,0,0,0,0,0,0,0,0,0,5,0,0,0,0,0,0,0}
			, {0,0,6,0,0,7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
			, {0,0,0,0,0,0,0,0,0,0,4,0,0,0,0,0,0,0,0,0,0}
			, {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
			, {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
			, {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0} 
			, {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
			, {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
			, {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
			, {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
			, {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
			, {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
			, {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
			, {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
			, {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
			, {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
			, {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
			, {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
			, {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
			, {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
			, {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
			, {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
			, {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
			};
		
		levels[1] = new Level(gp,tileMatrix0, objectMatrix0);
		 
		
	}

	public Level getCurrentLevel() {
	    if(currentLevelID < 0 || currentLevelID >= levels.length) {
	        throw new IllegalStateException("Invalid level ID: " + currentLevelID);
	    }
	    return levels[currentLevelID];
	}
	
	private void getOut() {
		setCurrentLevelID(1);
		getCurrentLevel().increaseInitialY();
		Player.getInstance().updateXY();
		getCurrentLevel().resetXY();
		updateInside();
	}
	
	private void getInside() {
	    setCurrentLevelID(0);
	    
	    // Clamp player position to house boundaries
	    Player player = Player.getInstance();
	    getCurrentLevel().increaseInitialY();
	    player.updateXY();
	    getCurrentLevel().resetXY();
	    updateInside();
	}
	
	public int getCurrentLevelID() {
		return currentLevelID;
	}
	
	public boolean isInside() {
		return inside;
	}

	public void doorMovement() {
		updateInside();
		if(inside) {
			getOut();
		}else {
			getInside();
		}
	}

	public void setCurrentLevelID(int currentLevelID) {
		this.currentLevelID = currentLevelID;
		updateInside();
		gp.updateCurrentLevel();
	}
}
