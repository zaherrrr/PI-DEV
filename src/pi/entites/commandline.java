/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.entites;

/**
 *
 * @author bechi_msajrea
 */
public class commandline {
    private int id;
    private int quantity ;
    private Double total;
    private int id_plate;
    private int id_client;
    private int id_command;

    public commandline() {
    }

    public commandline(int quantity, Double total, int id_plate, int id_client, int id_command) {
        this.quantity = quantity;
        this.total = total;
        this.id_plate = id_plate;
        this.id_client = id_client;
        this.id_command = id_command;
    }

    public commandline(int id, int quantity, Double total, int id_plate, int id_client, int id_command) {
        this.id = id;
        this.quantity = quantity;
        this.total = total;
        this.id_plate = id_plate;
        this.id_client = id_client;
        this.id_command = id_command;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public Double getTotal() {
        return total;
    }

    public int getId_plate() {
        return id_plate;
    }

    public int getId_client() {
        return id_client;
    }

    public int getId_command() {
        return id_command;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public void setId_plate(int id_plate) {
        this.id_plate = id_plate;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public void setId_command(int id_command) {
        this.id_command = id_command;
    }
    
    
}
