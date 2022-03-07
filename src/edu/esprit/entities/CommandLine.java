/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;
import edu.esprit.services.PlateCRUD;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;



/**
 *
 * @author user
 */
public class CommandLine {
    private int idLC;
    private int quantityPLT;

    private double totalLC;
    private int id_p;
        private int id_client;
    private int id_command;
    private Plate plate;
    PlateCRUD pc = new PlateCRUD();
    public Plate getPlate() {
        return plate;
    }

    public void setPlate(int id_plate) {
        this.plate = pc.plateInfo(id_plate);
    }

    public CommandLine() {
    }

    public CommandLine(int quantityPLT, int id_p, int id_client, int id_command) {
            String timeStamp = new SimpleDateFormat("ssMMdd").format(Calendar.getInstance().getTime());
     String timeStamp1 = new SimpleDateFormat("mmss").format(Calendar.getInstance().getTime());
   int id= Integer.parseInt(timeStamp)+Integer.parseInt(timeStamp1);
       
        this.idLC = id;
        this.quantityPLT = quantityPLT;
        this.id_p = id_p;
        this.id_client = id_client;
        this.id_command = id_command;
    }
    

    public CommandLine( int quantityPLT) {
       String timeStamp = new SimpleDateFormat("ssMMdd").format(Calendar.getInstance().getTime());
     String timeStamp1 = new SimpleDateFormat("mmss").format(Calendar.getInstance().getTime());
   int id= Integer.parseInt(timeStamp)+Integer.parseInt(timeStamp1);
       
        this.idLC = id;
        this.quantityPLT = quantityPLT;
    }

    public CommandLine(int quantityPLT, double total) {
            String timeStamp = new SimpleDateFormat("ssMMdd").format(Calendar.getInstance().getTime());
     String timeStamp1 = new SimpleDateFormat("mmss").format(Calendar.getInstance().getTime());
   int id= Integer.parseInt(timeStamp)+Integer.parseInt(timeStamp1);
       
        this.idLC = id;
        this.quantityPLT=quantityPLT;
       
        this.totalLC = total;
    }

    public CommandLine(int quantityPLT, double totalLC, int id_p, int id_client, int id_command) {
        this.quantityPLT = quantityPLT;
        this.totalLC = totalLC;
        this.id_p = id_p;
        this.id_client = id_client;
        this.id_command = id_command;
    }
    



    public int getId_p() {
        return id_p;
    }

    public void setId_p(int id_p) {
        this.id_p = id_p;
    }

    public int getIdLC() {
        return idLC;
    }

    public void setIdLC(int id) {
        this.idLC = id;
    }

    public int getQuantityPLT() {
        return quantityPLT;
    }

    public void setQuantityPLT(int nameCom) {
        this.quantityPLT = nameCom;
    }

    public double getTotalLC() {
        return totalLC;
    }

    public void setTotalLC(double total) {
        this.totalLC = total;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getId_command() {
        return id_command;
    }

    public void setId_command(int id_command) {
        this.id_command = id_command;
    }



    @Override
    public String toString() {
        return "LineCommand{" + "idLC=" + idLC + ", quantityPLT=" + quantityPLT + ", totalLC=" + totalLC + ", id_p=" + id_p + ", id_client=" + id_client + ", id_command=" + id_command + '}';
    }

   
   

    
    
    
}
