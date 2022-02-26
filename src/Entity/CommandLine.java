package Entity;

public class CommandLine {
    private int id;
    private int quantity;
    private double total;
    private int id_plate;
    private int id_client;
    private int id_command;

    public CommandLine() {
    }

    public CommandLine(int id, int quantity, int total, int id_plate, int id_client, int id_command) {
        this.id = id;
        this.quantity = quantity;
        this.total = total;
        this.id_plate = id_plate;
        this.id_client = id_client;
        this.id_command = id_command;
    }

    public CommandLine(int quantity, int id_plate, int id_client, int id_command) {
        this.quantity = quantity;
        this.id_plate = id_plate;
        this.id_client = id_client;
        this.id_command = id_command;
    }

    public CommandLine(int quantity, int id_plate, int id_client) {
        this.quantity = quantity;
        this.id_plate = id_plate;
        this.id_client = id_client;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getId_plate() {
        return id_plate;
    }

    public void setId_plate(int id_plate) {
        this.id_plate = id_plate;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getId_command() {
        return id_command;
    }

    public void setId_command(int id_command) {
        this.id_command = id_command;
    }

    @Override
    public String toString() {
        return "CommandLine{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", total=" + total +
                ", id_plate=" + id_plate +
                ", id_client=" + id_client +
                ", id_command=" + id_command +
                '}';
    }
}
