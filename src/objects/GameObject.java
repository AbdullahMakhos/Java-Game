package objects;

import java.awt.image.BufferedImage;

import tiles.Tile;

//objects like box fire...etc not monsters , monsters will be entities 

public class GameObject {
	protected BufferedImage image; //image holder
	private boolean crossable; //is there a collision
	private boolean pickable; 
	private int objectSize;
	 
	public GameObject() {
		setCrossable(true);
	}
	
	public void behavior() {	
		// each object will override this method 
	}
	
	public int getObjectSize() {
		return objectSize;
	}

	public void setObjectSize(int objectSize) {
		this.objectSize = objectSize;
	}
	
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	public BufferedImage getImage() {
		return image;
	}

	public boolean isCrossable() {
		return crossable; 
	}  
	
	public void setCrossable(boolean crossable) {
		this.crossable = crossable;
	}

	public boolean isPickable() {
		return pickable;
	}
	
	public void setPickable(boolean pickable) {
		this.pickable = pickable;
	}

}
