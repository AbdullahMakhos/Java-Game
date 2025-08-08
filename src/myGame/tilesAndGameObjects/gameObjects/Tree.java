package myGame.tilesAndGameObjects.gameObjects;

import java.io.IOException;

public class Tree extends GameObject{
	
	private int treeID;
	
	private Tree(int treeID) throws IOException {
		super();
		super.setCrossable(false); 
		super.setPickable(false);
		this.treeID = treeID;
		
		itemID = 6;
		
		if(treeID == 1) {
			imagePath = "/resources/images/gameObjects/tree1.png";			
			setObjectSize(getObjectSize()*2);
		}else if(treeID == 2) {
			imagePath = "/resources/images/gameObjects/tree2.png";
			setObjectSize(getObjectSize()*2);
		}
		
		loadImage();
	}
	
	public void behavior() {
		
	}
	
	public static Tree createFromID(int treeID) throws IOException {
		return new Tree(treeID); 
	}

	public int getTreeID() {
		return treeID;
	}
}
