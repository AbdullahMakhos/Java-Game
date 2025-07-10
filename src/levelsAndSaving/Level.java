package levelsAndSaving;


import java.io.IOException;

import main.GamePanel;
import tiles.Fish;

public class Level {
	private GamePanel gp;
	private int[][] tileMatrix;
	private int[][] objectMatrix;
	private int initialX,initialY;
	
	public Level(GamePanel gp,int[][] tileMatrix,int[][] objectMatrix) {
		this.gp = gp;
		this.tileMatrix = tileMatrix;
		this.objectMatrix = objectMatrix;
		resetXY();
	}	
	
	public void resetXY() {
		initialX = (getMapWidth() * gp.getTileSize())/2 - (gp.getTileSize()/2);
		initialY = gp.getTileSize()*2;
	}
	//can i pass to the next level
	public boolean canPass() throws IOException {
		Fish fish = new Fish();
		int ItemCount = 0;
		
		if(gp.getPlayer().getInventory().getItem(fish) != null) {
			ItemCount = gp.getPlayer().getInventory()
			.getItemCount(gp.getPlayer().getInventory().getItem(fish));
		}
		
		return ItemCount >= 26;
	}
	
	public int getInitialX() {
		return initialX;
	}

	public int getInitialY() {
		return initialY;
	}
	
	public void increaseInitialY() {
		initialY += gp.getTileSize()*2;
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
