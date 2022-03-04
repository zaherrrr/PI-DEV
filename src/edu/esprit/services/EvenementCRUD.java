/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.tests.MainClass;
import edu.esprit.entities.Evenements;
import java.util.List;
//import edu.esps.Evenements; 
import edu.esprit.utils.MyConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class EvenementCRUD {

    Connection cnx2;

    public EvenementCRUD() {
        cnx2 = MyConnection.getInstance().getCnx();
    }

    public EvenementCRUD(String string, String mariage, int i, int i0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /* 
    public void ajouterEvenement(){
        try {
            String requete="INSERT INTO event (id,id_user,id_pack,quantity,total,dateevent)"  +" VALUES ('1',fiancaille','2022-01-01') ";
             
            Statement st = cnx2 .createStatement();
         st.executeUpdate(requete); 
         System.out.println("evenemet ajoutée avec succes "); 
        
        
        } catch (SQLException ex) {
            System.err.println(ex.getMessage()); 
        }
       
    }
     */
    public void ajouterEvenement2(Evenements e) {

        String requete2 = "INSERT INTO event (id_client,id_pack,name,dateevent)" + "VALUES (?,?,?,?)";
        PreparedStatement pst;
        try {
            pst = cnx2.prepareStatement(requete2);

            //pst.setInt(1, e.getId());
            pst.setInt(1, e.getId_client());
            pst.setInt(2, e.getId_pack());
            pst.setString(3, e.getName());
            pst.setDate(4, e.getDateevent());
            pst.executeUpdate();
            System.out.println("Votre evenement est ajouté");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
    }

    public List<Evenements> afficherEvenement() {
        List<Evenements> myList = new ArrayList<>();
        try {

            String requete3 = "select * from event";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while (rs.next()) {
                Evenements e = new Evenements();
                e.setId(rs.getInt(1));
                e.setName(rs.getString(requete3));
                e.setDateevent(rs.getDate(requete3));
                myList.add(e);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }

    
    public void modifierEvenement(Evenements e ){
       try {
           String requet4 = "UPDATE event SET  id_pack=? , name=? , dateevent=? WHERE id=?";
           PreparedStatement st = cnx2.prepareStatement(requet4);
           
           
          
           st.setInt(1,e.getId_pack());
           st.setString(2,e.getName());
           st.setDate(3,e.getDateevent());
           st.setInt(4,e.getId());
           
           int rowsUpdated = st.executeUpdate();
           if (rowsUpdated > 0) {
               System.out.println(rowsUpdated);
               System.out.println("l'evenement a ete modifie avec succés!");
           }} catch (SQLException ex) {
           System.err.println(ex.getMessage());
       }
   }
        public void supprimerEvenement(Evenements e){
       try {
           String sql = "DELETE FROM event WHERE id=?";
           PreparedStatement statement = cnx2.prepareStatement(sql);
           statement.setInt(1,e.getId());
           
           int rowsDeleted = statement.executeUpdate();
           if (rowsDeleted > 0) {
               System.out.println(" l'evnement a ete supprime avec succes!");
           }      } catch (SQLException ex) {
                System.err.println(ex.getMessage());
       }
    }
}
