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
public class Event {
    private int id ;
    private int id_client;
    private String type ;
    private Date date ;
    private String locationx;
    private String locationy;
    private Double total; 

    public Event() {
    }

    public Event(int id_client, String type, Date date, String locationx, String locationy, Double total) {
        this.id_client = id_client;
        this.type = type;
        this.date = date;
        this.locationx = locationx;
        this.locationy = locationy;
        this.total = total;
    }

    public Event(int id, int id_client, String type, Date date, String locationx, String locationy, Double total) {
        this.id = id;
        this.id_client = id_client;
        this.type = type;
        this.date = date;
        this.locationx = locationx;
        this.locationy = locationy;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public int getId_client() {
        return id_client;
    }

    public String getType() {
        return type;
    }

    public Date getDate() {
        return date;
    }

    public String getLocationx() {
        return locationx;
    }

    public String getLocationy() {
        return locationy;
    }

    public Double getTotal() {
        return total;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setLocationx(String locationx) {
        this.locationx = locationx;
    }

    public void setLocationy(String locationy) {
        this.locationy = locationy;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
    
    
}
