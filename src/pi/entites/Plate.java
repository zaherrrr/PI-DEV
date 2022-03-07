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
public class Plate {
    private int id ;
    private String name;
    private String description;
    private String image ; 
    private Double price;

    PlateCategory pc = new PlateCategory ();
    
   
    public Plate() {
    }

    public Plate(String name, String description, String image, Double price) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
    }

    public Plate(int id, String name, String description, String image, Double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public Double getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    
     @Override
    public String toString() {
        return "PlateCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", Nbr of plates : ='" + pc.getNumberofplates() + '\'' +
                '}';
    
}}
