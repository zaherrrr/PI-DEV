/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.utilis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;




/**
 *
 * @author bechi_msajrea
 */
public class Myconnexion {
    
    public String url="jdbc:mysql://localhost:3306/pidev";
    public String login="root";
    public String pwd="";
    Connection cnx;
    public static Myconnexion instance;

    public static Myconnexion getInstance() {
        if (instance==null){
            instance=new Myconnexion();
        }
        return instance;
    }
    public Connection getCnx() {
        return cnx;
    }
   
private Myconnexion(){
        try {
            System.out.println("------");
          cnx= DriverManager.getConnection(url, login, pwd);
          System.out.println("connexion etablie");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        
        }
    
    

        
}
