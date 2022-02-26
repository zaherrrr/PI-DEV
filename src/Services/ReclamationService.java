package Services;

import Entity.Reclamation;
import Entity.Users;
import Util.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReclamationService {
    private static final String GET_RECLAMATION_DATA = "SELECT * from RECLAMATION where id = ? ";
    private static final String INSERT_QUERY = "insert into RECLAMATION (id_client,description,status) values (?,?,?)";
    private static final String SELECT_QUERY_ADMIN = "SELECT * FROM reclamation";
    private static final String SELECT_QUERY_USER = "SELECT * FROM reclamation where id_client = ?";
    private static final String UPDATE_RECLAMATION_USER = "UPDATE RECLAMATION SET description = ?  where id= ?   ";
    private static final String UPDATE_RECLAMATION_ADMIN = "UPDATE RECLAMATION SET RESPOND = ? , id_admin=? ,status= ?  where id= ?   ";
    private static final String DELETE_RECLAMATION = "DELETE FROM RECLAMATION WHERE ID = ? ";

    public boolean insertReclamation(Reclamation rc) {
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement preparedStmt = cnx.prepareStatement(INSERT_QUERY);
            preparedStmt.setInt(1, rc.getId_client());
            preparedStmt.setString(2, rc.getDescription());
            preparedStmt.setInt(3, 0);
            preparedStmt.executeUpdate();
            preparedStmt.close();
            System.out.println("Reclamation Inserted");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static List<Reclamation> readReclamationUser(Users u) {
        List<Reclamation> mylist = new ArrayList();
        Reclamation rc = new Reclamation();
        Statement st;
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement preparedStmt = cnx.prepareStatement(SELECT_QUERY_USER);
            preparedStmt.setInt(1, u.getId());
            ResultSet rs = preparedStmt.executeQuery();
            while (rs.next()) {

                rc.setId((rs.getInt("id")));
                rc.setDescription((rs.getString("description")));
                rc.setRespond((rs.getString("respond")));
                rc.setId_admin((rs.getInt("id_admin")));
                rc.setReclamationdate(rs.getDate("reclamationdate"));
                if (rs.getInt("status") == 0) {
                    rc.setStatus(false);
                } else {
                    rc.setStatus(true);
                }
                mylist.add(rc);
            }
        } catch (SQLException ex) {
            printSQLException(ex);
        }
        System.out.print(mylist);
        return mylist;
    }

    public static List<Reclamation> readReclamationAdmin() {
        List<Reclamation> mylist = new ArrayList();
        Reclamation rc = new Reclamation();
        Statement st;
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement preparedStmt = cnx.prepareStatement(SELECT_QUERY_ADMIN);
            ResultSet rs = preparedStmt.executeQuery();
            while (rs.next()) {

                rc.setId((rs.getInt("id")));
                rc.setDescription((rs.getString("description")));
                rc.setRespond((rs.getString("respond")));
                rc.setId_admin((rs.getInt("id_admin")));
                rc.setReclamationdate(rs.getDate("reclamationdate"));
                if (rs.getInt("status") == 0) {
                    rc.setStatus(false);
                } else {
                    rc.setStatus(true);
                }
                mylist.add(rc);
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

    public static boolean deleteReclamation(int id) {
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement preparedStmt = cnx.prepareStatement(DELETE_RECLAMATION);
            preparedStmt.setInt(1, id);
            preparedStmt.execute();
            System.out.println("RECLAMATION number " + id + " has been deleted !");
            return true;
        } catch (SQLException e) {
            System.err.println("Got an exception!");
            printSQLException(e);
            return false;
        }

    }

    public static boolean updateReclamationUser(Reclamation rc) {
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement preparedStmt = cnx.prepareStatement(UPDATE_RECLAMATION_USER);
            preparedStmt.setString(1, rc.getDescription());
            preparedStmt.setInt(2, rc.getId());
            preparedStmt.executeUpdate();
            preparedStmt.close();
            System.out.println("Reclamation Updated");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean updateReclamationAdmin(Reclamation rc) {
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement preparedStmt = cnx.prepareStatement(UPDATE_RECLAMATION_ADMIN);
            preparedStmt.setString(1, rc.getRespond());
            preparedStmt.setInt(2, rc.getId_admin());
            preparedStmt.setInt(3, 1);
            preparedStmt.executeUpdate();
            preparedStmt.close();
            System.out.println("Reclamation Responded");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static Reclamation reclamationInfo(int id) {
        Reclamation rc = new Reclamation();
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            ResultSet rs;
            PreparedStatement preparedStmt = cnx.prepareStatement(GET_RECLAMATION_DATA);
            preparedStmt.setInt(1, id);
            rs = preparedStmt.executeQuery();
            while (rs.next()) {
                rc.setId(id);
                rc.setId_client(rs.getInt("id_client"));
                rc.setDescription(rs.getString("description"));
                rc.setReclamationdate(rs.getDate("reclamationdate"));
                rc.setRespond(rs.getString("respond"));
                rc.setId_admin(rs.getInt("id_admin"));
                if (rs.getInt("status") == 0) {
                    rc.setStatus(false);
                } else {
                    rc.setStatus(true);
                }
            }
            preparedStmt.close();
            rs.close();
            return rc;
        } catch (SQLException e) {
            printSQLException(e);
            return rc;
        }
    }

}
