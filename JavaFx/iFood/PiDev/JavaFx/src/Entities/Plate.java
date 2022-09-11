package Entities;

public class Plate {
    private int id;
    private String description;
    private String name;
    private int price = 0;
    private int id_category;
    private int quantity;

    private PlateCategory category ;
    public int getNumberOfSales() {
        return numberOfSales;
    }

    public void setNumberOfSales(int numberOfSales) {
        this.numberOfSales = numberOfSales;
    }

    public int getTotalOfSales() {
        return totalOfSales;
    }

    public void setTotalOfSales(int totalOfSales) {
        this.totalOfSales = totalOfSales;
    }

    private int numberOfSales ;
    private int totalOfSales;

    public PlateCategory getCategory() {
        return category;
    }

    public void setCategory(PlateCategory category) {
        this.category = category;
    }



    public Plate() {
    }

    public Plate(int id, String description, String name, int price, int id_category, int quantity) {
        this.id = id;
        this.description = description;
        this.name = name;
        this.price = price;
        this.id_category = id_category;
        this.quantity = quantity;
    }

    public Plate(String description, String name, int price, int id_category, int quantity) {

        this.description = description;
        this.name = name;
        this.price = price;
        this.id_category = id_category;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Plate{" +
                "Name=" + name +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", Category=" + category.getName() +
                ", quantity=" + quantity +
                ", Sold=" + numberOfSales +
                ", Revenue=" + totalOfSales +
                '}';
    }
}
