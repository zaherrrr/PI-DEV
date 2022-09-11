package Entities;

import java.sql.Date;
import java.util.List;

public class Command {
    private int id;
    private int id_client;
    private Date datecommand;
    private int status;

    public  int getNumber() {
        return number;
    }

    public  void setNumber(int number) {
        this.number = number;
    }

    private  int number;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    private int total = 0;
    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    private Users user;
    private List<CommandLine> commandLines ;
    public Command() {

    }


    public Command(int id, int id_client, Date datecommand, int status) {
        this.id = id;
        this.id_client = id_client;
        this.datecommand = datecommand;
        this.status = status;
    }

    public Command(int id_client, Date datecommand, int status) {
        this.id_client = id_client;
        this.datecommand = datecommand;
        this.status = status;
    }

    public Command(int id, int id_client) {
        this.id = id;
        this.id_client = id_client;
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

    public Date getDatecommand() {
        return datecommand;
    }

    public void setDatecommand(Date datecommand) {
        this.datecommand = datecommand;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<CommandLine> getCommandLines() {
        return commandLines;
    }

    public void setCommandLines(List<CommandLine> commandLines) {
        this.commandLines = commandLines;
    }

    @Override
    public String toString() {
        return "Command{" +
                "id=" + id +
                ", id_client=" + getId_client() +
                ", datecommand=" + datecommand +
                ", status=" + status +
                ",Total = "+total+
                '}';
    }
}
