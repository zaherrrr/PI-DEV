/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author user
 */
public class Plate {
    private int id_p;
    private String desc_p;
     private  String name_c;
   private  String image_c;
    private double price_c;
    private int qnt_p;
      private int id_category;
    private int id_chef;
    private int id_pack;

    public Plate() {
    }

    public Plate(double price_c, int qnt_p) {
        this.price_c = price_c;
        this.qnt_p = qnt_p;
    }
    

    public Plate(String desc_p, String name_c, String image_c, double price_c, int id_category, int id_chef, int id_pack) {
                String timeStamp = new SimpleDateFormat("ssMM").format(Calendar.getInstance().getTime());
     String timeStamp1 = new SimpleDateFormat("mmddss").format(Calendar.getInstance().getTime());
   int id= Integer.parseInt(timeStamp)+Integer.parseInt(timeStamp1);
       
        this.id_p = id;
        this.desc_p = desc_p;
        
        this.name_c = name_c;
        this.image_c = image_c;
        this.price_c = price_c;
        this.id_category = id_category;
        this.id_chef = id_chef;
        this.id_pack = id_pack;
    }

    public Plate(int id_p, String desc_p, String name_c, String image_c, double price_c,int qnt_t, int id_category, int id_chef, int id_pack) {
        this.id_p = id_p;
        this.desc_p = desc_p;
        this.name_c = name_c;
        this.image_c = image_c;
        this.price_c = price_c;
        this.qnt_p=qnt_p;
        this.id_category = id_category;
        this.id_chef = id_chef;
        this.id_pack = id_pack;
    }

    public Plate(String desc_p, String name_c, String image_c, double price_c) {
        this.desc_p = desc_p;
        this.name_c = name_c;
        this.image_c = image_c;
        this.price_c = price_c;
    }
    

    public int getId_p() {
        return id_p;
    }

    public void setId_p(int id_p) {
        this.id_p = id_p;
    }

    public String getDesc_p() {
        return desc_p;
    }

    public void setDesc_p(String desc_p) {
        this.desc_p = desc_p;
    }

    public String getName_c() {
        return name_c;
    }

    public void setName_c(String name_c) {
        this.name_c = name_c;
    }

    public String getImage_c() {
        return image_c;
    }

    public void setImage_c(String image_c) {
        this.image_c = image_c;
    }

    public int getQnt_p() {
        return qnt_p;
    }

    public void setQnt_p(int qnt_p) {
        this.qnt_p = qnt_p;
    }

    public double getPrice_c() {
        return price_c;
    }

    public void setPrice_c(double price_c) {
        this.price_c = price_c;
    }

    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }

    public int getId_chef() {
        return id_chef;
    }

    public void setId_chef(int id_chef) {
        this.id_chef = id_chef;
    }

    public int getId_pack() {
        return id_pack;
    }

    public void setId_pack(int id_pack) {
        this.id_pack = id_pack;
    }

    @Override
    public String toString() {
        return "Plate{" + "id_p=" + id_p + ", desc_p=" + desc_p + ", name_c=" + name_c + ", image_c=" + image_c + ", price_c=" + price_c + ", id_category=" + id_category + ", id_chef=" + id_chef + ", id_pack=" + id_pack + '}';
    }






    
    
}
