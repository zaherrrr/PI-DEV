/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author User
 */
public class Evenements {
    private int id; 
    private Date dateevent; 
    private String name; 
    private int id_client; 
    private int id_pack; 
  

    public Evenements(int id, Date dateevent, String name, int id_client, int id_pack) {
        this.id = id;
        this.dateevent = dateevent;
        this.name = name;
        this.id_client = id_client;
        this.id_pack = id_pack;
       

    }

    public Evenements(Date dateevent, String name, int id_client, int id_pack) {
        this.dateevent = dateevent;
        this.name = name;
        this.id_client = id_client;
        this.id_pack = id_pack;
            }

    public Evenements() {
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateevent() {
        return dateevent;
    }

    public void setDateevent(Date dateevent) {
        this.dateevent = dateevent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getId_pack() {
        return id_pack;
    }

    public void setId_pack(int id_pack) {
        this.id_pack = id_pack;
    }

   
 

    @Override
    public String toString() {
        return "Evenements{" + "id=" + id + ", dateevent=" + dateevent + ", name=" + name + ", id_client=" + id_client + ", id_pack=" + id_pack + ", id_chef=" + '}';
    }
    

  
}