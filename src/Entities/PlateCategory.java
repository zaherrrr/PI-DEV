package Entities;

public class PlateCategory {
    private int id;
    private String name;
    private String description;

    public PlateCategory(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public int getNumberofplates() {
        return numberofplates;
    }

    public void setNumberofplates(int numberofplates) {
        this.numberofplates = numberofplates;
    }

    private int numberofplates;

    public PlateCategory() {
    }

    public PlateCategory(int id, String name, String description, int numberofplates) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.numberofplates = numberofplates;
    }

    public PlateCategory(String name, String description, int numberofplates) {
        this.name = name;
        this.description = description;
        this.numberofplates = numberofplates;

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
                ", Nbr of plates : ='" + numberofplates + '\'' +
                '}';
    }
}
