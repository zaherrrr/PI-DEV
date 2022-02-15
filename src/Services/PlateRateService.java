package Services;

import Entity.Plate;
import Entity.PlateRate;
import Entity.Reclamation;
import Entity.Users;
import Util.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlateRateService {
    private static final String GET_PLATERATE_DATA = "SELECT * from PLATERATE where id = ? ";
    private static final String INSERT_QUERY = "insert into platerate (id_plate,stars,id_user) values (?,?,?)";
    private static final String SELECT_QUERY = "SELECT * FROM platerate";
    private static final String SELECT_QUERY_USER = "SELECT * FROM platerate WHERE id_user=? ";
    private static final String SELECT_AVG_PLATE_RATE = "SELECT AVG(stars) from plate_rate";
    private static final String UPDATE_PLATE_RATE= "UPDATE platerate SET stars = ?  where id= ?   ";
    private static final String DELETE_PLATE_RATE ="DELETE FROM platerate WHERE ID = ? ";

    public boolean insertPlateRate(PlateRate pr) {
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            ResultSet rs;
            PreparedStatement preparedStmt = cnx.prepareStatement(INSERT_QUERY);
            preparedStmt.setInt(1, pr.getId_plate());
            preparedStmt.setInt(2, pr.getStars());
            preparedStmt.setInt(3, pr.getId_user());
            preparedStmt.executeUpdate();
            preparedStmt.close();
            System.out.println("PlateRate Inserted");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public static List<PlateRate> readPlateRate()
    {
        List<PlateRate> mylist= new ArrayList() ;
        PlateRate pr =new PlateRate();
        Statement st;
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            st=cnx.createStatement();
            ResultSet res=st.executeQuery(SELECT_QUERY);
            while (res.next()){
                pr.setId(res.getInt("id"));
                pr.setId_plate(res.getInt("id_plate"));
                pr.setStars(res.getInt("stars"));
                pr.setDaterate(res.getDate("daterate"));
                pr.setId_user(res.getInt("id_user"));

                mylist.add(pr);
            }
        } catch (SQLException ex) {
            printSQLException(ex);
        }
        System.out.print(mylist);
        return mylist;
    }
    public static float PlateRateAVG()
    {
       float AVGSTARS = 0 ;

        Statement st;
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            st=cnx.createStatement();
            ResultSet res=st.executeQuery(SELECT_AVG_PLATE_RATE);
            while (res.next()){
                AVGSTARS = res.getFloat(1);
            }
            return AVGSTARS;
        } catch (SQLException ex) {
            printSQLException(ex);
        }

        return AVGSTARS;
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
    public static boolean deletePlateRate(int id)  {
        try{
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement preparedStmt = cnx.prepareStatement(DELETE_PLATE_RATE);
            preparedStmt.setInt(1, id);
            preparedStmt.execute();
            System.out.println("PlateRate number "+id+" has been deleted !");
            return true;
        }
        catch (Exception e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            return false ;
        }

    }
    public static boolean updatePlateRate(PlateRate pr){
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement preparedStmt = cnx.prepareStatement(UPDATE_PLATE_RATE);
            preparedStmt.setInt(1, pr.getStars());
            preparedStmt.setInt(2, pr.getId());
            preparedStmt.executeUpdate();
            preparedStmt.close();
            System.out.println("PlateRate Updated");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public static PlateRate plateRateInfo(int id ){
        PlateRate pr = new PlateRate();
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            ResultSet rs;
            PreparedStatement preparedStmt = cnx.prepareStatement(GET_PLATERATE_DATA);
            preparedStmt.setInt(1, id);
            rs = preparedStmt.executeQuery();
            while (rs.next()) {
                pr.setId(id);
                pr.setId_user(rs.getInt("id_user"));
                pr.setDaterate(rs.getDate("daterate"));
                pr.setId_plate(rs.getInt("id_plate"));
                pr.setStars(rs.getInt("stars"));
            }
            preparedStmt.close();
            rs.close();
            return pr;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return pr;
        }
    }
    public static List<PlateRate> readPlateRates(Users u)
    {
        List<PlateRate> mylist= new ArrayList() ;
        PlateRate pr =new PlateRate();
        Statement st;
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement preparedStmt = cnx.prepareStatement(SELECT_QUERY_USER);
            preparedStmt.setInt(1, u.getId());
            ResultSet rs = preparedStmt.executeQuery();
            while (rs.next()) {
                pr.setId_user(u.getId());
                pr.setId((rs.getInt("id")));
                pr.setStars((rs.getInt("stars")));
                pr.setId_plate((rs.getInt("id_plate")));
                pr.setDaterate(rs.getDate("daterate"));
                mylist.add(pr);
            }
        } catch (SQLException ex) {
            printSQLException(ex);
        }
        System.out.print(mylist);
        return mylist;
    }
}
