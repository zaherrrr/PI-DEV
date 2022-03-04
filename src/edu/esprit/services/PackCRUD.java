/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.tests.MainClass; 
import edu.esprit.utils.MyConnection; 
import edu.esprit.entities.Evenements;
import edu.esprit.entities.Packs;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import java.io.FileOutputStream; 
import com.itextpdf.text.Document; 
import com.itextpdf.text.Paragraph; 
import com.itextpdf.text.pdf.PdfWriter; 
import java.io.*; 
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author User
 */
public class PackCRUD {
    Connection cnx2; 
    
    public PackCRUD()
    {
        cnx2 = MyConnection.getInstance().getCnx(); 
    }
 
    public void ajouterPack(Packs p){
        try {
            PreparedStatement pst = null;
             
            String requete2 = "INSERT INTO pack (name,description,price,id_owner) VALUES (?,?,?,?)";
            pst = cnx2.prepareStatement(requete2);
            pst.setString(1, p.getName());
            pst.setString(2, p.getDescription());
            pst.setDouble(3, p.getPrice());
            pst.setInt(4, p.getId_owner());
            pst.executeUpdate(); 
            System.out.println("Votre pack est ajouté");
        } catch (SQLException ex) {
            Logger.getLogger(PackCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    /*public void ajouterPack2(Packs p){
        PreparedStatement pst = null;
        try {
            String requete2 = "INSERT INTO pack (id,nom,description,prix)" + "VALUES (?,?,?,?)"; 
            pst = cnx2.prepareStatement(requete2);
            pst.setInt(1, p.getId()); 
        pst.setString(2, p.getNom()); 
        pst.setString(3,p.getDescription()); 
        pst.setDouble(4, p.getPrix()); 
        
        
        pst.executeUpdate(); 
            System.out.println("Votre pack est ajouté");
               
        } catch (SQLException ex) {
            System.err.println(ex.getMessage()); 
        
    }
    }
*/
    
        
   public List<Packs> afficherPack(){
       List<Packs>myList = new ArrayList<>();
        try {
            
            String requete3 = "SELECT * FROM pack";
            Statement st =cnx2.createStatement(); 
           ResultSet rs = st.executeQuery(requete3);
        while(rs.next()){
            Packs p = new Packs(); 
            p.setId(rs.getInt(1)); 
            p.setName(rs.getString("name")); 
            p.setDescription(rs.getString("description")); 
            p.setPrice(rs.getDouble("price"));
            p.setId_owner(rs.getInt("id_owner"));
            
            myList.add(p); 
        }
        
        
        } catch (SQLException ex) { 
System.err.println(ex.getMessage());
        }
        return myList; 
    }


   public void modifierPack(Packs p ){
       try {
           String requet4 = "UPDATE pack name=?, description=? ,  WHERE id=?";
           PreparedStatement st = cnx2.prepareStatement(requet4);
           
           st.setInt(3,p.getId());
           st.setString(1,p.getName()); 
           st.setString(2,p.getDescription()); 
         
           int rowsUpdated = st.executeUpdate();
           if (rowsUpdated > 0) {
               System.out.println("votre pack a ete modifie avec succés!");
           }} catch (SQLException ex) {
           System.err.println(ex.getMessage());
       }
   }
   
   
        public void supprimerPack(Packs p){
       try {
           String sql = "DELETE FROM pack WHERE id=?";
           PreparedStatement statement = cnx2.prepareStatement(sql);
           statement.setInt(1,p.getId());
           
           int rowsDeleted = statement.executeUpdate();
           if (rowsDeleted > 0) {
               System.out.println(" le pack a ete supprime avec succes!");
           }      } catch (SQLException ex) {
                System.err.println(ex.getMessage());
       }
    }
        
        
        
        public class Mypdf_file{
            
        }
}


