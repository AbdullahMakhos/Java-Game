package myGame.levelsAndSaving;


import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import myGame.core.GamePanel;
import myGame.tiles.Fish;

public class Level {
	@JsonIgnore
	private GamePanel gp;
	@JsonIgnore
	private int[][] tileMatrix;
	@JsonProperty("objectMatrix")
	private int[][] objectMatrix;
	@JsonIgnore
	private int initialX,initialY;
	@JsonProperty("levelId")
	private int levelId;

	public Level(GamePanel gp,int[][] tileMatrix,int[][] objectMatrix) {
		this.gp = gp;
		this.tileMatrix = tileMatrix;
		this.objectMatrix = objectMatrix;
		resetXY();
	}	
	
	@JsonIgnore
	public void resetXY() {
		initialX = (getMapWidth() * gp.getTileSize())/2 - (gp.getTileSize()/2);
		initialY = gp.getTileSize()*2;
	}
	//can i pass to the next level
	@JsonIgnore
	public boolean canPass() throws IOException {
		Fish fish = new Fish();
		int ItemCount = 0;
		
		if(gp.getPlayer().getInventory().getItem(fish) != null) {
			ItemCount = gp.getPlayer().getInventory()
			.getItemCount(gp.getPlayer().getInventory().getItem(fish));
		}
		
		return ItemCount >= 26;
	}
	@JsonIgnore
	public int getInitialX() {
		return initialX;
	}
	@JsonIgnore
	public int getInitialY() {
		return initialY;
	}
	@JsonIgnore
	public void increaseInitialY() {
		initialY += gp.getTileSize()*2;
	}
	@JsonIgnore
	public int[][] getTileMatrix() {
		return tileMatrix;
	}
	
	@JsonIgnore
	private int getMapWidth() {
		return tileMatrix[0].length;
	}
	@JsonIgnore
	private int getMapHeight() {
		return tileMatrix.length;
	}
	@JsonIgnore
	public int getLevelId() {
		return levelId;
	}
	@JsonIgnore
	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}
	
	
	public void setObjectMatrix(int[][] objectMatrix) {
		this.objectMatrix = objectMatrix;
	}

	public int[][] getObjectMatrix() {
		return objectMatrix;
	}
}
