/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.entites.pi.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.sql.DataSource;
import pi.entites.PlateCategory;
import pi.utilis.Myconnexion;

/**
 *
 * @author bechi_msajrea
 */

    
    public class PlateCategoryService {
    private static final String GET_CATEGORY_DATA = "SELECT * from PLATECATEGORY where id = ? ";
    private static final String INSERT_QUERY = "insert into PLATECATEGORY (name,description) values (?,?)";
    private static final String SELECT_QUERY = "SELECT * FROM PLATECATEGORY";
    private static final String UPDATE_CATEGORY = "UPDATE PLATECATEGORY SET NAME = ? , description = ? where id= ?   ";
    private static final String DELETE_CATEGORY ="DELETE FROM PLATECATEGORY WHERE ID = ? ";
    Connection cnx;
    private Statement stt;
    private ResultSet rs;

    private static PlateCategoryService instance;
    
    public static PlateCategoryService getInstance(){
        if(instance==null) 
            instance=new PlateCategoryService();
        return instance;
    }
   

    public PlateCategoryService(){
        cnx= Myconnexion.getInstance().getCnx();
        
    }
    public List<PlateCategory> displayAll() {
        String req="select * from platecategory";
        ObservableList<PlateCategory> list=FXCollections.observableArrayList();       
        Statement st;
        try {
            
            st=cnx.createStatement();
            ResultSet rs=st.executeQuery(SELECT_QUERY);
            while(rs.next()){
                PlateCategory f =new PlateCategory();
                f.setId(rs.getInt("id"));
                f.setName(rs.getString("name"));
                f.setDescription(rs.getString("description"));
                
                list.add(f);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PlateCategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    
    public void insertPlateCategory(PlateCategory pc) {
         
        try {
           
           String requet2 = "INSERT INTO PlateCategory (name , description)" + "VALUES (?,?)";
           PreparedStatement st = cnx.prepareStatement(requet2);
          
           st.setString(1,pc.getName());
           st.setString(2,pc.getDescription());
           
           st.executeUpdate();
           System.out.println("plat ajouté");
       } catch (SQLException ex) {
           System.err.println(ex.getMessage());
       }
    }
     

    public List<PlateCategory> readPlateCategory()
    {
        ObservableList<PlateCategory>mylist= FXCollections.observableArrayList(); 
        PlateCategory pc =new PlateCategory();
        Statement st;
        try {
            Connection cnx = Myconnexion.getInstance().getCnx();
            st=cnx.createStatement();
            ResultSet res=st.executeQuery(SELECT_QUERY);
            while (res.next()){
                pc.setId(res.getInt("id"));
                pc.setName(res.getString("name"));
                pc.setDescription(res.getString("description"));
                pc.setNumberofplates(res.getInt("numberofplates"));
                mylist.add(pc);
            }
        } catch (SQLException ex) {
            printSQLException(ex);
        }
        System.out.print(mylist);
        return mylist;
    }
    public void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
    
    public  boolean deleteCategoryplate(int id)  {
        try{
            Connection cnx = Myconnexion.getInstance().getCnx();
            PreparedStatement preparedStmt = cnx.prepareStatement(DELETE_CATEGORY);
            preparedStmt.setInt(1, id);
            preparedStmt.execute();
            System.out.println("Cat number "+id+" has been deleted !");
            return true;
        }
        catch (Exception e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            return false ;
        }

    }
    public boolean updatePlateCategory(PlateCategory pc){
        try {
            Connection cnx = Myconnexion.getInstance().getCnx();
            ResultSet rs;
            PreparedStatement preparedStmt = cnx.prepareStatement(UPDATE_CATEGORY);
            preparedStmt.setString(1, pc.getName());
            preparedStmt.setString(2, pc.getDescription());
            preparedStmt.setInt(3, pc.getId());
            preparedStmt.executeUpdate();
            preparedStmt.close();
            System.out.println("PlateCategory UpdatedS");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public PlateCategory plateInfo(int id ){
        PlateCategory pc = new PlateCategory();
        try {
            Connection cnx = Myconnexion.getInstance().getCnx();
            ResultSet rs;
            PreparedStatement preparedStmt = cnx.prepareStatement(GET_CATEGORY_DATA);
            preparedStmt.setInt(1, id);
            rs = preparedStmt.executeQuery();
            while (rs.next()) {
                    pc.setId(id);
                    pc.setName(rs.getString("name"));
                    pc.setDescription(rs.getString("description"));
                    pc.setNumberofplates(rs.getInt("numberofplates"));
            }


            preparedStmt.close();

            return pc;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return pc;
        }
    }
    
    
    

}
    
    
    
    
    
    
    
    
    
    
    












