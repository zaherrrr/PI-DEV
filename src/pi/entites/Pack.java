/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.entites;

/**
 *
 * @author bechi_msajrea
 */
public class Pack {

    
    private int id ; 
    private int id_chef ;
    private int id_events ; 
    private String Description ; 
    private Double price ; 
    
    
    
    public Pack() {
    }
    
    

    public Pack(int id, int id_chef, int id_events, String Description, Double price) {
        this.id = id;
        this.id_chef = id_chef;
        this.id_events = id_events;
        this.Description = Description;
        this.price = price;
    }

    public Pack(int id_chef, int id_events, String Description, Double price) {
        this.id_chef = id_chef;
        this.id_events = id_events;
        this.Description = Description;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public int getId_chef() {
        return id_chef;
    }

    public int getId_events() {
        return id_events;
    }

    public String getDescription() {
        return Description;
    }

    public Double getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_chef(int id_chef) {
        this.id_chef = id_chef;
    }

    public void setId_events(int id_events) {
        this.id_events = id_events;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    
    
}
