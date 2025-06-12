package main;

import java.util.HashMap;

import objects.GameObject;

public class Inventory {
	
	private HashMap<GameObject,Integer> bag;
	
	public Inventory() {
		bag = new HashMap<>();
	}
	
	public void addItem(GameObject item) {
		bag.put(item,bag.getOrDefault(item,0)+1);
	}
	
	public void removeItem(GameObject item) {
		if(item != null) {
			int count = bag.get(item);
			if(count == 1) {
				bag.remove(item);
			}else {
				bag.replace(item, count, count - 1);
			}	
		}
	}
	
	public <T extends GameObject> GameObject getItem(T item) {
	    
	    for (GameObject o : bag.keySet()) {
	        if (item.getClass().isInstance(o)) {
	            return o;
	        }
	    }
	    
	    return null;
	}
	
	public HashMap<GameObject, Integer> getBag() {
		return bag;
	}

	public int getItemCount(GameObject item) {
		
	    for (GameObject o : bag.keySet()) {
	        if (item.getClass().isInstance(o)) {
	            return bag.get(item);
	        }
	    }
	    
	    return 0;
	}
}
