package models;

public class Item {
    private String name;
    private int stock;
    private int capacity;
    private double cost;
    private int id;

    public Item(String name, int id, double cost) {
        this.name = name;
        this.cost = cost;
        this.id = id;
    }

    public Item(String name, int stock, int capacity, int id) {
        this.name = name;
        this.stock = stock;
        this.capacity = capacity;
        this.id = id;
    }
    
    

    public Item(String name, int stock, int capacity, double cost, int id) {
		super();
		this.name = name;
		this.stock = stock;
		this.capacity = capacity;
		this.cost = cost;
		this.id = id;
	}

	public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
    }

    public int getCapacity() {
        return capacity;
    }

    public double getCost() {
        return cost;
    }

    // mutators excluded since not required for requested functionality.
}
