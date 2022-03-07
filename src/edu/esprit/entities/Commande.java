/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 *
 * @author user
 */
public class Commande {
      public Commande(int id_commande) {
        this.id = id_commande;
    }

    private int id;
    private int id_user;
    private boolean etat_cmd;
    private java.sql.Date date_cmd;
    
    public Commande() {
    }
    
    public Commande(int id, int id_user, boolean etat_cmd, java.sql.Date date_cmd) {
        this.id = id;
        this.id_user = id_user;
        this.etat_cmd= etat_cmd;
        this.date_cmd=date_cmd;
    }
    
    public Commande(int id_user,boolean etat_cmd,java.sql.Date date_cmd) {
        this.id_user = id_user; 
        this.etat_cmd= etat_cmd;
        this.date_cmd=date_cmd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
    
    public boolean isEtat_cmd() {
        return etat_cmd;
    }

    public void setEtat_cmd(boolean etat_cmd) {
        this.etat_cmd = etat_cmd;
    }

    public java.sql.Date getDate_cmd() {
        return date_cmd;
    }

    public void setDate_cmd(java.sql.Date date_cmd) {
        this.date_cmd = date_cmd;
    }
    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", id_user=" + id_user + ", Validé=" + etat_cmd + ", date_cmd=" + date_cmd + '}';
    }

}
