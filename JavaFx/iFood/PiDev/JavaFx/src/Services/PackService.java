package Services;

import Entities.*;
import Utils.DataSource;

import java.sql.*;
import java.util.ArrayList;

public class PackService {
    private static final String GET_PACKS = "SELECT * from pack";

    public static ArrayList<Pack> packList()
    {
        ArrayList<Pack> mylist= new ArrayList();
      /*  PackLineService packLineService = new PackLineService();
        String Status = "";
        Statement st;
        try {
            /*Connection cnx = DataSource.getInstance().getCnx();
            st = cnx.createStatement();
            ResultSet res;
            PreparedStatement preparedStmt = cnx.prepareStatement(GET_PACKS);
            res = preparedStmt.executeQuery();
            while (res.next()){
                Pack pack =new Pack();
                pack.setId(res.getInt("id"));
                pack.setName("name");
                pack.setDescription(res.getString("description"));
                pack.setPackLines(PackLineService.findByPack(pack));
                for (PackLine packLine : pack.getPackLines()){
                    pack.setTotal(pack.getTotal()+packLine.getTotal());
                }

                mylist.add(pack);
            }

        } catch (SQLException ex) {
            printSQLException(ex);
        } */
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
}
