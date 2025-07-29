package myGame.entities.playerThings;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import myGame.Utilities.UIManager;
import myGame.core.GamePanel;
import myGame.tiles.GameObject;


public class Inventory {
	
	@JsonProperty("inventory")
    private ArrayList<Item> inventory;
	@JsonIgnore
    private int sideInventoryMaxSize = 7;
	@JsonIgnore
	private int sideInventoryLastUsedIndex = -1; //by id
	@JsonIgnore
	private int selectedItemId = -1;

    // Needed for Jackson 
    public Inventory() {
    	inventory = new ArrayList<>();
    }
    
    @JsonIgnore
    public void addItem(GameObject item) {
        for (Item itemCount : inventory) {
            if (itemCount.getItem().equals(item)) {
                itemCount.setCount(itemCount.getCount() + 1);
                return;
            }
        }
        // Item not found, add a new entry
        inventory.add(new Item(item, 1));
        sideInventoryLastUsedIndex++;
        UIManager.getInstance().updateInventory();
    }
    @JsonIgnore
    public void addItem(GameObject item,int count) {
    	inventory.add(new Item(item, count));
        sideInventoryLastUsedIndex++;
        UIManager.getInstance().updateInventory();
    }
    
    @JsonIgnore
    public void removeItem(GameObject item) {
        if (item != null) {
            for (int i = 0; i < inventory.size(); i++) {
                Item itemCount = inventory.get(i);
                if (itemCount.getItem().equals(item)) {
                    int count = itemCount.getCount();
                    if (count == 1) {
                    	inventory.remove(i); // Remove the item if count is 1
                    } else {
                        itemCount.setCount(count - 1); // Decrease the count
                    }
                    return;
                }
            }
        }
        UIManager.getInstance().updateInventory();
    }
    @JsonIgnore
    public <T extends GameObject> GameObject getItem(T item) {
        for (Item itemCount : inventory) {
            if (item.getClass().isInstance(itemCount.getItem())) {
                return itemCount.getItem();
            }
        }
        return null;
    }
    
    @JsonGetter("inventory")
    public ArrayList<Item> getInventory() {
        return inventory;
    }
    @JsonSetter("inventory")
    public void setInventory(ArrayList<Item> inventory) throws IOException {
        this.inventory = inventory;
        sideInventoryLastUsedIndex = inventory.size()-1;
        UIManager.getInstance().updateInventory();
    }

	@JsonIgnore
    public int getItemCount(GameObject item) {
        for (Item itemCount : inventory) {
            if (item.equals(itemCount.getItem())) {
                return itemCount.getCount();
            }
        }
        return 0;
    }
    @JsonIgnore
	public int getSideInventoryMaxSize() {
		return sideInventoryMaxSize;
	}
    @JsonIgnore
	public void setSideInventoryMaxSize(int sideInventoryMaxSize) {
		this.sideInventoryMaxSize = sideInventoryMaxSize;
	}
    @JsonIgnore
	public int getSelectedItemId() {
		return selectedItemId;
	}
    @JsonIgnore
	public void setSelectedItemId(int selectedItemId) {
		this.selectedItemId = selectedItemId;
		UIManager.getInstance().updateInventory();
	}
    @JsonIgnore
	public Item getSelectedItem() {
		return (selectedItemId != -1) ?
		((selectedItemId >= 0 && selectedItemId <= sideInventoryLastUsedIndex ) ?
				inventory.get(selectedItemId) : null)
		: null; 
	}
    
    
}
