package Services;

import Entities.Plate;
import Entities.PlateCategory;
import Utils.DataSource;

import java.sql.*;
import java.util.ArrayList;

public class PlateServices {
    private static final String GET_PLATE_DATA = "SELECT * from plate where id = ? ";
    private static final String INSERT_QUERY = "insert into plate (name,description,quantity,category_id,price) values (?,?,?,?,?)";
    private static final String SELECT_QUERY = "SELECT * FROM plate ";
    private static final String SELECT_PLATE_BY_CATEGORY = "SELECT * FROM plate  WHERE CATEGORY_ID = ? ";
    private static final String UPDATE_PLATE = "UPDATE plate SET category_id = ? ,description=? , name = ? , price = ?  where id= ?   ";
    private static final String DELETE_PLATE = "DELETE FROM plate WHERE ID = ? ";
    private static final String GET_PLATE_SALES ="SELECT count(cl.quantity) as count  from PLATE p INNER JOIN command_line cl INNER join command c where p.id = ? and cl.plate_id = p.id and cl.command_id = c.id and c.status =1";
    public boolean insertPlate(Plate pc) {
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            ResultSet rs;
            PreparedStatement preparedStmt = cnx.prepareStatement(INSERT_QUERY);
            preparedStmt.setString(1, pc.getName());
            preparedStmt.setString(2, pc.getDescription());
            preparedStmt.setInt(3, pc.getQuantity());
            preparedStmt.setInt(4, pc.getId_category());
            preparedStmt.setInt(5, pc.getPrice());
            preparedStmt.executeUpdate();
            preparedStmt.close();
            System.out.println("Plate Inserted");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static ArrayList<Plate> readPlates() {
        ArrayList<Plate> mylist = new ArrayList();
        Statement st;
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            st = cnx.createStatement();
            ResultSet res = st.executeQuery(SELECT_QUERY);
            while (res.next()) {
                Plate pc = new Plate();
                pc.setId(res.getInt("id"));
                pc.setId_category(res.getInt("category_id"));
                pc.setDescription(res.getString("description"));
                pc.setName(res.getString("name"));
                pc.setPrice(res.getInt("price"));
                pc.setQuantity(res.getInt("quantity"));
                ResultSet resultSet;
                PreparedStatement preparedStmt = cnx.prepareStatement(GET_PLATE_SALES);
                preparedStmt.setInt(1, pc.getId());
                resultSet = preparedStmt.executeQuery();
                while (resultSet.next()){
                    pc.setNumberOfSales(resultSet.getInt("count"));
                    pc.setTotalOfSales(resultSet.getInt("count")*pc.getPrice());
                }
                mylist.add(pc);
            }
        } catch (SQLException ex) {
            printSQLException(ex);
        }
        return mylist;
    }
public static ArrayList<Plate> readPlates(PlateCategory category) {
        ArrayList<Plate> mylist = new ArrayList();
        Statement st;
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            st = cnx.createStatement();
            ResultSet res;
            PreparedStatement preparedStmt = cnx.prepareStatement(SELECT_PLATE_BY_CATEGORY);
            preparedStmt.setInt(1, category.getId());
            res = preparedStmt.executeQuery();
            while (res.next()) {
                Plate pc = new Plate();
                pc.setId(res.getInt("id"));
                pc.setId_category(res.getInt("category_id"));
                pc.setDescription(res.getString("description"));
                pc.setName(res.getString("name"));
                pc.setPrice(res.getInt("price"));
                pc.setQuantity(res.getInt("quantity"));
                pc.setCategory(category);
                ResultSet resultSet;
                PreparedStatement preparedStatement = cnx.prepareStatement(GET_PLATE_SALES);
                preparedStatement.setInt(1, pc.getId());
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    pc.setNumberOfSales(resultSet.getInt("count"));
                    pc.setTotalOfSales(resultSet.getInt("count")*pc.getPrice());
                }
                mylist.add(pc);
            }
        } catch (SQLException ex) {
            printSQLException(ex);
        }

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
            preparedStmt.setDouble(4, pc.getPrice());
            preparedStmt.setInt(5, pc.getId());
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
        int total = 0;
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
                plate.setPrice(rs.getInt("price"));
                plate.setId_category(rs.getInt("category_id"));
                plate.setQuantity(rs.getInt("quantity"));
                total = 1;
            }
            if (total == 0){
                plate = new Plate(0,"null","null",0,0,0);
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
