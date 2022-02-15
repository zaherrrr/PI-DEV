package Entity;

import java.sql.Date;

public class Command {
    private int id;
    private int id_client;
    private Double total;
    private Date datecommand;

    public Command(){

    }
    public Command(int id, int id_client, Double total, Date datecommand) {
        this.id = id;
        this.id_client = id_client;
        this.total = total;
        this.datecommand = datecommand;
    }

    public Command(int id_client, Double total, Date datecommand) {
        this.id_client = id_client;
        this.total = total;
        this.datecommand = datecommand;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Date getDatecommand() {
        return datecommand;
    }

    public void setDatecommand(Date datecommand) {
        this.datecommand = datecommand;
    }

    @Override
    public String toString() {
        return "Command{" +
                "id=" + id +
                ", id_client=" + id_client +
                ", total=" + total +
                ", datecommand=" + datecommand +
                '}';
    }
}
