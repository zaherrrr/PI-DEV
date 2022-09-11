package Services;

import Entities.Command;
import Entities.CommandLine;
import Entities.Users;
import Utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommandLineService {
    private static final String GET_COMMAND_LINE_DATA = "SELECT * from command_line where id = ? ";
    private static final String INSERT_QUERY = "insert into command_line (command_id,plate_id,quantity) values (?,?,?)";
    private static final String SELECT_QUERY_USER = "SELECT * FROM command_line where id_client = ?";
    private static final String SELECT_BY_COMMAND = "SELECT * FROM command_line where command_id = ?";
    private static final String UPDATE_COMMAND_LINE_USER = "UPDATE command_line SET QUANTITY = ? where id= ?   ";
    private static final String DELETE_COMMAND_LINE = "DELETE FROM command_line WHERE ID = ? ";
    private static final String UPDATE_COMMAND_LINE_QUANTITY= "UPDATE command_line SET QUANTITY = ? where id= ?   ";
    private static final String CHECK_INFO = "SELECT COUNT(*) AS COUNT,id from command_line where  command_id = ? and plate_id = ? LIMIT 1";

    public boolean insertCommandeLine(CommandLine cl) {
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement preparedStmt = cnx.prepareStatement(INSERT_QUERY);
            preparedStmt.setInt(1,cl.getId_command());
            preparedStmt.setInt(2, cl.getId_plate());
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
    public void check_info(CommandLine commandLine){
        int ID = 0;
        int count = 0;
        System.out.println(commandLine.getId_command());
        System.out.println(commandLine.getId_plate());
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            ResultSet rs;
            PreparedStatement preparedStmt = cnx.prepareStatement(CHECK_INFO);
            preparedStmt.setInt(1, commandLine.getId_command());
            preparedStmt.setInt(2, commandLine.getId_plate());
            rs = preparedStmt.executeQuery();
            while (rs.next()) {
                count =rs.getInt("COUNT");
                ID =rs.getInt("id");
            }
            preparedStmt.close();
            rs.close();
            if(count == 1 ){
                commandLine.setId(ID);
                updateCommandline(commandLine);
                System.out.println("Quantity updated!");
            }
            else if(count == 0){
               insertCommandeLine(commandLine);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public static List<CommandLine> readcommndlineUser(Users u) {
        List<CommandLine> mylist = new ArrayList<>();
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
                rc.setId_plate((rs.getInt("id_plate")));
                rc.setQuantity(rs.getInt("quantity"));
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
            preparedStmt.setInt(2, rc.getId());
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
                rc.setId_plate((rs.getInt("id_plate")));
                rc.setQuantity(rs.getInt("quantity"));
            }
            preparedStmt.close();
            rs.close();
            return rc;
        } catch (SQLException e) {
            printSQLException(e);
            return rc;
        }

    }
    public static ArrayList<CommandLine> findByCommand(int id){
        ArrayList<CommandLine> mylist = new ArrayList();
        CommandLine commandLine = new CommandLine();
        PlateServices plateServices = new PlateServices();
        Statement st;
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement preparedStmt = cnx.prepareStatement(SELECT_BY_COMMAND);
            preparedStmt.setInt(1, id);
            ResultSet rs = preparedStmt.executeQuery();
            while (rs.next()) {
                commandLine.setId((rs.getInt("id")));
                commandLine.setId_command((rs.getInt("command_id")));
                commandLine.setId_plate((rs.getInt("plate_id")));
                commandLine.setQuantity(rs.getInt("quantity"));

                commandLine.setPlate(plateServices.plateInfo(commandLine.getId_plate()));

                mylist.add(commandLine);
            }
        } catch (SQLException ex) {
            printSQLException(ex);
        }
        System.out.print(mylist);
        return mylist;
    }
    public static ArrayList<CommandLine> findByCommand(Command c){
        ArrayList<CommandLine> mylist = new ArrayList<>();
        PlateServices plateServices = new PlateServices();
        Statement st;
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement preparedStmt = cnx.prepareStatement(SELECT_BY_COMMAND);
            preparedStmt.setInt(1, c.getId());
            ResultSet rs = preparedStmt.executeQuery();
            while (rs.next()) {
                CommandLine rc = new CommandLine();
                rc.setId((rs.getInt("id")));
                rc.setId_command(c.getId());
                rc.setId_plate((rs.getInt("plate_id")));
                rc.setQuantity(rs.getInt("quantity"));
                rc.setCommand(c);
                rc.setPlate(plateServices.plateInfo(rc.getId_plate()));
                rc.setTotal(rc.getPlate().getPrice()*rc.getQuantity());
                System.out.println(rc.getTotal());
                mylist.add(rc);
            }
        } catch (SQLException ex) {
            printSQLException(ex);
        }
        return mylist;
    }

}
