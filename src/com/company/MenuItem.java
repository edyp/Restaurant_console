package com.company;

public class MenuItem {
    private String itemName;
    private String itemDescription;
    private Double itemPrice;

    public MenuItem(String itemName, String itemDescription, Double itemPrice) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
    }

    public String getItemName() {
        return this.itemName;
    }

    public String getItemDescription() {
        return this.itemDescription;
    }

    public Double getItemPrice() {
        return this.itemPrice;
    }
}
