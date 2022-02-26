package Services;

import Entity.PlateCategory;
import Util.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlateCategoryService {
    private static final String GET_CATEGORY_DATA = "SELECT * from PLATECATEGORY where id = ? ";
    private static final String INSERT_QUERY = "insert into PLATECATEGORY (NAME,DESCRIPTION) values (?,?)";
    private static final String SELECT_QUERY = "SELECT * FROM PLATECATEGORY";
    private static final String UPDATE_CATEGORY = "UPDATE PLATECATEGORY SET NAME = ? , description = ? where id= ?   ";
    private static final String DELETE_CATEGORY = "DELETE FROM PLATECATEGORY WHERE ID = ? ";

    public boolean insertPlateCategory(PlateCategory pc) {
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            ResultSet rs;
            PreparedStatement preparedStmt = cnx.prepareStatement(INSERT_QUERY);
            preparedStmt.setString(1, pc.getName());
            preparedStmt.setString(2, pc.getDescription());
            preparedStmt.executeUpdate();
            preparedStmt.close();
            System.out.println("PlateCategory Inserted");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static List<PlateCategory> readPlateCategory() {
        List<PlateCategory> mylist = new ArrayList();
        PlateCategory pc = new PlateCategory();
        Statement st;
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            st = cnx.createStatement();
            ResultSet res = st.executeQuery(SELECT_QUERY);
            while (res.next()) {
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

    public static boolean deleteCategoryplate(int id) {
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement preparedStmt = cnx.prepareStatement(DELETE_CATEGORY);
            preparedStmt.setInt(1, id);
            preparedStmt.execute();
            System.out.println("Cat number " + id + " has been deleted !");
            return true;
        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            return false;
        }

    }

    public static boolean updatePlateCategory(PlateCategory pc) {
        try {
            Connection cnx = DataSource.getInstance().getCnx();
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

    public static PlateCategory plateInfo(int id) {
        PlateCategory pc = new PlateCategory();
        try {
            Connection cnx = DataSource.getInstance().getCnx();
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
