package models;

import java.util.ArrayList;
import java.util.List;

public class Distributor {
    private String name;
    private List<Item> items;

    public Distributor(String name) {
        this.name = name;

        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public double getCheapestCost(String itemName) {
        for (Item item : items) {
            if (item.getName().equals(itemName)) {
                return item.getCost();
            }
        }
        // doesn't exist in vendor
        return Double.MAX_VALUE;
    }
    
    public List<Item> getItems() {
		return items;
	}
}
