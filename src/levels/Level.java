package levels;

import java.awt.event.ItemEvent;

import main.GamePanel;
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
		int ItemCount = 0;
		
		if(gp.getPlayer().getInventory().getItem(fish) != null) {
			ItemCount = gp.getPlayer().getInventory()
			.getItemCount(gp.getPlayer().getInventory().getItem(fish));
		}
		
		return ItemCount >= 5;
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
