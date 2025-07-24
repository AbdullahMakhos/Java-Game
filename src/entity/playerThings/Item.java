package entity.playerThings;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import main.GamePanel;
import tiles.GameObject;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class Item {
    private int itemID;
    private int count;
    @JsonIgnore
    private GameObject gameObject; // Won't be serialized

    public Item() {} // For JSON deserialization

    public Item(GameObject gameObject, int count) {
        this.itemID = gameObject.getItemID();
        this.count = count;
        this.gameObject = gameObject;
    }
    
    @JsonIgnore
    public GameObject getItem() {
        if (gameObject == null) {
            gameObject = GamePanel.getInstance().getMapManager().getObjectTypesMatrix()[itemID];
        }
        return gameObject;
    }

    @JsonIgnore
    public void useItem() {
    	if (getItem() != null) {
    		getItem().behavior();
    	} else {
    		System.err.println("ERROR: Item's GameObject is null for ID: " + itemID);
        }
    }
    
    
    public int getItemID() { return itemID; }
    public int getCount() { return count; }
    public void setItemID(int id) { this.itemID = id; }
    public void setCount(int count) { this.count = count; }
    @JsonIgnore
    public GameObject getGameObject() { return gameObject; }
    
    
}
