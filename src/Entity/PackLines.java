package Entity;

import java.sql.Timestamp;

public class PackLines {
    private int id;
    private int id_plate;
    private int id_pack;
    private Double quantity;
    private Double total;
    private Timestamp datepack;

    public PackLines() {
    }

    public PackLines(int id, int id_plate, int id_pack, Double quantity, Double total, Timestamp datepack) {
        this.id = id;
        this.id_plate = id_plate;
        this.id_pack = id_pack;
        this.quantity = quantity;
        this.total = total;
        this.datepack = datepack;
    }

    public PackLines(int id_plate, int id_pack, Double quantity) {
        this.id_plate = id_plate;
        this.id_pack = id_pack;
        this.quantity = quantity;
    }

    public PackLines(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_plate() {
        return id_plate;
    }

    public void setId_plate(int id_plate) {
        this.id_plate = id_plate;
    }

    public int getId_pack() {
        return id_pack;
    }

    public void setId_pack(int id_pack) {
        this.id_pack = id_pack;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Timestamp getDatepack() {
        return datepack;
    }

    public void setDatepack(Timestamp datepack) {
        this.datepack = datepack;
    }

    @Override
    public String toString() {
        return "PackLine{" +
                "id=" + id +
                ", id_plate=" + id_plate +
                ", id_pack=" + id_pack +
                ", quantity=" + quantity +
                ", total=" + total +
                ", datepack=" + datepack +
                '}';
    }
}
