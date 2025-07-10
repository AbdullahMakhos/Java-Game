package levelsAndSaving;

import entity.playerThings.Inventory;

import com.fasterxml.jackson.annotation.JsonIgnore;

//POJO : an instance to load an info from j s o n that will be passed to other objects  

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL) // Only include non-null fields
public class SavePoint {
    private int x;
    private int y;
    private int levelId;
    private Inventory inventory;
    
    public SavePoint(int x, int y, int levelId) {
        this.x = x;
        this.y = y;
        this.levelId = levelId;
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
    
}