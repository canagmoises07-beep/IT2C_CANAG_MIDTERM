package com.canteen.model;

public class Item {
    private int id;
    private String itemName;
    private String category;
    private double price;
    private int stock;

    // Constructor for fetching from DB
    public Item(int id, String itemName, String category, double price, int stock) {
        this.id = id;
        this.itemName = itemName;
        this.category = category;
        this.price = price;
        this.stock = stock;
    }

    // Constructor for inserting new record (ID auto-generated)
    public Item(String itemName, String category, double price, int stock) {
        this.itemName = itemName;
        this.category = category;
        this.price = price;
        this.stock = stock;
    }

    // Getters
    public int getId() { return id; }
    public String getItemName() { return itemName; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
}