package Services;

import Entities.Reclamation;
import Entities.Users;
import Utils.DataSource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ReclamationService {
    private static final  String INSERT_QUERY = "INSERT INTO reclamation (utilisateur_id,description,sujet,status) VALUES (?,?,?,0)";
    private static final  String UPDATE_QUERY = "UPDATE reclamation set reponse= ? ,status = 1  where id= ? ";
    private static final  String DELETE_QUERY = "DELETE FROM reclamation WHERE ID = ? ";
    private static final  String SELECT_QUERY = "SELECT *  FROM  reclamation ";
    private static final  String SELECT_QUERY_BYID = "SELECT *  FROM  reclamation where id = ?  ";
    private static final  String SELECT_QUERY_BYUSER = "SELECT *  FROM  reclamation where utilisateur_id = ?  ";

    private static final  String SEARCH_QUERY = "SELECT *  FROM  reclamation WHERE UPPER(ID) LIKE ? OR UPPER(utilisateur) LIKE ? OR  UPPER(description) LIKE ?  ";


    public static void printSQLException(SQLException ex) {
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

    public boolean reclamationAdd(Reclamation u) throws SQLException {
        try{
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement preparedStmt = cnx.prepareStatement(INSERT_QUERY);
            preparedStmt.setInt (1, u.getId_client());
            preparedStmt.setString (2, u.getDescription());
            preparedStmt.setString (3, u.getSujet());
            preparedStmt.execute();
            System.out.println("Reclamation inserted.. ");
            return true;

        }
        catch (Exception e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            return false ;
        }

    }
    public boolean reclamationRespond(int id, String reply) throws SQLException {
        try{
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement preparedStmt = cnx.prepareStatement(UPDATE_QUERY);
            preparedStmt.setInt (2, id);
            preparedStmt.setString (1, reply);
            preparedStmt.execute();
            System.out.println("Respond Inserted.");
            return true;
        }
        catch (SQLException e)
        {
            printSQLException(e);
            return false ;
        }

    }
    public static boolean reclamationDelete(int id) throws SQLException {
        try{
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement preparedStmt = cnx.prepareStatement(DELETE_QUERY);
            preparedStmt.setInt(1, id);
            preparedStmt.execute();
            System.out.println("Reclamation Deleted!");
            return true;

        }
        catch (Exception e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            return false ;
        }

    }


    public static ObservableList<Reclamation> reclamationList()
    {
        ObservableList<Reclamation> mylist= FXCollections.observableArrayList();

        String Status = "";
        Statement st;
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            st=cnx.createStatement();
            ResultSet res=st.executeQuery(SELECT_QUERY);
            while (res.next()){
                Reclamation c =new Reclamation();
                c.setId(res.getInt("id"));
                c.setDescription(res.getString("description"));
                c.setSujet(res.getString("sujet"));
                c.setRespond(res.getString("reponse"));
                c.setStatus(res.getInt("status"));
                if (res.getInt("status") == 0) {
                    Status = "Pending";
                    c.setStatus(0);
                }
                else if(res.getInt("status") == 1){
                    Status = "Responded";
                    c.setStatus(1);

                }
                mylist.add(c);
            }

        } catch (SQLException ex) {
            printSQLException(ex);
        }
        System.out.print(mylist);
        return mylist;
    }
    public static ArrayList<Reclamation> reclamationList(Users u)
    {
        ArrayList<Reclamation> mylist= new ArrayList();

        String Status = "";
        Statement st;
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            st = cnx.createStatement();
            ResultSet res;
            PreparedStatement preparedStmt = cnx.prepareStatement(SELECT_QUERY_BYUSER);
            preparedStmt.setInt(1, u.getId());
            res = preparedStmt.executeQuery();
            while (res.next()){
                Reclamation c =new Reclamation();
                c.setId(res.getInt("id"));
                c.setId_client(u.getId());
                c.setDescription(res.getString("description"));
                c.setSujet(res.getString("sujet"));
                c.setRespond(res.getString("reponse"));
                c.setStatus(res.getInt("status"));
                c.setUser(u);
                if (res.getInt("status") == 0) {
                    Status = "Pending";
                    c.setStatus(0);
                }
                else if(res.getInt("status") == 1){
                    Status = "Responded";
                    c.setStatus(1);

                }
                mylist.add(c);
            }

        } catch (SQLException ex) {
            printSQLException(ex);
        }
        System.out.println("User: "+u.getAfterName()+" Reclamations: "+mylist);
        return mylist;
    }


}
