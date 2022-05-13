package com.ifood.entities;

import com.ifood.utils.DateUtils;
import com.ifood.utils.Statics;

import java.util.Date;

public class Commande implements Comparable<Commande> {

    private int id;
    private Utilisateur utilisateur;
    private Date dateCommand;
    private int status;

    public Commande() {
    }

    public Commande(int id, Utilisateur utilisateur, Date dateCommand, int status) {
        this.id = id;
        this.utilisateur = utilisateur;
        this.dateCommand = dateCommand;
        this.status = status;
    }

    public Commande(Utilisateur utilisateur, Date dateCommand, int status) {
        this.utilisateur = utilisateur;
        this.dateCommand = dateCommand;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Date getDateCommand() {
        return dateCommand;
    }

    public void setDateCommand(Date dateCommand) {
        this.dateCommand = dateCommand;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    @Override
    public int compareTo(Commande command) {
        switch (Statics.compareVar) {
            case "Utilisateur":
                return this.getUtilisateur().getEmail().compareTo(command.getUtilisateur().getEmail());
            case "DateCommand":
                DateUtils.compareDates(this.getDateCommand(), command.getDateCommand());
            case "Status":
                return Integer.compare(this.getStatus(), command.getStatus());

            default:
                return 0;
        }
    }

}