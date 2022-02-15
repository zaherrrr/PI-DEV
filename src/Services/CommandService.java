package Services;

import Entity.Command;
import Entity.Users;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommandService {
    private static final String SELECT_QUERY_USER = "select * from command where id_user = ?" ;
    private static final String INSERT_QUERY = "INSERT INTO command (id_client,total) VALUES(?,(SELECT sum(commandline.total) FROM commandline where commandline.id_client=?) ) ";
    private static final String DELETE_QUERY = "DELETE FROM commande WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE commande SET  where id = ? ";
    private static final String VALIDATE_COMMAND_LINES = "UPDATE commandline SET id_command = ? where (id_client= ? and id_command is null) ";
    private static final String LAST_COMMAND_USER = "SELECT ID FROM command where id_client= ? order by id desc limit 1";
    public boolean insertCommand(Command c , Users u){
        try {
            Connection cnx = Util.DataSource.getInstance().getCnx();
            PreparedStatement pst = cnx.prepareStatement(INSERT_QUERY);
            pst.setInt(1, u.getId());
            pst.setInt(2, u.getId());
            pst.executeUpdate();
            System.out.println("Command Added ! ");
            PreparedStatement preparedStmt = cnx.prepareStatement(LAST_COMMAND_USER);
            preparedStmt.setInt(1, u.getId());
            ResultSet rs = preparedStmt.executeQuery();
            while (rs.next()){
                c.setId(rs.getInt("id"));
                System.out.println(c.getId());
            }
            PreparedStatement pst2 = cnx.prepareStatement(VALIDATE_COMMAND_LINES);
            pst2.setInt(1,c.getId());
            pst2.setInt(2,u.getId());
            pst2.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println("Erreur ");
            return false;
        }
    }

    public boolean deleteCommand(int id ){

        try {
             Connection cnx = Util.DataSource.getInstance().getCnx();
            PreparedStatement pst = cnx.prepareStatement(DELETE_QUERY);
            pst.setInt(1, id);
            pst.executeUpdate();
            pst.close();
            System.out.println("Command Deleted!");
            return true;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println("Error");
            return false;
        }
    }

    public List<Command> readCommandUser(Users u){
        List<Command> list=new ArrayList();
        Command c = new Command();
        try {
            Connection cnx = Util.DataSource.getInstance().getCnx();
            PreparedStatement pst = cnx.prepareStatement(SELECT_QUERY_USER);
            pst.setInt(1, u.getId());
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                c.setId_client(rs.getInt("id_client"));
                c.setTotal(rs.getDouble("total"));
                c.setDatecommand(rs.getDate("datecommnand"));
                c.setId(rs.getInt("id"));
                list.add(c);
            }
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println("Error");
        }
        return list;
    }

    public boolean updateCommand(Command c){
        try{
            Connection cnx = Util.DataSource.getInstance().getCnx();
            PreparedStatement pst = cnx.prepareStatement(UPDATE_QUERY);
            pst.setInt(1, c.getId());
            pst.executeUpdate();

            return true;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println("Error ");
            return false;
        }

    }

}
