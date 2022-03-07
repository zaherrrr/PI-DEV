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
public class platerate {
    private int id;
    private int id_plate;
    private int stars;
    private Date daterate;
    private int id_user;

    public platerate() {
    }

    public platerate(int id_plate, int stars, Date daterate, int id_user) {
        this.id_plate = id_plate;
        this.stars = stars;
        this.daterate = daterate;
        this.id_user = id_user;
    }

    public platerate(int id, int id_plate, int stars, Date daterate, int id_user) {
        this.id = id;
        this.id_plate = id_plate;
        this.stars = stars;
        this.daterate = daterate;
        this.id_user = id_user;
    }

    public int getId() {
        return id;
    }

    public int getId_plate() {
        return id_plate;
    }

    public int getStars() {
        return stars;
    }

    public Date getDaterate() {
        return daterate;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_plate(int id_plate) {
        this.id_plate = id_plate;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public void setDaterate(Date daterate) {
        this.daterate = daterate;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
    
}
