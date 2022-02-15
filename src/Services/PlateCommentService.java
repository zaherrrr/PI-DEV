package Services;

import Entity.*;
import Util.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlateCommentService {
    private static final String GET_PLATECOMMENT_DATA = "SELECT * FROM PLATECOMMENT where id = ? ";
    private static final String INSERT_QUERY = "insert into PLATECOMMENT (ID_PLATE,id_user,content) values (?,?,?)";
    private static final String SELECT_QUERY = "SELECT * FROM platecomment where id_plate = ? ";
    private static final String SELECT_QUERY_USER = "SELECT * FROM platecomment WHERE id_user=? ";
    private static final String UPDATE_PLATE_COMMENT = "UPDATE platecomment SET content = ?  where id= ?   ";
    private static final String DELETE_PLATE_COMMENT = "DELETE FROM PLATECOMMENT WHERE ID = ? ";

    public boolean insertPlateComment(PlateComment pc) {
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            ResultSet rs;
            PreparedStatement preparedStmt = cnx.prepareStatement(INSERT_QUERY);
            preparedStmt.setInt(1, pc.getId_plate());
            preparedStmt.setInt(2, pc.getId_user());
            preparedStmt.setString(3, pc.getContent());
            preparedStmt.executeUpdate();
            preparedStmt.close();
            System.out.println("PlateComment Inserted");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static List<PlateComment> readPlateComment(Plate p) {
        List<PlateComment> mylist = new ArrayList();
        PlateComment pc = new PlateComment();
        Statement st;
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement preparedStmt = cnx.prepareStatement(SELECT_QUERY);
            preparedStmt.setInt(1, p.getId());
            ResultSet rs = preparedStmt.executeQuery();
            while (rs.next()) {
                pc.setId_plate(p.getId());
                pc.setId((rs.getInt("id")));
                pc.setId_user((rs.getInt("id_user")));
                pc.setContent((rs.getString("contect")));
                pc.setDatecomment(rs.getDate("datecomment"));
                mylist.add(pc);
            }
        } catch (SQLException ex) {
            printSQLException(ex);
        }
        System.out.print(mylist);
        return mylist;

    }

    public static boolean deletePlateComment(int id) {
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement preparedStmt = cnx.prepareStatement(DELETE_PLATE_COMMENT);
            preparedStmt.setInt(1, id);
            preparedStmt.execute();
            System.out.println("PlateComment number " + id + " has been deleted !");
            return true;
        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            return false;
        }

    }

    public static boolean updatePlateComment(PlateComment pc) {
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement preparedStmt = cnx.prepareStatement(UPDATE_PLATE_COMMENT);
            preparedStmt.setString(1, pc.getContent());
            preparedStmt.setInt(2, pc.getId());
            preparedStmt.executeUpdate();
            preparedStmt.close();
            System.out.println("PlateComment Updated");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static PlateComment plateCommentInfo(int id) {
        PlateComment pc = new PlateComment();
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            ResultSet rs;
            PreparedStatement preparedStmt = cnx.prepareStatement(GET_PLATECOMMENT_DATA);
            preparedStmt.setInt(1, id);
            rs = preparedStmt.executeQuery();
            while (rs.next()) {
                pc.setId(id);
                pc.setId_user(rs.getInt("id_user"));
                pc.setDatecomment(rs.getDate("datecomment"));
                pc.setId_plate(rs.getInt("id_plate"));
                pc.setContent(rs.getString("content"));
            }
            preparedStmt.close();
            rs.close();
            return pc;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return pc;
        }
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
}
