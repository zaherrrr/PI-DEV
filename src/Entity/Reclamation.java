package Entity;

import java.sql.Date;

public class Reclamation {
    private int id;
    private int id_client;
    private String description;
    private String respond;
    private int id_admin;
    private boolean status;
    private Date reclamationdate;

    public Reclamation() {
    }

    public Reclamation(int id, int id_client, String description, String respond, int id_admin, boolean status, Date date) {
        this.id = id;
        this.id_client = id_client;
        this.description = description;
        this.respond = respond;
        this.id_admin = id_admin;
        this.status = status;
        this.reclamationdate = date;
    }

    public Reclamation(int id_client, String description, String respond, int id_admin, boolean status, Date date) {
        this.id_client = id_client;
        this.description = description;
        this.respond = respond;
        this.id_admin = id_admin;
        this.status = status;
        this.reclamationdate = date;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRespond() {
        return respond;
    }

    public void setRespond(String respond) {
        this.respond = respond;
    }

    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getReclamationdate() {
        return reclamationdate;
    }

    public void setReclamationdate(Date reclamationdate) {
        this.reclamationdate = reclamationdate;
    }

    @Override
    public String toString() {
        return "Reclamation{" +
                "id=" + id +
                ", id_client=" + id_client +
                ", description='" + description + '\'' +
                ", respond='" + respond + '\'' +
                ", id_admin=" + id_admin +
                ", Responded=" + status +
                ", Date=" + reclamationdate +
                '}';
    }
}
