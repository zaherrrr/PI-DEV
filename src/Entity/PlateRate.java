package Entity;

import java.sql.Date;

public class PlateRate {
    private int id;
    private int id_plate ;
    private int id_user;
    private int stars;
    private Date daterate;

    public PlateRate() {
    }

    public PlateRate(int id, int id_plate, int id_user, int stars, Date daterate) {
        this.id = id;
        this.id_plate = id_plate;
        this.id_user = id_user;
        this.stars = stars;
        this.daterate = daterate;
    }

    public PlateRate(int id_plate, int id_user, int stars, Date daterate) {
        this.id_plate = id_plate;
        this.id_user = id_user;
        this.stars = stars;
        this.daterate = daterate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_plate() {
        return id_plate;
    }

    public void setId_plate(int id_plate) {
        this.id_plate = id_plate;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public Date getDaterate() {
        return daterate;
    }

    public void setDaterate(Date daterate) {
        this.daterate = daterate;
    }

    @Override
    public String toString() {
        return "PlateRate{" +
                "id=" + id +
                ", id_plate=" + id_plate +
                ", id_user=" + id_user +
                ", stars=" + stars +
                ", daterate=" + daterate +
                '}';
    }
}
