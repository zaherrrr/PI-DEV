package Entities;

public class
CommandLine {
    private int id;
    private int quantity = 0;
    private int id_plate;
    private int id_command;

    public int getTotal() {
        System.out.println(this.id+" has "+this.quantity+" ");
        return (this.plate.getPrice()* this.quantity);
    }

    public void setTotal(int total) {
        this.total = total;
    }

    private int total ;
    public Plate getPlate() {
        return plate;
    }

    public void setPlate(Plate plate) {
        this.plate = plate;
    }

    private Plate plate;
    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    private Command command;


    public CommandLine() {
    }

    public CommandLine(int id, int quantity, int id_plate, int id_command) {
        this.id = id;
        this.quantity = quantity;
        this.id_plate = id_plate;
        this.id_command = id_command;
    }

    public CommandLine(int quantity, int id_plate, int id_command) {
        this.quantity = quantity;
        this.id_plate = id_plate;
        this.id_command = id_command;
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

    public int getId_plate() {
        return id_plate;
    }

    public void setId_plate(int id_plate) {
        this.id_plate = id_plate;
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
               // ", plate=" + plate.getName() +" Price: "+ plate.getPrice()+
                ", id_command=" + id_command +
                '}';
    }
}
