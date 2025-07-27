package myGame.tiles;

import java.io.IOException;

public class Tree extends GameObject{
	
	private final int ID = 6;
	
	public Tree() throws IOException {
		super();
		super.setCrossable(false); 
		super.setPickable(false);
		
		itemID = ID;
		imagePath = "/resources/images/external-pack/main-tree.png";
		
		loadImage();
	}
	
	public void behavior() {
		
	}

}
