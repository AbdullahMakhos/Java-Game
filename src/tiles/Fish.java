package tiles;

import java.io.IOException;

import entity.Player;
import main.GamePanel;

public class Fish extends GameObject {
	private final int ID = 2;
	
	public Fish() throws IOException {
		super();
		super.setCrossable(true); 
		super.setPickable(true);
		itemID = ID;
		imagePath = "/tiles/resources/fish.png";
		
		loadImage();
		
	}

	public void behavior() {
		Player player = GamePanel.getInstance().getPlayer();
		
		if(player.getStatus().canEat()) {
				player.getInventory().removeItem(this);
				player.getStatus().eat();
				gp.getUIManager().updateStatus();
	
		}else {
			
			System.out.println("I'M FULL");
		
		}
		
		
	}
	
	
}
