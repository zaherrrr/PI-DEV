package Services;

public class UserSession {
    public static UserSession getInstance() {
        return instance;
    }

    private static int id;
    public static UserSession instance;
    private static String email;
    private static String role;
    private static String name;
    private static String aftername;
    private static String profilepicture;


    public static void setId(int id) {
        UserSession.id = id;
    }

    public static int getId() {
        return id;
    }

    public static void setProfilepicture(String profilepicture) {
        UserSession.profilepicture = profilepicture;
    }

    public static String getProfilepicture() {
        return profilepicture;
    }

    public static String getName() {
        return name;
    }

    public static String getAftername() {
        return aftername;
    }


    public static void setRole(String role) {
        UserSession.role = role;
    }

    public static String getRole() {
        return role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static String getEmail() {
        return email;
    }

    public UserSession(int id, String role, String email, String name, String aftername, String profilepicture) {
        this.id = id;
        this.email = email;
        this.role = role;
        this.name = name;
        this.aftername = aftername;
        this.profilepicture = profilepicture;
    }

    public static void setInstance(UserSession instance) {
        UserSession.instance = instance;
    }

    public static UserSession getInstance(int id, String email, String role, String name, String aftername, String profilepicture) {
        if (instance == null) {
            instance = new UserSession(id, email, role, role, aftername, profilepicture);
        }


        return instance;

    }

    public static void cleanUserSession() {
        id = 0;
        email = null;
        role = null;
        name = null;
        aftername = null;
        profilepicture = null;
        instance = null;
    }

    @Override
    public String toString() {
        return "Logged in as {" +
                "Email='" + email + '\'' +
                ", Role ='" + role + '\'' +
                ", Name ='" + name + '\'' +
                ", Aftername='" + aftername + '\'' +
                '}';
    }
}
