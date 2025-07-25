package myGame.entity;

import java.awt.Rectangle;

import myGame.core.GamePanel;
//a super class to all game entities (player,monster...etc)
public class Entity {
	GamePanel gp;
	protected int worldX,worldY; //player's position on the map
	protected int speed; 
	protected int spriteID = 0;//to check which sprite to display
	protected int spriteCounter = 0;//helps us to change spriteID
	protected Rectangle solidArea; //the solid area to help us in collision 
	
	public Entity() {
		gp = GamePanel.getInstance();
	}
	
	public int getWorldX() {
		return this.worldX;
	}
	
	public int getWorldY() {
		return this.worldY;
	}
	 
	public int getSpeed() { 
		return this.speed;
	}
	
}
 