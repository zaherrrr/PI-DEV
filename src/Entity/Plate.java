package Entity;

public class Plate {
    private int id;
    private String description;
    private String name;
    private String image;
    private Double price;
    private int id_category;
    private int id_chef;
    private int id_pack;

    public Plate() {
    }

    public Plate(int id, int id_category, int id_chef, String description, String name, String image, Double price, int id_pack) {
        this.id = id;
        this.id_category = id_category;
        this.id_chef = id_chef;
        this.description = description;
        this.name = name;
        this.image = image;
        this.price = price;
        this.id_pack = id_pack;
    }

    public Plate(int id, int id_category, int id_chef, String description, String name, String image, Double price) {
        this.id = id;
        this.id_category = id_category;
        this.id_chef = id_chef;
        this.description = description;
        this.name = name;
        this.image = image;
        this.price = price;
    }

    public Plate(int id_category, int id_chef, String description, String name, String image, Double price) {
        this.id_category = id_category;
        this.id_chef = id_chef;
        this.description = description;
        this.name = name;
        this.image = image;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }

    public int getId_chef() {
        return id_chef;
    }

    public void setId_chef(int id_chef) {
        this.id_chef = id_chef;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getId_pack() {
        return id_pack;
    }

    public void setId_pack(int id_pack) {
        this.id_pack = id_pack;
    }


    @Override
    public String toString() {
        return "Plate{" +
                "ID=" + id +
                ", Category=" + id_category +
                ", Chef=" + id_chef +
                ", \n Description ='" + description + '\'' +
                ", \n Plate name='" + name + '\'' +
                ", Current Image='" + image + '\'' +
                ", Price TND =" + price +
                ", Pack =" + id_pack +
                '}';
    }
}
