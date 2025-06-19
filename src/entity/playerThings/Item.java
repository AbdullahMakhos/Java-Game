package entity.playerThings;

import objects.GameObject;

public class Item {
	
    private GameObject item;
    private int count;

    public Item(GameObject item, int count) {
        this.item = item;
        this.count = count;
    }

    public GameObject getItem() {
        return item;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

