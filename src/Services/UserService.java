package Services;

import Entity.Users;
import Util.DataSource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserService {

    private static final String VALIDATE_LOGIN = "SELECT COUNT(*) AS COUNT FROM UTILISATEUR WHERE EMAIL =? and PASSWORD=? LIMIT 1";
    private static final String VALIDATE_EMAIL = "SELECT id from utilisateur where email=?";
    private static final String GET_USER_DATA = "SELECT ID,ROLES,EMAIL,name,aftername,profilepicture from utilisateur where email=?";
    private static final String INSERT_QUERY = "insert into utilisateur (name,aftername,email,password,roles,profilepicture) values(?,?,?,?,?,?)";
    private static final String SELECT_QUERY = "select * from utilisateur ";
    private static final String USER_LOGIN = "insert into userlogins (id_user) values (?)";
    private static final String STATUS_ST_QUERY = "select roles, count(*) from utilisateur group by roles ";
    private static final String DELETE_USER = "delete from utilisateur where id = ?";
    private static final String UPDATE_USER = "UPDATE UTILISATEUR SET NAME = ? ,AFTERNAME=? , EMAIL = ? ,PASSWORD = ?, ROLES = ? , PROFILEPICTURE = ?  where id= ?   ";


    public boolean validateLogin(String email, String password) {
        try {
            int Login = 0;
            Connection cnx = DataSource.getInstance().getCnx();
            ResultSet rs;
            PreparedStatement preparedStmt = cnx.prepareStatement(VALIDATE_LOGIN);
            preparedStmt.setString(1, email);
            preparedStmt.setString(2, password);
            rs = preparedStmt.executeQuery();
            while (rs.next()) {
                Login = rs.getInt("COUNT");
            }
            rs.close();
            System.out.println("Result set Closed.");
            preparedStmt.close();
            System.out.println("Prepared Statement closed.");

            if (Login > 0) {
                System.out.println("User found");
                return true;
            } else {
                System.out.println("User not found ");
                return false;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean setOnline(String email) {
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            ResultSet rs;
            int id = 0;
            String role = "";
            String name = "";
            String aftername = "";
            String profilepicture = "";
            PreparedStatement preparedStmt = cnx.prepareStatement(GET_USER_DATA);
            preparedStmt.setString(1, email);
            rs = preparedStmt.executeQuery();        // Get the result table from the query  3
            while (rs.next()) {               // Position the cursor                  4
                id = rs.getInt("id");
                role = rs.getString("roles");
                name = rs.getString("name");
                aftername = rs.getString("aftername");
                profilepicture = rs.getString("profilepicture");
            }
            UserSession.cleanUserSession();
            UserSession userOnline = new UserSession(id, role, email, name, aftername, profilepicture);
            UserSession.setInstance(userOnline);
            rs.close();
            preparedStmt.close();
            return true;
        } catch (SQLException e) {
            printSQLException(e);
            return false;
        }
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

    public boolean add(Users u) throws SQLException {
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement preparedStmt = cnx.prepareStatement(INSERT_QUERY);
            preparedStmt.setString(1, u.getName());
            preparedStmt.setString(2, u.getAfterName());
            preparedStmt.setString(3, u.getEmail());
            preparedStmt.setString(4, u.getPassword());
            preparedStmt.setString(5, u.getRole());
            preparedStmt.setString(6, u.getProfilePicture());
            preparedStmt.execute();
            preparedStmt.close();
            return true;
        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            return false;
        }

    }

    public boolean addUserLogin(int id) throws SQLException {
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement preparedStmt = cnx.prepareStatement(USER_LOGIN);
            preparedStmt.setInt(1, id);
            preparedStmt.execute();
            preparedStmt.close();
            return true;

        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            return false;
        }

    }

    public boolean validateEmail(String email) {
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            ResultSet rs;
            int Login = 0;
            PreparedStatement preparedStmt = cnx.prepareStatement(VALIDATE_EMAIL);
            preparedStmt.setString(1, email);
            rs = preparedStmt.executeQuery();
            while (rs.next()) {
                Login = rs.getInt("id");
            }
            rs.close();
            preparedStmt.close();
            if (Login > 0) {
                System.out.println("Error User with same mail found !");
                return false;
            } else {
                System.out.println("Email is valid.");
                return true;
            }

        } catch (SQLException e) {
            printSQLException(e);
            return false;
        }
    }

    public static ArrayList<Users> readUsers() {
        ArrayList<Users> mylist = new ArrayList();
        String role = "";
        Statement st;
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            st = cnx.createStatement();
            ResultSet res = st.executeQuery(SELECT_QUERY);
            while (res.next()) {
                Users c = new Users();
                c.setId(res.getInt("id"));
                c.setName(res.getString("name"));
                c.setAfterName(res.getString("aftername"));
                c.setEmail(res.getString("email"));
                c.setProfilePicture(res.getString("profilepicture"));

                if (res.getString("roles").equals("ROLE_ADMIN")) {
                    role = "Admin";
                } else if (res.getString("roles").equals("ROLE_CHEF")) {
                    role = "Chef";
                } else if (res.getString("roles").equals("ROLE_DELIVERY")) {
                    role = "Delivery";
                }
                c.setRole(role);
                c.setJoinDate(res.getTimestamp("creationdate"));
                mylist.add(c);
            }


        } catch (SQLException ex) {
            printSQLException(ex);
        }
        System.out.print(mylist);
        return mylist;
    }

    public ObservableList<PieChart.Data> userStats() {
        ObservableList<PieChart.Data> myList = FXCollections.observableArrayList();
        Connection cnx = DataSource.getInstance().getCnx();
        try {
            String role = "";
            ResultSet res = cnx.createStatement().executeQuery(STATUS_ST_QUERY);
            while (res.next()) {
                if (res.getString("roles").equals("ROLE_ADMIN")) {
                    role = "Admin";
                } else if (res.getString("roles").equals("ROLE_CHEF")) {
                    role = "Chef";
                } else if (res.getString("roles").equals("ROLE_DELIVERY")) {
                    role = "Delivery";
                } else if (res.getString("roles").equals("ROLE_CLIENT")) {
                    role = "Client";
                }
                PieChart.Data c = new PieChart.Data(role, res.getInt("count(*)"));
                myList.add(c);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(myList);

        return myList;
    }

    public static boolean deleteUser(int id) {
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement preparedStmt = cnx.prepareStatement(DELETE_USER);
            preparedStmt.setInt(1, id);
            preparedStmt.execute();
            System.out.println("User number " + id + " has been deleted !");
            return true;
        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            return false;
        }

    }

    public static boolean updateUser(Users user) {
        try {
            Connection cnx = DataSource.getInstance().getCnx();

            PreparedStatement preparedStmt = cnx.prepareStatement(UPDATE_USER);
            preparedStmt.setString(1, user.getName());
            preparedStmt.setString(2, user.getAfterName());
            preparedStmt.setString(3, user.getEmail());
            preparedStmt.setString(4, user.getPassword());
            preparedStmt.setString(5, user.getRole());
            preparedStmt.setString(6, user.getProfilePicture());
            preparedStmt.setInt(7, user.getId());
            preparedStmt.executeUpdate();
            preparedStmt.close();
            System.out.println("User Updated");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
