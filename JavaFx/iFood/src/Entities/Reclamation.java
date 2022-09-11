package Entities;

import java.sql.Date;

public class Reclamation {
    private int id;
    private int id_client;
    private String description;

    private String sujet;

    public Reclamation(int id_client, String description, String sujet) {
        this.id_client = id_client;
        this.description = description;
        this.sujet = sujet;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    private String respond;

    public int getStatus() {
        return status;
    }

    private int status;

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    private Users user;

    public Reclamation() {
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



    public int isStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }



    @Override
    public String toString() {
        return "Reclamation{" +
                "id=" + id +
                ", id_client=" + id_client +
                ", description='" + description + '\'' +
                ", sujet='" + sujet + '\'' +

                ", respond='" + respond + '\'' +
                ", Responded=" + status +
                '}';
    }
}
