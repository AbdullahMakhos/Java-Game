package myGame.tilesAndGameObjects.gameObjects;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import myGame.core.GamePanel;



//objects like box fire...etc not monsters , monsters will be entities 

public class GameObject {
	protected GamePanel gp;
	protected String imagePath;
	protected BufferedImage image; //image holder
	private boolean crossable; //is there a collision
	private boolean pickable; 
	private int objectSize;
	protected int itemID;
	
	
	public GameObject() throws IOException{
		this.gp = GamePanel.getInstance();
		itemID = 0;
		setObjectSize(gp.getTileSize());
		setCrossable(true);
		setCrossable(true);
	}

	public void behavior() {	
		// each object will override this method 
		System.out.println("NO BEHAVOIR YET");
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
	
	protected void loadImage() throws IOException {
		setImage(ImageIO.read(getClass().
	    	    getResourceAsStream(imagePath)));
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

	
	public boolean equals(GameObject o) {
		
		return this.getClass().equals(o.getClass());
		
	}

	public int getItemID() {
		return itemID;
	}

	public static GameObject createFromID(int itemId) throws IOException {
	    switch (itemId) {
	        case 0: return new GameObject();
	        case 1: return new Door(); 
	        case 2: return new Fish();
	        case 3: return new SnowPearl();
	        case 4: return new FishingRod();
	        case 5: return new SnowMan();
	        case 6: return Tree.createFromID(1);
	        case 7: return Tree.createFromID(2);
	        case 8: return new SticksPack();
	        case 9: return new IceEffect();
	        default: throw new IllegalArgumentException("Unexpected value: " + itemId);
	    }
	}
}
