/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entities.Plate;
import edu.esprit.utils.MyConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class PlateCRUD {
         Connection   cnx;
    public PlateCRUD(){
        cnx=MyConnection.getInstance().getCnx();
    }
    public void ajouterPlate(Plate c){
               String requete ="INSERT INTO plate (id_p,desc_p,name_c,image_c,price_c,id_category,id_chef,id_pack)"+"VALUES (?,?,?,?,?,?,?,?)";
            try {
                PreparedStatement pst= new MyConnection().getCnx().prepareStatement(requete);
                pst.setInt(1,c.getId_p());
                
                  pst.setString(2, c.getDesc_p());
                     pst.setString(3,c.getName_c());
                  pst.setString(4,c.getImage_c());
                     pst.setDouble(5,c.getPrice_c());
                     pst.setInt(6,c.getId_category());
                     pst.setInt(7,c.getId_chef());
                     pst.setInt(8,c.getId_pack());
                  pst.executeUpdate();
                 
                 System.out.println("Plate added successfully");
            } catch (SQLException ex) {
               System.err.println(ex.getMessage());
            }
           
    }
    public void supprimerPlate(Plate c){
                 
 try {
           String sql = "DELETE FROM plate WHERE id_p=?";
           PreparedStatement statement = new MyConnection().getCnx().prepareStatement(sql);
           statement.setInt(1,c.getId_p());
           
           int rowsDeleted = statement.executeUpdate();
           if (rowsDeleted > 0) {
               System.out.println("Plate was deleted successfully!");
           }      } catch (SQLException ex) {
                System.err.println(ex.getMessage());
       }
    }
    public void modifierPlate(Plate c){
        try {
           String requete = "UPDATE plate SET price_c=? WHERE id_p=?";
           PreparedStatement st = cnx.prepareStatement(requete);
           
           st.setDouble(1, c.getPrice_c());
             st.setInt(2, c.getId_p());
      
          
           int rowsUpdated = st.executeUpdate();
           if (rowsUpdated > 0) {
               System.out.println("An existing Plate was updated successfully!");
           }} catch (SQLException ex) {
           System.err.println(ex.getMessage());
       }

 
    }
    public List<Plate> afficherPlate(){
        List<Plate> myList= new ArrayList<>();
        String requete="SELECT * FROM plate";
            try {
                Statement st= new MyConnection().getCnx().createStatement();
            ResultSet rs =    st.executeQuery(requete);
            while(rs.next()){
                Plate c = new Plate();
                c.setId_p(rs.getInt(1));
               
                c.setDesc_p(rs.getString(2));
                  c.setName_c(rs.getString(3));
                 
                c.setImage_c(rs.getString(3));
                 c.setPrice_c(rs.getDouble(4));
                  c.setId_category(rs.getInt(5));
                   c.setId_chef(rs.getInt(6));
                    c.setId_pack(rs.getInt(7));
                myList.add(c);
            }
            } catch (SQLException ex) {
               System.err.println(ex.getMessage());
            }
            return myList;
    }
    
    public  Plate plateInfo(int id ){
        Plate plate = new Plate();
        try {
         String requete="SELECT * from plate where id_p = ?" ;
            ResultSet rs;
            PreparedStatement preparedStmt = cnx.prepareStatement(requete);
            preparedStmt.setInt(1, id);
            rs = preparedStmt.executeQuery();
            while (rs.next()) {
                plate.setId_p(id);
                plate.setName_c(rs.getString("name_c"));
                plate.setDesc_p(rs.getString("desc_p"));
                plate.setPrice_c(rs.getDouble("price_c"));
                plate.setImage_c(rs.getString("image_c"));
                plate.setId_chef(rs.getInt("id_chef"));
                plate.setId_pack(rs.getInt("id_pack"));
                plate.setId_category(rs.getInt("id_category"));
            }
            preparedStmt.close();
            rs.close();
            return plate;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return plate;
        }
    }

}
