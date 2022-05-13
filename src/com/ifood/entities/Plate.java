package com.ifood.entities;

public class Plate {

    private int id;
    private Category category;
    private String name;
    private String description;
    private int quantity;
    private int price;

    public Plate() {
    }

    public Plate(int id, Category category, String name, String description, int quantity, int price) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    public Plate(Category category, String name, String description, int quantity, int price) {
        this.category = category;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "Plate : " +
                "id=" + id
                + ", Category=" + category
                + ", Name=" + name
                + ", Description=" + description
                + ", Quantity=" + quantity
                + ", Price=" + price
                ;
    }


}