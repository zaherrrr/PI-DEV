/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;


import com.mysql.jdbc.Util;
import edu.esprit.utils.MyConnection;
import edu.esprit.entities.CommandLine;
import edu.esprit.entities.Plate;
import edu.esprit.entities.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class LineCommandeCRUD {

    Connection cnx;

    public LineCommandeCRUD() {
        cnx = MyConnection.getInstance().getCnx();
    }

    /*      public void ajouterCommande(){
        String requete ="INSERT INTO Commande (nomCom)"+"VALUES ('lala')";
            try {
                Statement st= new MyConnection().getCnx().createStatement();
                 st.executeUpdate(requete);
                 System.out.println("Personne ajoutée avec succés");
            } catch (SQLException ex) {
               System.err.println(ex.getMessage());
            }
       
    }
     */

    public boolean ajouterCommandLine(Plate plate,Users u,int qnt) {
       
       String requete = "INSERT INTO commandline (id_p,id_user,quantite) VALUES(?,?,?)" ;
        if(qnt<plate.getQnt_p()) {
       try {
            PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete);
            pst.setInt(1, plate.getId_p());
       
            pst.setInt(2, u.getId());
            pst.setInt(3, qnt);
   
            pst.executeUpdate();
          
            System.out.println("Line Command added successfully");
              return true;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return false;
        }

    }
        else{
              System.out.println("Quantite inssufisante");
             return false;
        }}

    public boolean supprimerCommandLine(int id) {

        try {
            String sql = "DELETE FROM commandline WHERE idLC=?";
            PreparedStatement statement = new MyConnection().getCnx().prepareStatement(sql);
            statement.setInt(1,id);

           statement.executeUpdate();
            statement.close();
           
                System.out.println("Order was deleted successfully!");
        return true;   
        }
         catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println("Echec de suppression");
            return false;
        }
    }

    public boolean modifierCommandLine(CommandLine c,int qnt) {
  
            
               try{
                   String requete = "UPDATE commandline SET quantityPLT=? WHERE idLC=?";
       PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete);
            pst.setInt(1, qnt);
            pst.setInt(2,c.getIdLC() );
            pst.executeUpdate();
            System.out.println("Produit Modifier! ");
            return true;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println("Erreur ");
            return false;
        }
    }

    public List<CommandLine> afficherCommandLine(int id_user) {
        List<CommandLine> myList = new ArrayList<>();
        String requete = "SELECT * FROM commandline p inner join plate pr \n";
        try {
           PreparedStatement st = new MyConnection().getCnx().prepareStatement(requete);
          st.setInt(1, id_user);
           ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                CommandLine c = new CommandLine();
                     c.setId_client(id_user);
                c.setIdLC(rs.getInt("idLC"));
                c.setPlate(rs.getInt("id_p"));
                c.setTotalLC(rs.getDouble("price_c")*rs.getInt("quantity_PLT"));
                c.setQuantityPLT(rs.getInt("quantity_PLT"));
                System.out.println(c);
                
                myList.add(c);
            }
              st.close();
            rs.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
             System.out.println("Erreur");
        }
        return myList;
    }

    public CommandLine commandLineInfo(int id) {
        CommandLine rc = new CommandLine();
        try {
            String requete = "SELECT * from commandline where id = ? ";
            ResultSet rs;
            PreparedStatement preparedStmt = cnx.prepareStatement(requete);
            preparedStmt.setInt(1, id);
            rs = preparedStmt.executeQuery();
            while (rs.next()) {
                rc.setIdLC(id);
                rc.setId_command((rs.getInt("id_command")));
                rc.setId_client((rs.getInt("id_client")));
                rc.setId_p((rs.getInt("id_p")));
                rc.setQuantityPLT(rs.getInt("quantityplt"));
                rc.setTotalLC(rs.getDouble("totalLc"));
            }
            preparedStmt.close();
            rs.close();
            return rc;
        } catch (SQLException e) {
            printSQLException(e);
            return rc;
        }
    }
       public int getQuantiteP(int id){
        Plate p = new Plate();
        int total = 0;
        int id_prod = 0;
         try {
           String  requete="SELECT id_p FROM commandline WHERE idLC = ? ";
           String requete1="SELECT quantity FROM plate WHERE id_p = ?";
            PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                 id_prod = rs.getInt("id_p");
            }
            PreparedStatement pst2 = new MyConnection().getCnx().prepareStatement(requete1);
            pst2.setInt(1,id_prod);
            ResultSet RS = pst2.executeQuery();
            while(RS.next()){
                total = RS.getInt("quantity_PLT");
            }
            pst.close();
            rs.close();
            pst2.close();
            RS.close();
            return total;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println("Erreur");
        }
           return total;
   }
         
     public int getIdPanier(Plate p,Users u){
        Plate panier = new Plate();
        int id = 0;
        
         try {
           String  requete="SELECT idLC FROM commandline WHERE ID_p = ? and id_user = ? and id_commande = 0";
            PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete);
            pst.setInt(1, p.getId_p());
            pst.setInt(2, u.getId());

            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                 id = rs.getInt("id");
            }
           
            pst.close();
            rs.close();
          
            return id;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println("Erreur");
        }
           return id;
   }
             

    private void printSQLException(SQLException e1) {
               for (Throwable e : e1) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = e1.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
