package myGame.tiles;

import java.io.IOException;

import myGame.core.GamePanel;
import myGame.entity.Player;


public class Fish extends GameObject {
	private final int ID = 2;
	
	public Fish() throws IOException {
		super();
		super.setCrossable(true); 
		super.setPickable(true);
		itemID = ID;
		imagePath = "/resources/images/fish.png";
		
		loadImage();
		
	}

	public void behavior() {
		Player player = GamePanel.getInstance().getPlayer();
		
		if(player.getStatus().canEat()) {
				player.getInventory().removeItem(this);
				player.getStatus().eat();
				gp.getUIManager().updateStatus();
	
		}
	}
	
	
}
