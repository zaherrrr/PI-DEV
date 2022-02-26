package Services;

import Entity.Plate;
import Util.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlateService {
    private static final String GET_PLATE_DATA = "SELECT * from plate where id = ? ";
    private static final String INSERT_QUERY = "insert into plate (id_category,id_chef,description,name,image,price) values (?,?,?,?,?,?)";
    private static final String SELECT_QUERY = "SELECT * FROM plate";
    private static final String UPDATE_PLATE = "UPDATE plate SET id_category = ? ,description=? , name = ? ,image = ?, price = ?  where id= ?   ";
    private static final String DELETE_PLATE = "DELETE FROM plate WHERE ID = ? ";

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

    public static List<Plate> readPlate() {
        List<Plate> mylist = new ArrayList();
        Plate pc = new Plate();
        Statement st;
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            st = cnx.createStatement();
            ResultSet res = st.executeQuery(SELECT_QUERY);
            while (res.next()) {
                pc.setId(res.getInt("id"));
                pc.setId_category(res.getInt("id_category"));
                pc.setId_chef(res.getInt("id_chef"));
                pc.setDescription(res.getString("description"));
                pc.setName(res.getString("name"));
                pc.setImage(res.getString("image"));
                pc.setPrice(res.getDouble("price"));
                pc.setId_pack(res.getInt("id_pack"));
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

    public static boolean deletePlate(int id) {
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement preparedStmt = cnx.prepareStatement(DELETE_PLATE);
            preparedStmt.setInt(1, id);
            preparedStmt.execute();
            System.out.println("Plate number " + id + " has been deleted !");
            return true;
        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            return false;
        }

    }

    public static boolean updatePlate(Plate pc) {
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement preparedStmt = cnx.prepareStatement(UPDATE_PLATE);
            preparedStmt.setInt(1, pc.getId_category());
            preparedStmt.setString(2, pc.getDescription());
            preparedStmt.setString(3, pc.getName());
            preparedStmt.setString(4, pc.getImage());
            preparedStmt.setDouble(5, pc.getPrice());
            preparedStmt.setInt(6, pc.getId());
            preparedStmt.executeUpdate();
            preparedStmt.close();
            System.out.println("Plate Updated");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static Plate plateInfo(int id) {
        Plate plate = new Plate();
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            ResultSet rs;
            PreparedStatement preparedStmt = cnx.prepareStatement(GET_PLATE_DATA);
            preparedStmt.setInt(1, id);
            rs = preparedStmt.executeQuery();
            while (rs.next()) {
                plate.setId(id);
                plate.setName(rs.getString("name"));
                plate.setDescription(rs.getString("description"));
                plate.setPrice(rs.getDouble("price"));
                plate.setImage(rs.getString("image"));
                plate.setId_chef(rs.getInt("id_chef"));
                plate.setId_pack(rs.getInt("id_pack"));
                plate.setId_category(rs.getInt("id_category"));
            }
            preparedStmt.close();
            rs.close();
            return plate;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return plate;
        }
    }

}
