package Services;
import Entities.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Utils.DataSource;

import java.sql.*;
import java.util.*;

public class CategoryService {
    private static final  String INSERT_QUERY = "INSERT INTO category (name,description) VALUES (?,?)";
    private static final  String UPDATE_QUERY = "UPDATE category set description= ? where id= ? ";
    private static final  String DELETE_QUERY = "DELETE FROM category WHERE ID = ? ";
    private static final  String SELECT_QUERY = "SELECT *  FROM  category ";
    private static final  String SELECT_QUERY_BYID = "SELECT *  FROM  aide where id = ?  ";

    private static final  String SEARCH_QUERY = "SELECT *  FROM  category WHERE UPPER(ID) LIKE ? OR UPPER(categorie) LIKE ? OR  UPPER(description) LIKE ?  ";

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

    public boolean AddCategory(PlateCategory u) throws SQLException {
        try{
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement preparedStmt = cnx.prepareStatement(INSERT_QUERY);

            preparedStmt.setString (1, u.getName());
            preparedStmt.setString (2, u.getDescription());

            preparedStmt.execute();
            return true;

        }
        catch (Exception e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            return false ;
        }

    }
    public static ArrayList<PlateCategory> categoryData()
    {
        ArrayList<PlateCategory> mylist= new ArrayList();
        Statement st;
        PlateServices plateServices = new PlateServices();
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            st=cnx.createStatement();
            ResultSet res=st.executeQuery(SELECT_QUERY);
            while (res.next()){
                PlateCategory c =new PlateCategory();
                c.setId(res.getInt("id"));
                c.setName(res.getString("Name"));
                c.setDescription(res.getString("description"));
                c.setPlates(PlateServices.readPlates(c));
                mylist.add(c);
            }


        } catch (SQLException ex) {
            printSQLException(ex);
        }

        return mylist;
    }
    public static ObservableList<PlateCategory> categoryList()
    {
        ObservableList<PlateCategory> mylist= FXCollections.observableArrayList();
        Statement st;
        PlateServices plateServices = new PlateServices();
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            st=cnx.createStatement();
            ResultSet res=st.executeQuery(SELECT_QUERY);
            while (res.next()){
                PlateCategory c =new PlateCategory();
                c.setId(res.getInt("id"));
                c.setName(res.getString("Name"));
                c.setDescription(res.getString("description"));
                c.setPlates(plateServices.readPlates(c));
                mylist.add(c);
            }


        } catch (SQLException ex) {
            printSQLException(ex);
        }
        System.out.println("Category List:");
        System.out.print(mylist);
        return mylist;
    }
}
