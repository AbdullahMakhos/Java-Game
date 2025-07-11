package entity.playerThings;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	public Status() {
		// TODO Auto-generated constructor stub
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
	@JsonIgnore
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
 
	@JsonIgnore
	public boolean canEat() {
		return !Arrays.equals(hunger,new boolean[] {true,true,true});
	}
	@JsonIgnore
	public void eat() {
		starving = false;

		for(int i=0 ; i<hunger.length ; i++) {
			if(!hunger[i]) {
				hunger[i] = true;
				break;
			}
		}
		
	}
	@JsonIgnore
	public void heal() {
		for(int i=0 ; i < TOTAL_HEALTH ; i++) {
			if(!health[i]) {
				health[i] = true;
				break;
			}
		}
	}
	@JsonIgnore
	public boolean canHeal() {
		return !Arrays.equals(health,new boolean[] {true,true,true});
	} 

	public void setHealth(boolean[] health) { this.health = health; }
	public void setHunger(boolean[] hunger) { this.hunger = hunger; }
	public void setStarving(boolean starving) { this.starving = starving; }
	public boolean isStarving() { return starving; }
	public boolean[] getHealth() { return health; }
	public boolean[] getHunger() { return hunger; }
	
}
