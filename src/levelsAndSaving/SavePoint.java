package levelsAndSaving;

import entity.playerThings.Inventory;
import entity.playerThings.Status;

import com.fasterxml.jackson.annotation.JsonIgnore;

//POJO : an instance to load an info from j s o n that will be passed to other objects  

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL) // Only include non-null fields
public class SavePoint {
    private int x;
    private int y;
    private int levelId;
    private Inventory inventory;
    private int[][] objectMatrix;
    private Status status;
    
	public SavePoint(int x, int y,Status status,Inventory inventory, int levelId , int[][] objectMatrix) {
        this.x = x;
        this.y = y;
        this.setStatus(status);
        this.inventory = inventory;
        this.levelId = levelId;
        this.objectMatrix = objectMatrix;
    }
    
    public SavePoint() {
        x = -1;
        y = -1;
    }
    @JsonIgnore
    public boolean isModified() {
        return x != -1 && y != -1;
    }
    
    // Getters and setters
    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
    
    public int getY() { return y; }
    public void setY(int y) { this.y = y; }
    
    public int getLevelId() { return levelId; }
    public void setLevelId(int levelId) { this.levelId = levelId; }
    
    public Inventory getInventory() { return inventory; }
    public void setInventory(Inventory inventory) { this.inventory = inventory; }
    
    public int[][] getObjectMatrix() { return objectMatrix; }
	public void setObjectMatrix(int[][] objectMatrix) { this.objectMatrix = objectMatrix; }

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}