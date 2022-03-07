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
public class platecomment {
    private int id;
    private int id_plate;
    private int id_user;
    private String content ;
    private Date datecomment;

    public platecomment() {
    }

    public platecomment(int id_plate, int id_user, String content, Date datecomment) {
        this.id_plate = id_plate;
        this.id_user = id_user;
        this.content = content;
        this.datecomment = datecomment;
    }

    public platecomment(int id, int id_plate, int id_user, String content, Date datecomment) {
        this.id = id;
        this.id_plate = id_plate;
        this.id_user = id_user;
        this.content = content;
        this.datecomment = datecomment;
    }

    public int getId() {
        return id;
    }

    public int getId_plate() {
        return id_plate;
    }

    public int getId_user() {
        return id_user;
    }

    public String getContent() {
        return content;
    }

    public Date getDatecomment() {
        return datecomment;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_plate(int id_plate) {
        this.id_plate = id_plate;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDatecomment(Date datecomment) {
        this.datecomment = datecomment;
    }
    
    
    
}
