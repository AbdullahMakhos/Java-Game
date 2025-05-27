package entity;


//a super class to all game entities (player,monster...etc)
public class Entity {
	public int worldX,worldY; //player's position on the map
	protected int speed; 
	protected int spriteID = 0;//to check which sprite to display
	protected int spriteCounter = 0;//helps us to change spriteID
	   
}
 