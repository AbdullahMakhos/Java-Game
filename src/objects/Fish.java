package objects;

import entity.Player;
import main.GamePanel;

public class Fish extends GameObject {
	
	public Fish(GamePanel gp) {
		super(gp);
	}

	@Override
	public void behavior() {
		Player player = gp.getPlayer();
		
		if(player.getStatus().canEat()) {
	
			if(player.getInventory().getItemCount(this) > 0) {
				player.getInventory().removeItem(this);
				player.getStatus().eat();
				gp.getUIManager().updateStatus();
			}
	
		}else {
			
			System.out.println("I'M FULL");
		
		}
		
		
	}
	
}
