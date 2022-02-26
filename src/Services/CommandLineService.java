package Services;

import Entity.CommandLine;
import Entity.Users;
import Util.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommandLineService {
    private static final String GET_COMMAND_LINE_DATA = "SELECT * from commandline where id = ? ";
    private static final String INSERT_QUERY = "insert into commandline (id_client,id_plate,quantity,total) values (?,?,?,((SELECT price FROM plate where plate.id=?)*quantity))";
    private static final String SELECT_QUERY_USER = "SELECT * FROM commandline where id_client = ?";
    private static final String UPDATE_COMMAND_LINE_USER = "UPDATE COMMANDLINE SET QUANTITY = ?, total = ((SELECT price FROM plate where plate.id=?)*quantity)  where id= ?   ";
    private static final String DELETE_COMMAND_LINE = "DELETE FROM Commandline WHERE ID = ? ";

    public boolean insertReclamation(CommandLine cl) {
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement preparedStmt = cnx.prepareStatement(INSERT_QUERY);
            preparedStmt.setInt(1, cl.getId_client());
            preparedStmt.setInt(2, cl.getId_plate());
            preparedStmt.setInt(4, cl.getId_plate());
            preparedStmt.setInt(3, cl.getQuantity());
            preparedStmt.executeUpdate();
            preparedStmt.close();
            System.out.println("CommandLine Inserted");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static List<CommandLine> readcommndlineUser(Users u) {
        List<CommandLine> mylist = new ArrayList();
        CommandLine rc = new CommandLine();
        Statement st;
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement preparedStmt = cnx.prepareStatement(SELECT_QUERY_USER);
            preparedStmt.setInt(1, u.getId());
            ResultSet rs = preparedStmt.executeQuery();
            while (rs.next()) {

                rc.setId((rs.getInt("id")));
                rc.setId_command((rs.getInt("id_command")));
                rc.setId_client((rs.getInt("id_client")));
                rc.setId_plate((rs.getInt("id_plate")));
                rc.setQuantity(rs.getInt("quantity"));
                rc.setTotal(rs.getDouble("total"));
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

    public static boolean deleteCommandline(int id) {
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement preparedStmt = cnx.prepareStatement(DELETE_COMMAND_LINE);
            preparedStmt.setInt(1, id);
            preparedStmt.execute();
            System.out.println("CommandLine number " + id + " has been deleted !");
            return true;
        } catch (SQLException e) {
            System.err.println("Got an exception!");
            printSQLException(e);
            return false;
        }

    }

    public static boolean updateCommandline(CommandLine rc) {
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement preparedStmt = cnx.prepareStatement(UPDATE_COMMAND_LINE_USER);
            preparedStmt.setInt(1, rc.getQuantity());
            preparedStmt.setInt(2, rc.getId_plate());
            preparedStmt.setInt(3, rc.getId());
            preparedStmt.executeUpdate();
            preparedStmt.close();
            System.out.println("Commandline Updated");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static CommandLine commandLineInfo(int id) {
        CommandLine rc = new CommandLine();
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            ResultSet rs;
            PreparedStatement preparedStmt = cnx.prepareStatement(GET_COMMAND_LINE_DATA);
            preparedStmt.setInt(1, id);
            rs = preparedStmt.executeQuery();
            while (rs.next()) {
                rc.setId(id);
                rc.setId_command((rs.getInt("id_command")));
                rc.setId_client((rs.getInt("id_client")));
                rc.setId_plate((rs.getInt("id_plate")));
                rc.setQuantity(rs.getInt("quantity"));
                rc.setTotal(rs.getDouble("total"));
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
