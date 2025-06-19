package entity.playerThings;

import java.util.ArrayList;

import objects.GameObject;

public class Inventory {
	

    private ArrayList<Item> bag;
    private int sideInventoryMaxSize = 7;
    private int sideInventoryLastUsedIndex = -1; //by id
    private int fullInventoryMaxSize = 30;
    private int selectedItemId = -1;

    public Inventory() {
        bag = new ArrayList<>();
    }

    public void addItem(GameObject item) {
        for (Item itemCount : bag) {
            if (itemCount.getItem().equals(item)) {
                itemCount.setCount(itemCount.getCount() + 1);
                return;
            }
        }
        // Item not found, add a new entry
        bag.add(new Item(item, 1));
        sideInventoryLastUsedIndex++;
        
    }

    public void removeItem(GameObject item) {
        if (item != null) {
            for (int i = 0; i < bag.size(); i++) {
                Item itemCount = bag.get(i);
                if (itemCount.getItem().equals(item)) {
                    int count = itemCount.getCount();
                    if (count == 1) {
                        bag.remove(i); // Remove the item if count is 1
                    } else {
                        itemCount.setCount(count - 1); // Decrease the count
                    }
                    return;
                }
            }
        }
    }

    public <T extends GameObject> GameObject getItem(T item) {
        for (Item itemCount : bag) {
            if (item.getClass().isInstance(itemCount.getItem())) {
                return itemCount.getItem();
            }
        }
        return null;
    }

    public ArrayList<Item> getBag() {
        return bag;
    }

    public int getItemCount(GameObject item) {
        for (Item itemCount : bag) {
            if (item.equals(itemCount.getItem())) {
                return itemCount.getCount();
            }
        }
        return 0;
    }


	public int getFullInventoryMaxSize() {
		return fullInventoryMaxSize;
	}

	public void setFullInventoryMaxSize(int fullInventoryMaxSize) {
		this.fullInventoryMaxSize = fullInventoryMaxSize;
	}

	public int getSideInventoryMaxSize() {
		return sideInventoryMaxSize;
	}

	public void setSideInventoryMaxSize(int sideInventoryMaxSize) {
		this.sideInventoryMaxSize = sideInventoryMaxSize;
	}

	public int getSelectedItemId() {
		return selectedItemId;
	}

	public void setSelectedItemId(int selectedItemId) {
		this.selectedItemId = selectedItemId;
	}

	public Item getSelectedItem() {
		return (selectedItemId != -1) ?
		((selectedItemId >= 0 && selectedItemId <= sideInventoryLastUsedIndex ) ?
				bag.get(selectedItemId) : null)
		: null; 
	}
}
