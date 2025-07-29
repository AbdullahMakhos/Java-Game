package myGame.tiles;

import java.io.IOException;

import myGame.Utilities.UIManager;
import myGame.entities.Player;


public class Fish extends GameObject {
	private final int ID = 2;
	
	public Fish() throws IOException {
		super();
		super.setCrossable(true); 
		super.setPickable(true);
		itemID = ID;
		imagePath = "/resources/images/gameObjects/fish.png";
		
		loadImage();
		
	}

	public void behavior() {
		Player player = Player.getInstance();
		
		if(player.getStatus().canEat()) {
				player.getInventory().removeItem(this);
				player.getStatus().eat();
				 UIManager.getInstance().updateStatus();
	
		}
	}
	
	
}
