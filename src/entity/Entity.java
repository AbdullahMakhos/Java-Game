package entity;
//a super class to all game entities (player,monster...etc)
public class Entity {
	public int x,y;
	public int speed;
	
	public Entity() {
		//default values
		this.x = 100;
		this.y = 100;
		this.speed = 4;
	}
}
