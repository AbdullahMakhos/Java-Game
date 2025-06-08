package main;

import objects.Fish;

public class Level {
	private GamePanel gp;
	private int[][] tileMatrix;
	private int[][] objectMatrix;
	int initialX,initialY;
	
	public Level(GamePanel gp,int[][] tileMatrix,int[][] objectMatrix) {
		this.gp = gp;
		this.tileMatrix = tileMatrix;
		this.objectMatrix = objectMatrix;
		initialX = (getMapWidth() * gp.getTileSize())/2 - (gp.getTileSize()/2);
		initialY = gp.getTileSize()*2;
	}	
	
	//can i pass to the next level
	public boolean canPass() {
		Fish fish = new Fish();
		if(gp.getUIManager().itemCount(fish) >= 5) {
			gp.getLevelManager().goToNextLevel();
			gp.updateCurrentLevel();
			return true;
		}
		return false;
	}
	public int getInitialX() {
		return initialX;
	}

	public int getInitialY() {
		return initialY;
	}
	
	public int[][] getTileMatrix() {
		return tileMatrix;
	}
	
	public int[][] getObjectMatrix() {
		return objectMatrix;
	}
	
	private int getMapWidth() {
		return tileMatrix[0].length;
	}
	
	private int getMapHeight() {
		return tileMatrix.length;
	}
}
