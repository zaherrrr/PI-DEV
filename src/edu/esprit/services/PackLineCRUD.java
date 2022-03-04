/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entities.PackLine;
import edu.esprit.entities.Packs;
import edu.esprit.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class PackLineCRUD {
     Connection cnx2; 
    
    public PackLineCRUD()
    {
        cnx2 = MyConnection.getInstance().getCnx(); 
    }

    public PackLineCRUD(String pack1, String ce_packline_numero_1, int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
    public void ajouterPackLine(PackLine pL){
        PreparedStatement pst = null;
        try {
            String requete2 = "INSERT INTO pack (id_pack ,id_plate, quantity, total) VALUES (?,?,?,?)"; 
            pst = cnx2.prepareStatement(requete2);
         pst.setInt(1, pL.getId_pack()); 
        pst.setInt(2, pL.getId_plate()); 
         pst.setInt(3,pL.getQuantity());
         pst.setDouble(4,pL.getTotal()); 
         // pst.setInt(1, p.getId_client()); 
        pst.executeUpdate(); 
            System.out.println("Votre packline est ajoutée");
               
        } catch (SQLException ex) {
            System.err.println(ex.getMessage()); 
        
    }
    }
    
        
   public List<Packs> afficherPackLine(){
       List<Packs>myList = new ArrayList<>();
        try {
            
            String requete3 = "SELECT * FROM pack";
            Statement st =cnx2.createStatement(); 
           ResultSet rs = st.executeQuery(requete3);
        while(rs.next()){
            Packs p = new Packs(); 
            p.setId(rs.getInt(1)); 
            p.setName(rs.getString("name")); 
            myList.add(p); 
        }
        
        
        } catch (SQLException ex) { 
System.err.println(ex.getMessage());
        }
        return myList; 
    }


    public void modifierPackLine(Packs p ){
       try {
           String requet4 = "UPDATE pack SET categorie=?, WHERE id=?";
           PreparedStatement st = cnx2.prepareStatement(requet4);
           
           //st.setInt(1,p.getId());
           st.setString(1,p.getName()); 
           st.setString(2,p.getDescription()); 
         
           int rowsUpdated = st.executeUpdate();
           if (rowsUpdated > 0) {
               System.out.println("la packline a été modifié avec succés!");
           }} catch (SQLException ex) {
           System.err.println(ex.getMessage());
       }
   }
        public void supprimerPackLine(Packs p){
       try {
           String sql = "DELETE FROM pack WHERE id=?";
           PreparedStatement statement = cnx2.prepareStatement(sql);
           statement.setInt(1,p.getId());
           
           int rowsDeleted = statement.executeUpdate();
           if (rowsDeleted > 0) {
               System.out.println(" le pack a ete supprimé avec succés!");
           }      } catch (SQLException ex) {
                System.err.println(ex.getMessage());
       }
    }

    /*public void ajouterPackLine(PackLineCRUD pL1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

    public void ajouterPackLine(PackLineCRUD pL1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
