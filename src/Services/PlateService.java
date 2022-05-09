package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Entities.Plate;
import Utils.DataSource;

/**
 *
 * @author bechi_msajrea
 */
public class PlateService {
    private static final String GET_Plate_DATA ="SELECT * from Plate where id= ? ";
    private static final String INSERT_QUERY = "insert into PLATE (NAME,DESCRIPTION) values (?,?)";
    private static final String SELECT_QUERY = "SELECT * FROM PLATE";
    private static final String UPDATE_Plate = "UPDATE PLATE SET NAME = ? , description = ? where id= ?   ";
    private static final String DELETE_Plate ="DELETE FROM PLATE WHERE ID = ? ";
    Connection cnx;
    public boolean insertPlate( Plate p) {
        try {
            Connection cnx =DataSource.getInstance().getCnx();
            ResultSet rs;
            PreparedStatement preparedStmt = cnx.prepareStatement(INSERT_QUERY);
            preparedStmt.setString(1,p.getName());
            preparedStmt.setString(2,p.getDescription());
            preparedStmt.executeUpdate();
            preparedStmt.close();
            System.out.println("Plate Inserted");
            return true;

        }   catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;

        }}
    public List<Plate> readPlate() throws SQLException
    {
        List<Plate> mylist=new ArrayList() ;
        Plate p =new Plate() ;
        Statement st ;
        try
        {
            Connection cnx =DataSource.getInstance().getCnx();
            st=cnx.createStatement();
            ResultSet res=st.executeQuery(SELECT_QUERY);
            while (res.next()) {
                p.setId(res.getInt("id"));
                p.setName(res.getString("Name"));
                p.setDescription(res.getString("Description"));
                mylist.add(p);

            }
        }
        catch(SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return null;

    }
    public void printSQLException (SQLException ex) {
        for (Throwable e : ex){
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
    public boolean deletePlate(int id) {
        try {
            Connection cnx =DataSource.getInstance().getCnx();
            PreparedStatement preparedStmt =cnx.prepareStatement(DELETE_Plate);
            preparedStmt.setInt(1,id);
            preparedStmt.execute();
            System.out.println("Plate number "+id+"has been deleted !");
            return true;

        } catch (SQLException e) {
            System.err.println("An exception!!");
            System.err.println(e.getMessage());
            return false;
        }
    }
    public boolean updatePlate(Plate p ) throws SQLException {
        try {
            Connection cnx =DataSource.getInstance().getCnx();
            ResultSet rs ;
            PreparedStatement preparedStmt = cnx.prepareStatement(UPDATE_Plate);
            preparedStmt.setString(1, p.getName());
            preparedStmt.setString(2, p.getDescription());
            preparedStmt.setInt(3, p.getId());
            preparedStmt.setString(4, p.getImage());
            preparedStmt.setDouble(5, p.getPrice());
            preparedStmt.executeUpdate();
            preparedStmt.close();
            System.out.println("Plate Updated");
            return true;
        }
        catch (SQLException e ) {
            System.out.println(e.getMessage());
            return false ;
        }}}
