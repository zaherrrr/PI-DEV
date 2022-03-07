/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.entites;

import java.sql.Date;

/**
 *
 * @author bechi_msajrea
 */
public class command {
private int id;
private Date datecommand;
private Double total;
private int id_client;

    public command() {
    }

    public command(Date datecommand, Double total, int id_client) {
        this.datecommand = datecommand;
        this.total = total;
        this.id_client = id_client;
    }

    public command(int id, Date datecommand, Double total, int id_client) {
        this.id = id;
        this.datecommand = datecommand;
        this.total = total;
        this.id_client = id_client;
    }

    public int getId() {
        return id;
    }

    public Date getDatecommand() {
        return datecommand;
    }

    public Double getTotal() {
        return total;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDatecommand(Date datecommand) {
        this.datecommand = datecommand;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }


}
