package Services;

import Entity.Pack;
import Entity.Plate;
import Util.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PackService {
    private static final String GET_PACK_DATA = "SELECT * from plate where id = ? ";
    private static final String INSERT_QUERY = "insert into plate (id_category,id_chef,description,name,image,price) values (?,?,?,?,?,?)";
    private static final String SELECT_QUERY = "SELECT * FROM pack";
    private static final String UPDATE_PACK = "UPDATE pack SET price = ? ,description=? , name = ?  where id= ?   ";
    private static final String DELETE_PLATE = "DELETE FROM pack WHERE ID = ? ";

    public boolean insertPlate(Plate pc) {
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            ResultSet rs;
            PreparedStatement preparedStmt = cnx.prepareStatement(INSERT_QUERY);
            preparedStmt.setInt(1, pc.getId_category());
            preparedStmt.setInt(2, pc.getId_chef());
            preparedStmt.setString(3, pc.getDescription());
            preparedStmt.setString(4, pc.getName());
            preparedStmt.setString(5, pc.getImage());
            preparedStmt.setDouble(6, pc.getPrice());
            preparedStmt.setInt(7, pc.getId());
            preparedStmt.executeUpdate();
            preparedStmt.close();
            System.out.println("Plate Inserted");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static List<Pack> readPack() {
        List<Pack> mylist = new ArrayList();
        Pack pc = new Pack();
        Statement st;
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            st = cnx.createStatement();
            ResultSet res = st.executeQuery(SELECT_QUERY);
            while (res.next()) {
                pc.setId(res.getInt("id"));
                pc.setDescription(res.getString("description"));
                pc.setName(res.getString("name"));
                pc.setPrice(res.getDouble("price"));
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

    public static boolean deletePack(int id) {
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement preparedStmt = cnx.prepareStatement(DELETE_PLATE);
            preparedStmt.setInt(1, id);
            preparedStmt.execute();
            System.out.println("Pack number " + id + " has been deleted !");
            return true;
        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            return false;
        }

    }

    public static boolean updatePack(Pack pack) {
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement preparedStmt = cnx.prepareStatement(UPDATE_PACK);
            preparedStmt.setDouble(1, pack.getPrice());
            preparedStmt.setString(2, pack.getDescription());
            preparedStmt.setString(3, pack.getName());
            preparedStmt.setInt(4, pack.getId());
            preparedStmt.executeUpdate();
            preparedStmt.close();
            System.out.println("Pack Updated");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static Pack packInfo(int id) {
        Pack pack = new Pack();
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            ResultSet rs;
            PreparedStatement preparedStmt = cnx.prepareStatement(GET_PACK_DATA);
            preparedStmt.setInt(1, id);
            rs = preparedStmt.executeQuery();
            while (rs.next()) {
                pack.setId(id);
                pack.setName(rs.getString("name"));
                pack.setDescription(rs.getString("description"));
                pack.setPrice(rs.getDouble("price"));

            }
            preparedStmt.close();
            rs.close();
            return pack;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return pack;
        }
    }
}
