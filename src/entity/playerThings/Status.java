package entity.playerThings;

import java.util.Arrays;

import main.GamePanel;

public class Status {
	GamePanel gp;
	
	private static final int TOTAL_HEALTH = 3;
	private static final int TOTAL_HUNGER = 3;

	private boolean[] health;
	private boolean[] hunger;
	private boolean starving;
	
	public Status(GamePanel gp) {
		this.gp = gp;
		
		health = new boolean[TOTAL_HEALTH];
		hunger = new boolean[TOTAL_HUNGER];
		starving = false;
		
		Arrays.fill(health, true);
		Arrays.fill(hunger, true);
	}
	
	public void takeDamage() {
		
		int lastHeartIndex = -1;
		
		for(int i=TOTAL_HEALTH - 1 ; i >= 0 ; i--) {
			if(health[i]) {
				lastHeartIndex = i;
				break;
			}
		}
		
		if(lastHeartIndex != -1) {
			health[lastHeartIndex] = false;
		}
		
	}
	
	public void reduceHunger() {
		
		if(starving) {
			for(int i=TOTAL_HEALTH - 1 ; i>=0 ; i--) {
				if(health[i]) {
					health[i] = false;
					if(i == 0) {
						gp.setGameState(2); //game over
					}
					break;
				}
			}
			
		}else {
			
			for(int i=TOTAL_HUNGER - 1 ; i>=0 ; i--) {
				if(hunger[i]) {
					hunger[i] = false;
					
					if(i == 0) {
					
						starving = true;
					
					}else {
					
						starving = false;
					
					}
					
					break;
				}
			}
		}
		
		
	}
 
	
	public boolean canEat() {
		return !Arrays.equals(hunger,new boolean[] {true,true,true});
	}

	public void eat() {
		starving = false;

		for(int i=0 ; i<hunger.length ; i++) {
			if(!hunger[i]) {
				hunger[i] = true;
				break;
			}
		}
		
	}
	
	public void heal() {
		for(int i=0 ; i < TOTAL_HEALTH ; i++) {
			if(!health[i]) {
				health[i] = true;
				break;
			}
		}
	}

	public boolean canHeal() {
		return !Arrays.equals(health,new boolean[] {true,true,true});
	} 

	public boolean isStarving() {
		return starving;
	}

	public boolean[] getHealth() {
		return health;
	}
	
	public boolean[] getHunger() {
		return hunger;
	}
	
}
