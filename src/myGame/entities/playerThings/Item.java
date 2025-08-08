package myGame.entities.playerThings;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import myGame.Utilities.MapManager;
import myGame.Utilities.UIManager;
import myGame.core.GamePanel;
import myGame.tilesAndGameObjects.gameObjects.FishingRod;
import myGame.tilesAndGameObjects.gameObjects.GameObject;


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
            gameObject = MapManager.getInstance().getObjectTypesMatrix()[itemID];
        }
        return gameObject;
    }

    @JsonIgnore
    public void useItem() {
    	if (getItem() != null) {
    		if(gameObject instanceof FishingRod) {
    			if(MapManager.getInstance().isPlayerNearWater()) {
    				getItem().behavior();
    			}
    		}else {
    			getItem().behavior();    			
    		}
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
