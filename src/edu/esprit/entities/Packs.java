/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

/**
 *
 * @author User
 */
public class Packs {
    private int id; 
    private String name; 
    private String description ; 
    private double price; 
    private int id_owner;

    public Packs(int id, String name, String description, double price, int id_owner) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.id_owner = id_owner;
    }

    public Packs(int id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Packs(String name, String description, double price, int id_owner) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.id_owner = id_owner;
    }
    

    public Packs() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId_owner() {
        return id_owner;
    }

    public void setId_owner(int id_owner) {
        this.id_owner = id_owner;
    }

    public double getTotal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    

}
