package Entities;

public class PackLine {
    private int id ;
    private int plate_id;
    private int pack_id;
    private int quantity;

    public PackLine() {
    }

    public PackLine(int id, int plate_id, int pack_id, int quantity) {
        this.id = id;
        this.plate_id = plate_id;
        this.pack_id = pack_id;
        this.quantity = quantity;
    }

    public PackLine(int plate_id, int pack_id, int quantity) {
        this.plate_id = plate_id;
        this.pack_id = pack_id;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlate_id() {
        return plate_id;
    }

    public void setPlate_id(int plate_id) {
        this.plate_id = plate_id;
    }

    public int getPack_id() {
        return pack_id;
    }

    public void setPack_id(int pack_id) {
        this.pack_id = pack_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "PackLine{" +
                "id=" + id +
                ", plate_id=" + plate_id +
                ", pack_id=" + pack_id +
                ", quantity=" + quantity +
                '}';
    }
}
