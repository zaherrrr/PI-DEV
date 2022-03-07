/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entities.Commande;
import edu.esprit.entities.Users;

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
public class CommandeCRUD {
      Connection   cnx;
    public CommandeCRUD(){
        cnx=MyConnection.getInstance().getCnx();
    }
    public boolean ajouterCommande(Users u){
        String requete1="INSERT INTO commande (id_user,etat_cmd) VALUES(?,'0')";
        String requete2="UPDATE commandline SET id_commande = ? where (id_user= ? and id_commande =0 )";
               String requete3 ="SELECT ID FROM commande order by id desc limit 1";
 Commande c = new Commande();
        try {
            PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete1);
            pst.setInt(1, u.getId());
            pst.executeUpdate();
            System.out.println("Commande Ajouté! ");
            Statement st = new MyConnection().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while (rs.next()) {
                c.setId(rs.getInt("id"));
            }
            PreparedStatement pst2 = new MyConnection().getCnx().prepareStatement(requete2);
            pst2.setInt(1, c.getId());
            pst2.setInt(2, u.getId());
            pst2.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println("Erreur ");
            return false;
        }
           
    }
    public void supprimerCommande(Commande c){
                 
 try {
           String sql = "DELETE FROM Commande WHERE id_c=?";
           PreparedStatement statement = new MyConnection().getCnx().prepareStatement(sql);
           statement.setInt(1,c.getId());
           
           int rowsDeleted = statement.executeUpdate();
           if (rowsDeleted > 0) {
               System.out.println("Command was deleted successfully!");
           }      } catch (SQLException ex) {
                System.err.println(ex.getMessage());
       }
    }
    public boolean modifierCommande(Commande c){
        String requete="UPDATE commande SET etat_cmd = '1' where id = ? ";
                try {
            PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete);
            pst.setInt(1, c.getId());
            pst.executeUpdate();
            System.out.println("Commande Validé!! ");
            return true;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println("Erreur ");
            return false;
        }
 
    }
    public List<Commande> afficherCommande(int id_user){
        List<Commande> list = new ArrayList();
        try {
            String requete="select * from commande where id_user = ?";
            PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete);
            pst.setInt(1, id_user);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Commande c = new Commande();
                c.setId_user(id_user);
                c.setId(rs.getInt("id"));
                if (rs.getInt("etat_cmd") == 0) {
                    c.setEtat_cmd(false);
                } else {
                    c.setEtat_cmd(true);
                }
                c.setDate_cmd(rs.getDate("date_cmd"));
                list.add(c);
            }
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println("Erreur");
        }
        return list;
    }
    
}
