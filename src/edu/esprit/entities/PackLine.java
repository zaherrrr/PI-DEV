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
public class PackLine {
    private int id;
    private int id_pack; 
    private int id_plate; 
    private int quantity; 
    private Double total; 

    public PackLine(int id, int id_pack, int id_plate, int quantity, Double total) {
        this.id = id;
        this.id_pack = id_pack;
        this.id_plate = id_plate;
        this.quantity = quantity;
        this.total = total;
    }

    public PackLine(int id_plate, int quantity, Double total) {
        this.id_plate = id_plate;
        this.quantity = quantity;
        this.total = total;
    }
    
    

    public PackLine(int id_pack, int id_plate, int quantity, Double total) {
        this.id_pack = id_pack;
        this.id_plate = id_plate;
        this.quantity = quantity;
        this.total = total;
    }

    
    
    public PackLine(int id_pack, int id_plate, int quantity) {
        this.id_pack = id_pack;
        this.id_plate = id_plate;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_pack() {
        return id_pack;
    }

    public void setId_pack(int id_pack) {
        this.id_pack = id_pack;
    }

    public int getId_plate() {
        return id_plate;
    }

    public void setId_plate(int id_plate) {
        this.id_plate = id_plate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "PackLine{" + "id=" + id + ", id_pack=" + id_pack + ", id_plate=" + id_plate + ", quantity=" + quantity + ", total=" + total + '}';
    }

    
    
    
    
}