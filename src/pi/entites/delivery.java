/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.entites;

/**
 *
 * @author bechi_msajrea
 */
public class delivery {

private int id;
private String locationx;
private String locationy;
private Double shippingprice;
private int id_command;
private int id_deliveryguy;

    public delivery() {
    }

    public delivery(String locationx, String locationy, Double shippingprice, int id_command, int id_deliveryguy) {
        this.locationx = locationx;
        this.locationy = locationy;
        this.shippingprice = shippingprice;
        this.id_command = id_command;
        this.id_deliveryguy = id_deliveryguy;
    }

    public delivery(int id, String locationx, String locationy, Double shippingprice, int id_command, int id_deliveryguy) {
        this.id = id;
        this.locationx = locationx;
        this.locationy = locationy;
        this.shippingprice = shippingprice;
        this.id_command = id_command;
        this.id_deliveryguy = id_deliveryguy;
    }

    public int getId() {
        return id;
    }

    public String getLocationx() {
        return locationx;
    }

    public String getLocationy() {
        return locationy;
    }

    public Double getShippingprice() {
        return shippingprice;
    }

    public int getId_command() {
        return id_command;
    }

    public int getId_deliveryguy() {
        return id_deliveryguy;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLocationx(String locationx) {
        this.locationx = locationx;
    }

    public void setLocationy(String locationy) {
        this.locationy = locationy;
    }

    public void setShippingprice(Double shippingprice) {
        this.shippingprice = shippingprice;
    }

    public void setId_command(int id_command) {
        this.id_command = id_command;
    }

    public void setId_deliveryguy(int id_deliveryguy) {
        this.id_deliveryguy = id_deliveryguy;
    }

}
