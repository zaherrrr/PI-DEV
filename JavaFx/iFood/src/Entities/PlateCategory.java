package Entities;

import java.util.List;

public class PlateCategory {
    private int id;
    private String name;
    private String description;

    public List<Plate> getPlates() {
        return plates;
    }

    public void setPlates(List<Plate> plates) {
        this.plates = plates;
    }

    private List<Plate> plates ;



    public PlateCategory() {
    }



    public PlateCategory(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public PlateCategory(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    @Override
    public String toString() {
        return "PlateCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
