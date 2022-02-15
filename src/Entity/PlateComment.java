package Entity;

import java.sql.Date;

public class PlateComment {
    private int id;
    private int id_plate;
    private int id_user;
    private String content;
    private Date datecomment;

    public PlateComment() {
    }

    public PlateComment(int id, int id_plate, int id_user, String content, Date datecomment) {
        this.id = id;
        this.id_plate = id_plate;
        this.id_user = id_user;
        this.content = content;
        this.datecomment = datecomment;
    }

    public PlateComment(int id_plate, int id_user, String content, Date datecomment) {
        this.id_plate = id_plate;
        this.id_user = id_user;
        this.content = content;
        this.datecomment = datecomment;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDatecomment() {
        return datecomment;
    }

    public void setDatecomment(Date datecomment) {
        this.datecomment = datecomment;
    }

    @Override
    public String toString() {
        return "PlateComment{" +
                "id=" + id +
                ", id_plate=" + id_plate +
                ", id_user=" + id_user +
                ", content='" + content + '\'' +
                ", datecomment=" + datecomment +
                '}';
    }
}
