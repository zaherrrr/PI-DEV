package Services;

import Entities.Command;
import Entities.CommandLine;
import Entities.Reclamation;
import Entities.Users;
import Utils.DataSource;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CommandService {
    private static final String GET_COMMANDS = "SELECT * from command where user_id = ?";

    private static final String GET_CART = "SELECT COUNT(*) AS COUNT,id from command where  user_id = ? and status = 0 LIMIT 1";

    private static final String INSERT_QUERY = "insert into command (user_id,status) values (?,?)";
    private static final String SELECT_QUERY_USER = "SELECT id FROM command where user_id = ? and status = 0";
    private static final String UPDATE_COMMAND_LINE_USER = "UPDATE command_line SET QUANTITY = ?, total = ((SELECT price FROM plate where plate.id=?)*quantity)  where id= ?   ";
    private static final String DELETE_COMMAND_LINE = "DELETE FROM command_line WHERE ID = ? ";

    public static Command createCart(int id) {
        Command command = new Command();
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement preparedStmt = cnx.prepareStatement(INSERT_QUERY);
            preparedStmt.setInt(1, id);
            preparedStmt.setInt(2, 0);
            preparedStmt.executeUpdate();
            preparedStmt.close();
            System.out.println("New Cart for User "+id+" Has been created!");
            PreparedStatement pst = cnx.prepareStatement(SELECT_QUERY_USER);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                command.setId(rs.getInt("id"));
            }
            pst.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("New cart generated id:"+command.getId());
        return command;
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

    public static Command currentCart(int id) {
        Command command = new Command();
        CommandLineService commandLineService = new CommandLineService();
        int ID = 0;
        int count = 0;
        int total = 0;

        try {
            Connection cnx = DataSource.getInstance().getCnx();
            ResultSet rs;
            PreparedStatement preparedStmt = cnx.prepareStatement(GET_CART);
            preparedStmt.setInt(1, id);
            rs = preparedStmt.executeQuery();
            while (rs.next()) {
                count =rs.getInt("COUNT");
                ID =rs.getInt("id");
             }
            preparedStmt.close();
            rs.close();
            if(count == 1 ){
                command.setId(ID);
                System.out.println("Cart found!");
                command.setCommandLines(commandLineService.findByCommand(command));
                for (CommandLine commandLine : command.getCommandLines()){
                    total = total + commandLine.getTotal();
                }
                command.setTotal(total);

            }
            else if(count == 0){
                System.out.println("Cart not found! Creating cart..");
                command = createCart(id);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }

        return command;
    }
    public static ArrayList<Command> commandList(Users u)
    {
        ArrayList<Command> mylist= new ArrayList();
        CommandLineService commandLineService = new CommandLineService();
        String Status = "";
        Statement st;
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            st = cnx.createStatement();
            ResultSet res;
            PreparedStatement preparedStmt = cnx.prepareStatement(GET_COMMANDS);
            preparedStmt.setInt(1, u.getId());
            res = preparedStmt.executeQuery();
            while (res.next()){
                Command c =new Command();
                c.setUser(u);
                c.setId(res.getInt("id"));
                c.setId_client(u.getId());
                c.setStatus(res.getInt("status"));
                c.setDatecommand(res.getDate("date_command"));
                c.setCommandLines(commandLineService.findByCommand(c));
                for (CommandLine commandLine : c.getCommandLines()){
                    c.setTotal(c.getTotal()+commandLine.getTotal());
                }

                mylist.add(c);
            }

        } catch (SQLException ex) {
            printSQLException(ex);
        }
        System.out.print("User: "+u.getAfterName()+" Commands: "+mylist);
        return mylist;
    }
}
